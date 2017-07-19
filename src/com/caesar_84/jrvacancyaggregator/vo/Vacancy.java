package com.caesar_84.jrvacancyaggregator.vo;

/**
 * Created by caesar-84 on 11/14/16.
 */
public class Vacancy
{

    private String title, salary, city, companyName, siteName, url;

    // getters

    public String getTitle()
    {
        return title;
    }

    public String getSalary()
    {
        return salary;
    }

    public String getCity()
    {
        return city;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public String getSiteName()
    {
        return siteName;
    }

    public String getUrl()
    {
        return url;
    }

    // setters

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    // overrides

    @Override
    public int hashCode()
    {
        int result = 0;
        char[] urlArr = url.toCharArray();

        for (int i = 0; i < urlArr.length; i++)
        {
            result += urlArr[i];
        }

        char[] salaryArr = salary.toCharArray();
        int intermed = 0;
        for (int i = 0; i < salaryArr.length; i++)
        {
            intermed += salaryArr[i];
        }

        result *= 32 * intermed;

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        Vacancy other = (Vacancy) obj;
        if (this.title.equals(other.title))
            if (this.salary.equals((other.salary)))
                if (this.city.equals(other.city))
                    if (this.companyName.equals(other.companyName))
                        if (this.siteName.equals(other.siteName))
                            if (this.url.equals(other.url))
                                return true;

        return false;
    }
}
