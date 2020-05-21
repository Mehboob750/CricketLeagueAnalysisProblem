package com.cricket;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CricketLeagueAnalysisTest {
    private static final String IPL_RUNS_FILE_PATH ="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String INCORRECT_FILE_TYPE_PATH ="./src/main/resources/IPL2019FactsheetMostRuns.MP4";
    private static final String IPL_WICKETS_FILE_PATH ="./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void  givenIplMostRunsCSVFile_WhenLoaded_ReturnsCorrectRecords() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            int numberOfRecords = cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            Assert.assertEquals(100,numberOfRecords);
        } catch (CricketLeagueAnalyserException e) { }
    }

    @Test
    public void givenIplMostRunsCSVFile_WithWrongFile_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketLeagueAnalyserException.class);
            cricketLeagueAnalyser.loadIPLCSVFile(WRONG_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIplMostRunsCSVFile_WithTypeIncorrect_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketLeagueAnalyserException.class);
            cricketLeagueAnalyser.loadIPLCSVFile(INCORRECT_FILE_TYPE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIplMostRunsCSVFile_withIncorrectDelimeter_shouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIplMostRunsCSVFile_withIncorrectHeader_shouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHasMaximumAverageBattingScore() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getBattingAverageWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHasMinimumAverageBattingScore() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getBattingAverageWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHasMaximumStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getStrikingRateWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHasMinimumStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getStrikingRateWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", iplRunSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHitMaximumSixes() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSixesWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHitMinimumSixes() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSixesWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Kuldeep Yadav", iplRunSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHitMaximumFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getFoursWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHitMinimumFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getFoursWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Tim Southee", iplRunSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHitMaximumSixesAndFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSixAndFoursWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHitMinimumSixesAndFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSixAndFoursWiseSorted();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Kuldeep Yadav", iplRunSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHadBestStrikingRateWithMaximumSixAndFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByStrikingRateWithMaximumSixesAndFours();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHadGreatAverageWithBestStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByAverageWithStrikingRate();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHadLowAverageWithStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByAverageWithStrikingRate();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHitMaximumRunsWithBestAverages() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByRunsWithAverages();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("David Warner", iplRunSheetCSV[iplRunSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_shouldReturnPlayerName_whoHitMinimumRunsWithAverages() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByRunsWithAverages();
            IPLRunSheetCSV[] iplRunSheetCSV = new Gson().fromJson(sortedIPLData, IPLRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_whenLoaded_shouldReturnsCorrectRecords() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            int numberOfRecords = cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            Assert.assertEquals(99,numberOfRecords);
        } catch (CricketLeagueAnalyserException e) { }
    }

    @Test
    public void givenIplMostWicketsCSVFile_withWrongFile_shouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketLeagueAnalyserException.class);
            cricketLeagueAnalyser.loadIPLCSVFile(WRONG_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_withTypeIncorrect_shouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketLeagueAnalyserException.class);
            cricketLeagueAnalyser.loadIPLCSVFile(INCORRECT_FILE_TYPE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_withIncorrectDelimeter_shouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_withIncorrectHeader_shouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnPlayerName_whoHasTopBowlingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBowlingAverage();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Mayank Markande", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnPlayerName_whoHasLowBowlingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBowlingAverage();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Umesh Yadav", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnPlayerName_whoHasTopBowlingStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBowlingStrikeRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnPlayerName_whoHasLowBowlingStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBowlingStrikeRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Suresh Raina", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnBowlerName_whoHasBestEconomyRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByEconomyRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Mayank Markande", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnBowlerName_whoHasLowEconomyRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByEconomyRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Umesh Yadav", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnBowlerName_whoHasBestStrikingRateWith5wAnd4w() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByStrikingRateWith5wand4w();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Mayank Markande", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnBowlerName_whoHaslowStrikingRateWith5wAnd4w() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByStrikingRateWith5wand4w();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Umesh Yadav", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnBowlerName_whoHasBestAveragesWithStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByAveragesWithStrikingRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnBowlerName_whoHasLowAveragesWithStrikingRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByAveragesWithStrikingRate();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Suresh Raina", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnBowlerName_whoTookMaximumWicketsWithBestBowlingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByWicketsWithBowlingAverage();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Mayank Markande", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnBowlerName_whoTookMinimumWicketsWithBestBowlingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByWicketsWithBowlingAverage();
            IPLWicketSheetCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketSheetCSV[].class);
            Assert.assertEquals("Umesh Yadav", iplWicketSheetCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnPlayer_whoHadBestBattingAndBowlingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH, IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBattingAndBowlingAverage();
            IplDAO[] iplDAO = new Gson().fromJson(sortedIPLData, IplDAO[].class);
            Assert.assertEquals("MS Dhoni", iplDAO[iplDAO.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnPlayer_whoHadLowBattingAndBowlingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH, IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByBattingAndBowlingAverage();
            IplDAO[] iplDAO = new Gson().fromJson(sortedIPLData, IplDAO[].class);
            Assert.assertEquals("Suresh Raina", iplDAO[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnPlayer_whoIsBestAllRounder() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH, IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByRunsAndWickets();
            IplDAO[] iplDAO = new Gson().fromJson(sortedIPLData, IplDAO[].class);
            Assert.assertEquals("David Warner", iplDAO[iplDAO.length-1].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_shouldReturnPlayer_whoIsWorstAllRounder() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSVFile(IPL_RUNS_FILE_PATH, IPL_WICKETS_FILE_PATH);
            String sortedIPLData=cricketLeagueAnalyser.getSortedByRunsAndWickets();
            IplDAO[] iplDAO = new Gson().fromJson(sortedIPLData, IplDAO[].class);
            Assert.assertEquals("Suresh Raina", iplDAO[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }
}
