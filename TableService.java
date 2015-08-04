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


public class TableService {	

    public static StringBuilder createRandom(int xSize, int ySize, int cellSize) {

        StringBuilder stringFile = new StringBuilder();

        //randomizer
        Random rnd = new Random();

        //stringbuilder for the random string
        StringBuilder cellData = new StringBuilder(cellSize);

        boolean kvSeperate  = true;

        //create the random data
        for(int a = 0; a < xSize; a++) {
            kvSeperate = true;
            for(int b = 0; b < ySize * 2; b++) {
                for(int c = 0; c < cellSize; c++) {
                    cellData.append(TableConstant.RANDOM_CHAR[rnd.nextInt(TableConstant.RANDOM_CHAR.length)]);
                }//for cellSize
                stringFile.append(cellData.toString());
                cellData.delete(0,cellSize);
                if (kvSeperate) {
                    stringFile.append(TableConstant.KV_DELIMITER);
                    kvSeperate = false;
                }
                else {
                    stringFile.append(TableConstant.CELL_DELIMITER);
                    kvSeperate = true;
                }
            }//for ySize
            stringFile.append("\n");
        }//for xSize

        return stringFile;
    }

    public static List<Config> splitData(String lineData, int lineCounter) {

        List<Config> linkHold = new ArrayList<Config>();
        int cellCounter = 0;

        for(String cellSplit : lineData.split(TableConstant.CELL_DELIMITER)) { //split per cell
            String[] kvSplit = cellSplit.split(TableConstant.KV_DELIMITER); 
            linkHold.add(new Config(kvSplit[0], kvSplit[1], lineCounter, cellCounter++)); //put the key-value to the List with their index
        }

        return linkHold; //return the list to be added on the Map

    }
    //arguments: mainStr = cell string, findStr = entered string to find
    public static int checkSubstring(String mainStr, String findStr) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for(int a = 0; a <= mainStr.length() - findStr.length(); a++) { // string loop
            //checkSubstring finds if find_string is in the cell
            if ( findStr.equals( mainStr.substring(a, a + findStr.length()) ) ) {
                counter++;
            }
        }
        return counter;
    }

    //checking if the user exited the menu
    public static boolean isDone(String chosen) {
        return !TableConstant.EXIT_OPTION.equals(chosen);
    }

}