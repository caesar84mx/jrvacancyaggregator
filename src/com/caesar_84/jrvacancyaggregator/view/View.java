package com.caesar_84.jrvacancyaggregator.view;

import com.caesar_84.jrvacancyaggregator.Controller;
import com.caesar_84.jrvacancyaggregator.vo.Vacancy;

import java.util.List;

/**
 * Created by caesar-84 on 11/15/16.
 */
public interface View
{
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
