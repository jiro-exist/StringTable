package com.manio.tables;

import java.util.Scanner;
import java.util.Random;
import java.lang.StringBuilder;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class TableOptions{




    /************************************************
    * every function below is used for exercise 2  *
    ************************************************/

    //option 1
    public boolean readInputFile(String filename, TableModel tableModel) {

        TableService tableService = new TableService();

        Map<String,List<Config>> mapTable = new LinkedHashMap<String,List<Config>>();

        File inputFile = new File(filename);

        int lineCounter = 0;

        //check if file exists, else ask for a randomly generated string on the file
        if (inputFile.exists() && !inputFile.isDirectory()) {

            //trying out the try with resource
            try(FileReader inputFileReader = new FileReader(inputFile);
                BufferedReader inputFileBuffer = new BufferedReader(inputFileReader)  ) {

                for(String s; ( s = inputFileBuffer.readLine() ) != null; ) {
                    //call splitData for the Map value
                    List<Config> holder = tableService.splitData(s,lineCounter);
                    if (!holder.isEmpty()) {
                        mapTable.put(""+lineCounter++, holder);
                    }
                }
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
        else {
            //handle create random on main
            return false;
        }

        tableModel.setCurrFile(filename);
        tableModel.setData(mapTable);

        return showData(tableModel);
    }

    //option2
    public boolean writeRandom(String filename, TableModel tableModel, int xSize, int ySize, int cellSize) throws FileNotFoundException, IOException{

        TableService tableService = new TableService();

        File file = new File(filename);

        PrintWriter pw = new PrintWriter(file);
            pw.print(tableService.createRandom(xSize, ySize, cellSize).toString());
            pw.flush();
            if (pw != null) {
                pw.close();
            }

        return readInputFile(filename, tableModel);
    }

    //option 3
    public boolean showData(TableModel tableModel) {

        Map<String,List<Config>> mapTable = tableModel.getData();
        StringBuilder mapData = new StringBuilder();

        if (!mapTable.isEmpty()) {
            System.out.println();
            System.out.println("Displaying contents of Map");
            for(String key : mapTable.keySet()) {
                mapData.append("{ ");
                for(Config config: mapTable.get(key)) {
                    // mapData.append("[" + config.getKey() + TableConstant.KV_DELIMITER + config.getValue() + "] ");
                    mapData.append(config.toString());
                }
                mapData.append("}\n");
            }
            System.out.println(mapData);

            return true;
        }
        else {
            System.out.println(TableConstant.EMPTY_MAP);
            return false;
        }
    }

    //option 4
    public boolean searchString(TableModel tableModel, String findStr) {

        TableService tableService = new TableService();

        Map<String,List<Config>> mapTable = tableModel.getData();

        if (!mapTable.isEmpty()) {
            if(findStr.length()==0){
                System.out.println("Search string is empty");
                return false;
            }

            for(String key : mapTable.keySet()) {
                for(Config config : mapTable.get(key)) {    //get the Config class
                    config.setKResult(tableService.checkSubstring(config.getKey(), findStr)); // save the results in the Config class
                    config.setVResult(tableService.checkSubstring(config.getValue(), findStr)); // save the results in the Config class

                    if (config.getResult() > 0) {
                        System.out.println(config.searchResult());
                    }
                }
            }

            System.out.println("Search Finished.");
            return true;
        }
        else {
            System.out.println(TableConstant.EMPTY_MAP);
            return false;
        }
    }

    //option 5
    public boolean editString(TableModel tableModel, int xSize, int ySize, String updateValue, String change) {

        TableService tableService = new TableService();

        Map<String,List<Config>> mapTable = tableModel.getData();   //main data holder
        List<Config> listHold = new ArrayList<Config>();    //holder for the Map value

        int counter = 0; //counter for the index of the List
        int lineCounter = 0; //counter for the index of the Map
        int changes = 0; //counter for the changes

        if (!mapTable.isEmpty()) {

            for(String key : mapTable.keySet()) {           //get the keys in the map
                for(Config config : mapTable.get(key)) {    //get the Config class
                    listHold = mapTable.get(key);           //put it in a holder
                    if ( config.getX() == xSize && config.getY() == ySize ) {    //find if the index matches
                        if ("k".equals(change)) {
                            config.setKey(updateValue);       //set the new value of Config
                        }
                        else if ("v".equals(change)) {
                            config.setValue(updateValue);       //set the new value of Config
                        }
                        listHold.set(counter,config);       //replace the Config in the holder
                        changes++;
                    }
                    counter++;
                }
                counter = 0;
            }

            System.out.println();
            System.out.println("Number of Replaced Cells:" + changes);
            showData(tableModel);
            return true;
        }
        else {
            System.out.println(TableConstant.EMPTY_MAP);
            return false;
        }

    }

    //option 6
    public boolean dataSort(String sortOrder, TableModel tableModel) {

        Map<String,List<Config>> mapTable = tableModel.getData();
        List<Config> listHolder = null;
        if (!mapTable.isEmpty()) {

            for(Map.Entry<String, List<Config>> entry : mapTable.entrySet()) {

                listHolder = entry.getValue();
                if ("a".equals(sortOrder.toLowerCase())) {
                    System.out.println("Sorting in ascending order.");
                    Collections.sort(listHolder);
                }
                else if ("d".equals(sortOrder.toLowerCase())) {
                    System.out.println("Sorting in descending order.");
                    Collections.sort(listHolder,Collections.reverseOrder());
                }
                else {
                    System.out.println("Option not found, using default.");
                    System.out.println("Sorting in ascending order.");
                    Collections.sort(listHolder);
                }
                showData(tableModel);
            }
            return true;
        }
        else {
            System.out.println(TableConstant.EMPTY_MAP);
            return false;
        }

    }

    //option 7
    public boolean fileSave(TableModel tableModel) {

        Map<String,List<Config>> mapTable = tableModel.getData();
        StringBuilder mapData = new StringBuilder();

        String filename = tableModel.getCurrFile();

        if (filename == null || "".equals(filename)) {
            System.out.println("No file chosen");
            return false;
        }
        
        File file = new File(filename);
        PrintWriter pw = null;
        try {

            if (!mapTable.isEmpty()) {

                for(String key : mapTable.keySet()) {
                    for(Config config: mapTable.get(key)) {
                        mapData.append(config.getKey() + TableConstant.KV_DELIMITER + config.getValue() + TableConstant.CELL_DELIMITER);
                    }
                    mapData.append("\n");
                }

                pw = new PrintWriter(file);
                pw.print(mapData);
                pw.flush();
                return true;

            }
            else {
                System.out.println(TableConstant.EMPTY_MAP);
            }

        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
        finally {
            if (pw != null) {
                pw.close();
            }
        }

        return false;
    }

    /************************************************
    * end of the hashmap functions                  *
    ************************************************/

}