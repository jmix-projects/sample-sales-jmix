package com.company.samplesales.screen.charts.blankscreenganttchart;

import com.company.samplesales.entity.Segment;
import com.company.samplesales.entity.TaskSpan;
import io.jmix.charts.component.GanttChart;
import io.jmix.core.Metadata;
import io.jmix.ui.data.impl.EntityDataItem;
import io.jmix.ui.data.impl.ListDataProvider;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;


@UiController("sales_BlankScreenGanttchart")
@UiDescriptor("blank-screen-ganttChart.xml")
public class BlankScreenGanttchart extends Screen {
    @Autowired
    private GanttChart ganttChart;

    @Autowired
    private Metadata metadata;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        ListDataProvider dataProvider = new ListDataProvider();
        dataProvider.addItem(new EntityDataItem(getTaskSpan("Cat")));
        dataProvider.addItem(new EntityDataItem(getTaskSpan("Dog")));
        dataProvider.addItem(new EntityDataItem(getTaskSpan("Pig")));

        ganttChart.setDataProvider(dataProvider);

    }

    private TaskSpan getTaskSpan(String name){
        TaskSpan span = metadata.create(TaskSpan.class);
        List<Segment> list = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            Segment segment = metadata.create(Segment.class);
            segment.setIndex(i+1);
            segment.setStart(8+2*i);
            segment.setDuration(1);
            segment.setTask("Test task #"+(i+1));
            segment.setColor("ff"+(i+5)+"0"+(i+5)+"0");
            list.add(segment);
        }
        span.setSegments(list);
        span.setCategory(name);
        return span;
    }
}