package com.caesar_84.jrvacancyaggregator.model;

import com.caesar_84.jrvacancyaggregator.view.View;
import com.caesar_84.jrvacancyaggregator.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caesar-84 on 11/15/16.
 */
public class Model
{
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) throws IllegalArgumentException
    {
        if (view == null || providers == null || providers.length == 0) throw new IllegalArgumentException("Illegal arguments");
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city)
    {
        List<Vacancy> vacancies = new ArrayList<Vacancy>();
        for (Provider provider: providers)
        {
            vacancies.addAll(provider.getJavaVacancies(city));
        }
        view.update(vacancies);
    }
}
