package com.company.samplesales.screen.charts.chartsincrementalupdating;

import com.company.samplesales.entity.ChartsOrder;

import io.jmix.core.Metadata;
import io.jmix.core.TimeSource;
import io.jmix.ui.component.Timer;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


@UiController("sales_Chartsincrementalupdating")
@UiDescriptor("ChartsIncrementalUpdating.xml")
public class ChartsIncrementalUpdating extends Screen {
    @Autowired
    private Metadata metadata;
    @Autowired
    private CollectionContainer<ChartsOrder> ordersDc;
    @Autowired
    private TimeSource timeSource;

    private final Random random = new Random(42);
    private final Queue<ChartsOrder> itemsQueue = new LinkedList<>();


    @Subscribe
    private void onInit(Screen.InitEvent event) {
        ChartsOrder initialValue = metadata.create(ChartsOrder.class);
        initialValue.setAmount(new BigDecimal(random.nextInt(1000) + 100));
        initialValue.setDate(timeSource.currentTimestamp());

        ordersDc.getMutableItems().add(initialValue);

    }

    @Subscribe("updateChartTimer")
    public void onUpdateChartTimerTimerAction(Timer.TimerActionEvent event) {
        ChartsOrder orderHistory = metadata.create(ChartsOrder.class);
        orderHistory.setAmount(new BigDecimal(random.nextInt(1000) + 100));
        orderHistory.setDate(timeSource.currentTimestamp());;
        ordersDc.getMutableItems().add(orderHistory);

        itemsQueue.add(orderHistory);

        if (itemsQueue.size() > 10) {
            ChartsOrder item = itemsQueue.poll();
            ordersDc.getMutableItems().remove(item);
        }
        getScreenData().loadAll();
    }
}