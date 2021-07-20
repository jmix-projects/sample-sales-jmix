package com.company.samplesales.screen.clockwidget;

import com.company.samplesales.entity.User;
import io.jmix.core.TimeSource;
import io.jmix.dashboardsui.annotation.DashboardWidget;
import io.jmix.dashboardsui.annotation.WidgetParam;
import io.jmix.ui.WindowParam;
import io.jmix.ui.component.Label;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;

@UiController("sales_ClockWidget")
@UiDescriptor("clock-widget.xml")
@DashboardWidget(name = ClockWidget.CAPTION)
public class ClockWidget extends ScreenFragment {

    public static final String CAPTION = "Clock";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Named("clockField")
    protected Label clockField;

    @Inject
    private TimeSource timeSource;

    @WidgetParam
    @WindowParam
    private User user;

    @Autowired
    private InstanceContainer<User> userDc;

    @Subscribe
    public void onBeforeShow(AttachEvent attachEvent) throws ClassNotFoundException {
        updateTime();
        userDc.setItem(user);
    }

    public void updateTime() {
        clockField.setValue(DATE_FORMAT.format(timeSource.currentTimestamp()));
    }

    public Label getClockField() {
        return clockField;
    }
}