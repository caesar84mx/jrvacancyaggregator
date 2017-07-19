package com.caesar_84.jrvacancyaggregator;

import com.caesar_84.jrvacancyaggregator.model.Model;

/**
 * Created by caesar-84 on 11/14/16.
 */
public class Controller
{
    private Model model;

    public Controller(Model model) throws IllegalArgumentException
    {
        if (model == null) throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName)
    {
        model.selectCity(cityName);
    }
}
