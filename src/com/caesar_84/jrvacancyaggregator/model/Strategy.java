package com.caesar_84.jrvacancyaggregator.model;

import com.caesar_84.jrvacancyaggregator.vo.Vacancy;

import java.util.List;

/**
 * Created by caesar-84 on 11/14/16.
 */
public interface Strategy
{
    List<Vacancy> getVacancies(String searchString);
}
