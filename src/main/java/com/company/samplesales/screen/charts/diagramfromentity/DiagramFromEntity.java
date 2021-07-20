package com.company.samplesales.screen.charts.diagramfromentity;

import com.company.samplesales.entity.CountryGrowth;
import io.jmix.charts.component.Chart;
import io.jmix.charts.component.SerialChart;
import io.jmix.core.Metadata;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.ContentMode;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UiController("sales_Diagramfromentity")
@UiDescriptor("diagramFromEntity.xml")
public class DiagramFromEntity extends Screen {
    @Autowired
    private CollectionContainer<CountryGrowth> countryGrowthDc;
    @Autowired
    private Metadata metadata;
    @Autowired
    SerialChart chart;
    @Autowired
    private Notifications notifications;

    @Subscribe
    private void onInit(InitEvent event) {
        List<CountryGrowth> items = new ArrayList<>();
        items.add(countryGrowth("USA", 3.5, 4.2));
        items.add(countryGrowth("UK", 1.7, 3.1));
        items.add(countryGrowth("Canada", 2.8, 2.9));
        items.add(countryGrowth("Japan", 2.6, 2.3));
        items.add(countryGrowth("France", 1.4, 2.1));
        items.add(countryGrowth("Brazil", 2.6, 4.9));
        items.add(countryGrowth("Russia", 6.4, 7.2));
        items.add(countryGrowth("India", 8.0, 7.1));
        items.add(countryGrowth("China", 9.9, 10.1));
        countryGrowthDc.setItems(items);
        chart.addGraphItemClickListener(graphItemClickEvent ->{
            String caption = itemClickEventInfo(graphItemClickEvent);

            notifications.create()
                    .withCaption(caption)
                    .withContentMode(ContentMode.HTML)
                    .show();
        });

    }

    private String itemClickEventInfo(Chart.GraphItemClickEvent event) {
        CountryGrowth countryGrowth = (CountryGrowth) event.getEntityNN();
        return String.format("GDP grow in %s (%s): %.1f%%",
                countryGrowth.getCountry(),
                event.getGraphId().substring(5),
                "graph2014".equals(event.getGraphId()) ? countryGrowth.getYear2014() : countryGrowth.getYear2015());
    }

    private CountryGrowth countryGrowth(String country, double year2014, double year2015) {
        CountryGrowth cg = metadata.create(CountryGrowth.class);
        cg.setCountry(country);
        cg.setYear2014(year2014);
        cg.setYear2015(year2015);
        return cg;
    }
}