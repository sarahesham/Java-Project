package com.example.smileproject;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ChartMaker {
    public CategoryChart categoryChart(Map<String, Long> chartMap, Integer lim, String xTitle) throws IOException {
        List<String> keys = chartMap.keySet().stream().collect(Collectors.toList());
        List<Long> val    = chartMap.values().stream().collect(Collectors.toList());

        CategoryChart chart = new CategoryChartBuilder().width(720).height(768).title("histogram ").xAxisTitle(xTitle).yAxisTitle("count").theme(Styler.ChartTheme.GGPlot2).build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setXAxisLabelRotation(45);
        chart.addSeries("count of " + xTitle, keys.stream().limit(lim).collect(Collectors.toList()), val.subList(0,lim));
        return  chart;
    }


     public PieChart pieChart(Map<String, Long> chartMap, Integer lim) throws IOException {
        List<String> keys = chartMap.keySet().stream().collect(Collectors.toList());
        List<Long> val    = chartMap.values().stream().collect(Collectors.toList());

        List<Color> sliceColors = new ArrayList<Color>();
        Random objGenerator = new Random();
//        long seed =20;
//        objGenerator.setSeed(seed);

        PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass ().getSimpleName ()).build ();

        // adding chart colors
        for (int j = 0; j<lim ; j++){
            sliceColors.add(new Color(objGenerator.nextInt(255), objGenerator.nextInt(255), objGenerator.nextInt(255)));
        }

        // converting sliceColors to array
        chart.getStyler ().setSeriesColors (sliceColors.toArray(new Color[sliceColors.size()]));

        // Adding chart series
        for (int i = 0; i<lim ; i++){
            chart.addSeries (keys.get(i), val.get (i));
        }
        return chart;
    }
}
