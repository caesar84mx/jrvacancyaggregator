package com.caesar_84.jrvacancyaggregator.model;

import com.caesar_84.jrvacancyaggregator.vo.Vacancy;

import java.util.List;

/**
 * Created by caesar-84 on 11/14/16.
 */
public class Provider
{
    private Strategy strategy;

    public Provider(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString)
    {
        return strategy.getVacancies(searchString);
    }
}
