package com.manio.tables;

import java.util.Scanner;
import java.io.*;
import java.lang.NullPointerException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.lang.ArrayIndexOutOfBoundsException;

public class TableUtilities {

    private Scanner sc = new Scanner(System.in);
    
    //check if the data is a decimal
    public static boolean isNumeric(String str) {  
        if ("".equals(str) || str == null) {
            return false;   
        }
        else {
            return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal. Must start with 0 if only decimal eg 0.1
        }
    }

    //check if the data is int
    public static boolean isInt(String str) {
        if ("".equals(str) || str == null) {
            return false;   
        }
        else {
            return str.matches("\\d*");  //match an int
        }
    }

    public static void printMenu() {
        
        //prints the menu
        System.out.println();    
        System.out.println(TableConstant.MENU_TITLE);
        System.out.println(TableConstant.MENU5);
        System.out.println(TableConstant.MENU6);
        System.out.println(TableConstant.MENU7);
        System.out.println(TableConstant.MENU8);
        System.out.println(TableConstant.MENU9);
        System.out.println(TableConstant.MENU10);
        System.out.println(TableConstant.MENU11);
        System.out.println(TableConstant.MENU0);

    }
}