package com.cricket;

import censusanalyser.CSVBuilderException;
import censusanalyser.CSVBuilderFactory;
import censusanalyser.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
   public Map<String, IplRunSheetDAO> iplRunSheetMap =null;
    public int loadIPLCSVFile(String csvFilePath) throws CricketLeagueAnalyserException {
        iplRunSheetMap = new HashMap<String, IplRunSheetDAO>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunsheetCSV> csvIterator = icsvBuilder.getCSVFileIterator(reader, IPLRunsheetCSV.class);
            Iterable<IPLRunsheetCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplRunsheetCSV -> iplRunSheetMap.put(iplRunsheetCSV.player, new IplRunSheetDAO(iplRunsheetCSV)));
            return iplRunSheetMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

    public String getBattingAverageWiseSorted() throws CricketLeagueAnalyserException {
        if(iplRunSheetMap==null || iplRunSheetMap.size()==0) {
            throw new CricketLeagueAnalyserException("Data Not Found",CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }
        Comparator<IplRunSheetDAO> iplCSVComparator =Comparator.comparing(average->average.average);
        List sortedData=iplRunSheetMap.values()
                                      .stream()
                                      .sorted(iplCSVComparator)
                                      .collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }

    public String getStrikingRateWiseSorted() throws CricketLeagueAnalyserException {
        if(iplRunSheetMap==null || iplRunSheetMap.size()==0) {
            throw new CricketLeagueAnalyserException("Data Not Found",CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }
        Comparator<IplRunSheetDAO> iplCSVComparator =Comparator.comparing(average->average.strikeRate);
        List sortedData=iplRunSheetMap.values()
                .stream()
                .sorted(iplCSVComparator)
                .collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }
}

