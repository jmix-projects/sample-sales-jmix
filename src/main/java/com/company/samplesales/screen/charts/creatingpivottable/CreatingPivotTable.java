package com.company.samplesales.screen.charts.creatingpivottable;

import com.company.samplesales.entity.TipInfo;
import io.jmix.core.Metadata;
import io.jmix.pivottable.component.PivotTable;
import io.jmix.pivottable.component.PivotTableExtension;
import io.jmix.pivottable.component.impl.PivotExcelExporter;
import io.jmix.pivottable.component.impl.PivotTableExtensionImpl;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@UiController("sales_Creatingpivottable")
@UiDescriptor("creatingPivotTable.xml")
public class CreatingPivotTable extends Screen {

    @Autowired
    private Metadata metadata;
    @Autowired
    private CollectionContainer<TipInfo> tipsDc;
    @Autowired
    private PivotTable pivotTable;
    @Autowired
    protected ObjectProvider<PivotExcelExporter> excelExporterObjectProvider;
    private PivotTableExtension extension;
    @Subscribe
    protected void onInit(InitEvent event) {
        extension = new PivotTableExtensionImpl(pivotTable, excelExporterObjectProvider.getObject());
    }
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        List<TipInfo> tips = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            String sex = i % 3 == 0 ? "male" : "female";
            Boolean smoker = i%2 == 0;
            String time = i%2 == 0 ? "dinner" : "lunch";
            TipInfo tip = generateTip(new BigDecimal(i+2), new BigDecimal(i+3), sex, smoker, days[i], time, i+3);
            tips.add(tip);
        }
        tipsDc.setItems(tips);
    }
    private TipInfo generateTip(BigDecimal totalBill, BigDecimal tip, String sex,
                                Boolean smoker, String day, String time, Integer size){
        TipInfo tipInfo = metadata.create(TipInfo.class);
        tipInfo.setTip(tip);
        tipInfo.setTotalBill(totalBill);
        tipInfo.setSex(sex);
        tipInfo.setSmoker(smoker);
        tipInfo.setDay(day);
        tipInfo.setTime(time);
        tipInfo.setSize(size);
        return tipInfo;
    }
    @Subscribe("xlsExport")
    public void onXlsExportClick(Button.ClickEvent event) {
        extension.exportTableToXls();
    }
}