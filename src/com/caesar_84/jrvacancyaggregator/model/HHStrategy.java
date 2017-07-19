package com.caesar_84.jrvacancyaggregator.model;

import com.caesar_84.jrvacancyaggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caesar-84 on 11/14/16.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> result = new ArrayList<>();
        try
        {
            int pageNum = 0;
            Document document;
            while (true)
            {
                document = getDocument(searchString, pageNum++);
                if (document == null) break;
                Elements vacancyElement = document.select("[data-qa=vacancy-serp__vacancy]");
                if (vacancyElement.isEmpty()) break;

                for (Element element : vacancyElement)
                {
                    Vacancy vacancy = new Vacancy();

                    // get title
                    Element titleElem = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
                    String title = titleElem.text();

                    // get salary
                    Element salaryElem = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElem != null) salary = salaryElem.text();

                    // get company name
                    Element employerElem = element.select("[data-qa=vacancy-serp__vacancy-employer]").first();
                    String company = employerElem.text();

                    // get city
                    Element cityElem = element.select("[data-qa=vacancy-serp__vacancy-address]").first();
                    String city = cityElem.text();

                    // site name
                    String siteName = "http://hh.ua";

                    // get url
                    String url = titleElem.attr("href");

                    // setting vacancy
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCompanyName(company);
                    vacancy.setCity(city);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);

                    // adding vacancy to list
                    result.add(vacancy);
                }
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .referrer("http://hh.ua")
                .userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:49.0) Gecko/20100101 Firefox/49.0")
                .get();
    }
}