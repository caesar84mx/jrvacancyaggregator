package com.caesar_84.jrvacancyaggregator;

import com.caesar_84.jrvacancyaggregator.model.HHStrategy;
import com.caesar_84.jrvacancyaggregator.model.Model;
import com.caesar_84.jrvacancyaggregator.model.MoikrugStrategy;
import com.caesar_84.jrvacancyaggregator.model.Provider;
import com.caesar_84.jrvacancyaggregator.view.HtmlView;

/**
 * Created by caesar-84 on 11/14/16.
 */
public class Aggregator
{
    public static void main(String[] args)
    {
        Provider hhProvider = new Provider(new HHStrategy());
        Provider mkProvider = new Provider(new MoikrugStrategy());
        HtmlView view = new HtmlView();
        Model model = new Model(view, hhProvider, mkProvider);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}