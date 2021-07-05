package com.example.smileproject;


import smile.data.DataFrame;
import smile.data.measure.NominalScale;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


public class PreProcessingDF {

    static public Map<String , Long> getCount(DataFrame df , String col) {

        Map<String , Long> output = new HashMap<>();

        String[] values = df.stringVector(col).distinct().toArray(new String[]{});

        int[] pclassValues = df.stringVector(col).factorize(new NominalScale(values)  ).toIntArray();

        List<Integer> lst = new ArrayList<Integer>();
        for (int i = 0; i < pclassValues.length; i++) {
            lst.add(pclassValues[i]);
        }
        Map<Integer, Long> result = lst.stream().collect(Collectors.groupingBy(integer -> integer, Collectors.counting()));


        List<Integer> keys = result.keySet().stream().collect(Collectors.toList());
        List<Long> val     = result.values().stream().collect(Collectors.toList());

        List<String> loc = new ArrayList<String>();
        for (int i = 0; i < keys.size(); i++) {
            loc.add(values[keys.get(i)]);
        }

        for (int i=0 ; i<loc.size() ;i ++ ){
            output.put(loc.get(i) , val.get(i));
        }

        Map<String, Long> res = new HashMap<>() ;
        res = output.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));


        return res;
    }


    static public Map<String, Integer> splitColumn(DataFrame df) {
        List<String> column_splitted = new ArrayList<>();

        df.stringVector("Skills").stream().forEach(x -> {
            String[] temp = x.split(",");
            for (String s : temp){
                column_splitted.add(s.trim());
            }
        });
        Map<String, Integer> count = new HashMap<>();
        for(String s : column_splitted){
            if(!count.containsKey(s))
                count.put(s, 1);
            else
                count.put(s, count.get(s)+1);
        }
        count.forEach((k, v) -> System.out.println(k + ": " + v));

        column_splitted.stream().forEach(System.out::println);

        Map<String, Integer> sorted_count = new HashMap<>() ;
        sorted_count= count.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));

        return sorted_count;
        }
    }

