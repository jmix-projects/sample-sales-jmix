package com.company.samplesales.app;

import com.company.samplesales.clockwidget.ClockWidget;
import io.jmix.dashboardsui.component.Dashboard;
import io.jmix.dashboardsui.dashboard.assistant.DashboardViewAssistant;
import io.jmix.dashboardsui.event.DashboardUpdatedEvent;
import io.jmix.ui.screen.ScreenFragment;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("sales_ClockAssistant")
@Scope("prototype")
public class ClockAssistant implements DashboardViewAssistant {

    private Dashboard dashboard;

    @Override
    public void init(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @EventListener
    public void dashboardEventListener(DashboardUpdatedEvent event) {
        ScreenFragment wb = dashboard.getWidget("Clock");
        if (wb instanceof ClockWidget) {
            ClockWidget clockWidget = (ClockWidget) wb;
            clockWidget.updateTime();
        }
    }
}