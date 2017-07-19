package com.caesar_84.jrvacancyaggregator.view;

import com.caesar_84.jrvacancyaggregator.Controller;
import com.caesar_84.jrvacancyaggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by caesar-84 on 11/15/16.
 */
public class HtmlView implements View
{
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";


    @Override
    public void update(List<Vacancy> vacancies)
    {
        updateFile(getUpdatedFileContent(vacancies));

    }

    private String getUpdatedFileContent(List<Vacancy> vacancies)
    {
        String content = "";
        try
        {
            Document doc = getDocument();
            Element templateElem = doc.select(".template").first();
            Element copyTemplateElem = templateElem.clone();

            copyTemplateElem.removeClass("template");
            copyTemplateElem.removeAttr("style");

            doc.select("tr[class=vacancy]").remove();

            for (Vacancy vacancy: vacancies)
            {
                Element vacElem = copyTemplateElem.clone();
                vacElem.getElementsByClass("city").first().text(vacancy.getCity());
                vacElem.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                vacElem.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = vacElem.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                templateElem.before(vacElem.outerHtml());
            }

            content = doc.html();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            content = "Some exception occurred";
        }
        return content;
    }

    private void updateFile(String string)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            writer.write(string);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException
    {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    @Override
    public void setController(Controller controller) {this.controller = controller;}

    public void userCitySelectEmulationMethod()
    {
        controller.onCitySelect("Munich");
    }
}
