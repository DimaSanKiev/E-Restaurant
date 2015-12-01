package com.bionic.edu.bean;

import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportTotal;
import org.primefaces.model.chart.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@Scope("session")
public class ChartViewBean implements Serializable {
    private static final long serialVersionUID = 7288379413976890050L;

    private PieChartModel pieModel;
    private LineChartModel lineModel;
    @Inject
    private ReportBean reportBean;

    @PostConstruct
    public void init() {
        createPieModel();
        createLineModel();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void createPieModel() {
        pieModel = new PieChartModel();
        reportBean.refreshCategoryReport();
        for (ReportCategory category : reportBean.getReportCategories()) {
            pieModel.set(category.getDishCategoryName(), category.getTotal());
        }
        pieModel.setTitle("Categories Diagram");
        pieModel.setLegendPosition("e");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
    }


    // todo - try DateTimeFormatter
    private void createLineModel() {
        lineModel = new LineChartModel();
        LineChartSeries series = new LineChartSeries();
//        reportBean.refreshTotalReport();
        for (ReportTotal reportTotal : reportBean.getReportTotals()) {
            series.set(reportTotal.getDate().toString(), reportTotal.getCount());
            System.out.println(reportTotal);
        }
//        series.set("2014-01-12", 65);
//        series.set("2014-01-18", 74);
//        series.set("2014-01-24", 24);
//        series.set("2014-01-30", 51);
        lineModel.addSeries(series);
        lineModel.setTitle("Orders total");
        lineModel.setAnimate(true);
        lineModel.getAxis(AxisType.Y).setLabel("Values");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickFormat("%b %#d, %y");
        axis.setMax("2016-12-31");
        lineModel.getAxes().put(AxisType.X, axis);
    }
}