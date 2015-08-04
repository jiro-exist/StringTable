package com.manio.tables;

import java.io.*;
import java.util.Scanner;

public class TableMain{

    public static Scanner sc;

    public static void readFileOption(TableOptions tableOptions, TableModel tableModel) {
        System.out.println();
        System.out.println(TableConstant.PATH_FILE);
        String holder = sc.nextLine();
        if ( tableOptions.readInputFile(holder, tableModel) ) {
            System.out.println("File successfully read.");
        }
        else {
            //pending on file not exists
            System.out.println("File does not exist or is empty.");
            System.out.println("Do you want to create the file with a randomized data? (Y/N)");
            holder = sc.nextLine();
            if (holder.toLowerCase().equals("y")) {
                writeRandomOption(tableOptions, tableModel);
            }
        }
    }

    public static void writeRandomOption(TableOptions tableOptions, TableModel tableModel) {

        try{
            System.out.println(TableConstant.PATH_FILE);
            String filename = sc.nextLine();

            System.out.println("Please enter the size of the map.");

            String holder = "";

            System.out.println(TableConstant.X_VALUE);
            while(!TableUtilities.isInt(holder)) {
                holder = sc.nextLine();
                if(!TableUtilities.isInt(holder)) {
                    System.out.println("Please enter a number");
                }
                else {
                    if(Integer.parseInt(holder) < 1) {
                        System.out.println("Please enter an int greater than 0");
                        holder = "";
                    }
                }
            }
            int xSize = Integer.parseInt(holder);

            holder = "";

            System.out.println(TableConstant.Y_VALUE);
            while(!TableUtilities.isInt(holder)) {
                holder = sc.nextLine();
                if(!TableUtilities.isInt(holder)) {
                    System.out.println("Please enter a number");
                }
                else {
                    if(Integer.parseInt(holder) < 1) {
                        System.out.println("Please enter an int greater than 0");
                        holder = "";
                    }
                }
            }
            int ySize = Integer.parseInt(holder);

            holder = "";

            System.out.println("Please enter the length of every data:");
            while(!TableUtilities.isInt(holder)) {
                holder = sc.nextLine();
                if(!TableUtilities.isInt(holder)) {
                    System.out.println("Please enter a number");
                }
                else {
                    if(Integer.parseInt(holder) < 1) {
                        System.out.println("Please enter an int greater than 0");
                        holder = "";
                    }
                }
            }
            int cellSize = Integer.parseInt(holder);

            if ( tableOptions.writeRandom(filename, tableModel, xSize, ySize, cellSize) ) {
                System.out.println("Successfully created a random file to string.");
            }
            else {
                System.out.println(TableConstant.WRITE_ERROR);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(TableConstant.PATH_NOT_FOUND);
        }
        catch (IOException e) {
            System.out.println(TableConstant.PATH_NOT_FOUND);
        }
    }

    public static void showDataOption(TableOptions tableOptions, TableModel tableModel) {
        if (!tableOptions.showData(tableModel)) {
            System.out.println("Unable to print the data.");
        }
    }

    public static void searchDataOption(TableOptions tableOptions, TableModel tableModel) {
        System.out.println("Enter string to search:");
        String holder = sc.nextLine();
        if (!tableOptions.searchString(tableModel, holder)) {
            System.out.println(TableConstant.PRINT_UNABLE);
        }
    }

    public static void editDataOption(TableOptions tableOptions, TableModel tableModel) {

        String holder = "";

        System.out.println(TableConstant.X_VALUE);
        while(!TableUtilities.isInt(holder)) {
            holder = sc.nextLine();
            if(!TableUtilities.isInt(holder)) {
                if(Integer.parseInt(holder) < 1) {
                    System.out.println("Please enter an int greater than 0");
                }
            }
        }
        int xSize = Integer.parseInt(holder);

        holder = "";

        System.out.println(TableConstant.Y_VALUE);
        while(!TableUtilities.isInt(holder)) {
            holder = sc.nextLine();
            if(!TableUtilities.isInt(holder)) {
                if(Integer.parseInt(holder) < 1) {
                    System.out.println("Please enter an int greater than 0");
                }
            }
        }
        int ySize = Integer.parseInt(holder);

        System.out.println("Enter the new string:");
        String updateValue = sc.nextLine();

        System.out.println("Replace the key(k) or the value(v)?:");
        String change = sc.nextLine();
        if (!tableOptions.editString(tableModel, xSize, ySize, updateValue, change)) {
            System.out.println(TableConstant.PRINT_UNABLE);
        }
    }

    public static void dataSortOption(TableOptions tableOptions, TableModel tableModel) {
        System.out.println(TableConstant.ASC_DESC);
        String holder = sc.nextLine();
        if (!tableOptions.dataSort(holder, tableModel)) {
            System.out.println(TableConstant.PRINT_UNABLE);
        }
    }

    public static void saveDataOption(TableOptions tableOptions, TableModel tableModel) {
        if (tableOptions.fileSave(tableModel)) {
            System.out.println("File write successful");
        }
        else {
            System.out.println("File write unsuccessful");
        }
    }



    public static void main(String args[]) {

        try {

            sc = new Scanner(System.in);

            TableModel tableModel = new TableModel();
            TableOptions tableOptions = new TableOptions();
            TableService tableService = new TableService();

            System.out.println(TableConstant.WELCOME_MSG);
            
            //ask for the options
            String chosen = "";
            String holder = "";

            while(tableService.isDone(chosen)) {
                //print the menu
                TableUtilities.printMenu();

                chosen = sc.nextLine(); //calls the scanner in the tableUtilities

                //switch case for the chosen option. 
                switch(chosen) {
                    case "1" :  readFileOption(tableOptions, tableModel);
                                break;

                    case "2" :  writeRandomOption(tableOptions, tableModel);
                                break;

                    case "3" :  showDataOption(tableOptions, tableModel);
                                break;

                    case "4" :  searchDataOption(tableOptions, tableModel);
                                break;

                    case "5" :  editDataOption(tableOptions, tableModel);
                                break;

                    case "6" :  dataSortOption(tableOptions, tableModel);
                                break;

                    case "7" :  saveDataOption(tableOptions, tableModel);
                                break;

                    case "0" :  break;
                                
                    default : System.out.println("Kindly choose within scope");
                              break;
                }

            }

            System.out.println(TableConstant.EXIT_MSG);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if(sc != null) {
                sc.close();
            }
        }

    }

}
