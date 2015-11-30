package com.bionic.edu.bean;

import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportTotal;
import org.primefaces.model.chart.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

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

    private void createLineModel() {
        lineModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        List<ReportTotal> reportTotals = reportBean.getReportTotals();
        DateAxis axis = new DateAxis("Date");
        for (ReportTotal reportTotal : reportTotals) {
            series1.set(reportTotal.getDate(), reportTotal.getTotal());
        }
        lineModel.addSeries(series1);
        lineModel.setTitle("Orders total");
        lineModel.setAnimate(true);
        axis.setTickFormat("%b %#d, %y");
        lineModel.getAxes().put(AxisType.X, axis);
    }
}
