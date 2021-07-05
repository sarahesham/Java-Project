package com.example.smileproject;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;

import java.io.IOException;
import java.util.Base64;

public class WebView {

    public static String sendDataString(String data){
        return  "<textarea " +
                "outline       = \"none\" " +
                "border-style  = \"none\" " +
                "border-color  = \"Transparent\" " +
                "wrap          = \"off\" " +
                "readonly      = \"\" " +
                "rows          = \"20\" " +
                "cols          = \"150\" " +
                "name          = \"description\">\n" +
                data +
                "</textarea><br>";
    }

    public static String sendChartString(CategoryChart chart) throws IOException {
        String bytes = null;
        byte[] image_bytes = BitmapEncoder.getBitmapBytes(chart , BitmapEncoder.BitmapFormat.PNG);
        bytes = Base64.getEncoder().encodeToString(image_bytes);
        return "<img src=\"data:image/png;base64,"+bytes+"\" />";
    }

    public static String sendChartString(PieChart chart) throws IOException {
        String bytes = null;
        byte[] image_bytes = BitmapEncoder.getBitmapBytes(chart , BitmapEncoder.BitmapFormat.PNG);
        bytes = Base64.getEncoder().encodeToString(image_bytes);
        return "<img src=\"data:image/png;base64,"+bytes+"\" />";
    }


}
