package com.company.samplesales.screen.charts.blankscreendatabindingapi;

import io.jmix.charts.component.PieChart;
import io.jmix.ui.data.impl.MapDataItem;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("sales_BlankScreenDatabindingapi")
@UiDescriptor("blank-screen-DataBindingAPI.xml")
public class BlankScreenDatabindingapi extends Screen {
    @Autowired
    private PieChart pieChart;

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        pieChart.addData(MapDataItem.of("key", "piece of apple pie",
                "value", 70),
                MapDataItem.of("key", "piece of blueberry pie",
                        "value", 20),
                MapDataItem.of("key", "piece of cherry pie",
                        "value", 10));
    }
}