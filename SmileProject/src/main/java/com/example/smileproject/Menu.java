package com.example.smileproject;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.data.DataFrame;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@RestController
public class Menu {

    DataFrame df = null;

    @RequestMapping("/")
    public String mainPage() throws IOException, URISyntaxException {
        //call function to get data set
        df = DAO.readCSV("src/main/resources/Wuzzuf_Jobs.csv");

        return "<p>Wuzzaf Dataset project \n</p>"+
                "<p>MENU:\n</p>"+
                "<p> <a href=\"/show \">1. Read data</a></p>"+
                "<p> <a href=\"/strcture\">2. Display structure</a></p>"+
                "<p> <a href=\"/summary\">3. Display summary</a></p>"+
                "<p> <a href=\"/jobsCount\">4. Count the jobs for each company (Pie chart)</a></p>"+
                "<p> <a href=\"/jobTitle\">5. Most popular job title (Bar chart)</a></p>"+
                "<p> <a href=\"/area\">6. Most popular area (Bar chart)</a></p>"+
                "<p> <a href=\"/skills\">7.Most popular skills list</a>";
    }

    @RequestMapping("/show")
    public String showData()  {
        return WebView.sendDataString(df.toString());
    }

    @RequestMapping("/strcture")
    public String showStructure()  {
        return WebView.sendDataString(df.structure().toString());
    }

    @RequestMapping("/summary")
    public String showSummary()  {
        return WebView.sendDataString(df.summary().toString());

    }

    @RequestMapping("/jobsCount")
    public String showJobCount() throws IOException, URISyntaxException {
        Map<String , Long> temp = PreProcessingDF.getCount(df , "Company");
        PieChart chart = new ChartMaker().pieChart(temp , 5);
        return WebView.sendChartString(chart) + "RELOAD TO CHANGE COLOR";
    }

    @RequestMapping("/jobTitle")
    public String showTitles() throws IOException {
        Map<String , Long> temp = PreProcessingDF.getCount(df , "Title");
        CategoryChart chart = new ChartMaker().categoryChart(temp , 5, "Job");
        return WebView.sendChartString(chart);
    }

    @RequestMapping("/area")
    public String showAreas() throws IOException {
        Map<String , Long> temp = PreProcessingDF.getCount(df , "Country");
        CategoryChart chart = new ChartMaker().categoryChart(temp , 5, "Country");
        return WebView.sendChartString(chart);
    }

    @RequestMapping("/skills")
    public String showSkills()  {
        String output = "";
        Map<String, Integer> temp = PreProcessingDF.splitColumn(df);

        List keys = temp.keySet().stream().collect(Collectors.toList());

        for(int i=0 ; i< temp.size() ; i++){
            output += keys.get(i).toString() +":\t "+ temp.get(keys.get(i)).toString();
            output += "\n";
        }
        return WebView.sendDataString(output);
    }


}