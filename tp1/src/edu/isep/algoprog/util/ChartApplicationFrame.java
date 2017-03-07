package edu.isep.algoprog.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

import java.awt.*;
import java.util.HashMap;

public class ChartApplicationFrame extends ApplicationFrame {
    private XYSeriesCollection dataset;
    private String chartTitle = "";

    public ChartApplicationFrame(String applicationTitle) {
        super(applicationTitle);
        this.dataset = new XYSeriesCollection();
    }

    public void drawGraph() {
        JFreeChart xylineChart = ChartFactory.createScatterPlot(
                chartTitle,
                "Input length",
                "Execution time (ns)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);

        Shape cross = ShapeUtilities.createDiagonalCross(3, 1);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();
        XYItemRenderer renderer = plot.getRenderer();

        renderer.setSeriesShape(0, cross);
        renderer.setSeriesPaint(0, Color.RED);

        plot.setDomainCrosshairVisible(false);
        plot.setRangeCrosshairVisible(false);

        setContentPane(chartPanel);

        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

    public void drawHashmap(HashMap values, String seriesName) {
        XYSeries XYvalues = new XYSeries(seriesName);

        if (values != null) {
            for (Object key : values.keySet()) {
                XYvalues.add((long) key, (long) values.get(key));
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(XYvalues);
        this.dataset = dataset;

        this.drawGraph();
    }

    public void addHashmapToDataset(HashMap hashMap, String seriesName) {
        if (hashMap == null) return;
        XYSeries XYvalues = new XYSeries(seriesName);

        for (Object key : hashMap.keySet()) {
            XYvalues.add((long) key, (long) hashMap.get(key));
        }
        dataset.addSeries(XYvalues);
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }
}
