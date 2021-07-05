package com.example.smileproject;

import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;
import smile.io.Read;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class DAO {

    public static DataFrame readCSV(String source) throws IOException, URISyntaxException {
            DataFrame df = null;
            CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
            df = Read.csv(source, format);

            df = df.omitNullRows();
            DataFrame dfNoDuplicates = DataFrame.of(df.stream().distinct());
            //System.out.println(df.nrows());
            //System.out.println(newdf.nrows());
            return dfNoDuplicates;
    }



}
