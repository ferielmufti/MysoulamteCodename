/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Interest;
import iServices.ServiceMatching;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fatma
 */
public class StatistiquesPref {

    int id=61;
    ServiceMatching sm= new ServiceMatching();
Interest interest= new Interest();
private Resources theme;
     /**
     * Creates a renderer for the specified colors.
     */
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        String[] categories= new String[]{interest.getInterest1(), interest.getInterest2(), interest.getInterest3(), interest.getInterest4(), interest.getInterest5()};
       for(int i=0; i<values.length;i++)
       {
           series.add(categories[i], values[i]);
       }

        return series;
    }

    public Form createPieChartForm() {
        theme = UIManager.initFirstTheme("/theme_1");
interest=sm.getInterestsPref(id);
double sum=interest.getPourcent1()+interest.getPourcent2()+interest.getPourcent3()+interest.getPourcent4()+interest.getPourcent5();
        // Generate the values
        double[] values = new double[]{(interest.getPourcent1()*100)/sum, (interest.getPourcent2()*100)/sum, (interest.getPourcent3()*100)/sum, (interest.getPourcent4()*100)/sum, (interest.getPourcent5()*100)/sum};

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
       
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Vos préférences", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("Vos préférences");
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, c);
        return f;
    }
}
