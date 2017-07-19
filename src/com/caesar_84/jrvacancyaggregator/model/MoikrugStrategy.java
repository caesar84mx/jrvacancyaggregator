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
 * Created by caesar-84 on 11/16/16.
 */
public class MoikrugStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://moikrug.ru/vacancies?page=%d&q=java+%s";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> result = new ArrayList<>();
        try
        {
            int page = 1;
            Document doc;
            while (true)
            {
                doc = getDocument(searchString, page++);
                if (doc == null) break;
                Elements vacancyElements = doc.getElementsByClass("job");
                if (vacancyElements.isEmpty()) break;

                for (Element vacElement: vacancyElements)
                {
                    Vacancy vacancy = new Vacancy();

                    // get title
                    String title = vacElement.getElementsByClass("info").first().getElementsByAttribute("title").text();

                    // get company
                    String company = vacElement.getElementsByClass("company_name").first().getElementsByTag("a").text();

                    // get salary
                    Elements salaryElem = vacElement.getElementsByClass("salary").first().getElementsByAttributeValue("title", "Зарплата");
                    String salary = "";
                    if (salaryElem != null) salary = salaryElem.text();

                    // get city
                    Elements cityElem = vacElement.getElementsByClass("location");
                    String city = "";
                    if (cityElem != null) city = cityElem.text();

                    // get site
                    String siteName = doc.title();

                    // get URL
                    String url = "http://moikrug.ru" + vacElement.getElementsByClass("title").first().getElementsByTag("a").first().attr("href");

                    // setting vacancy
                    vacancy.setTitle(title);
                    vacancy.setCompanyName(company);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);

                    result.add(vacancy);
                }

            }
        }
        catch (IOException ex){ex.printStackTrace();}

        return result;
    }

    public Document getDocument(String searchString, int page) throws IOException
    {
        return Jsoup.connect(String.format(URL_FORMAT, page, searchString)).get();
    }
}
