package com.manio.tables;

public final class TableConstant {

    //welcome/exit messages
    public static final String WELCOME_MSG = "Welcome~";
    public static final String EXI_MSG = "System exiting...";

    //menu
    public static final String MENU_TITLE = "Choose an action:";
    public static final String MENU1 = "1: Search a substring";
    public static final String MENU2 = "2: Edit a cell";
    public static final String MENU3 = "3: Print the map";
    public static final String MENU4 = "4: Reinitialize the map";

    public static final String MENU5 = "1: Read from file";
    public static final String MENU6 = "2: Write random to file and update map";
    public static final String MENU7 = "3: Display map";
    public static final String MENU8 = "4: Search";
    public static final String MENU9 = "5: Edit value";
    public static final String MENU10 = "6: Sort";
    public static final String MENU11 = "7: Save to Current File";

    public static final String MENU0 = "0: Exit";

    //option for exit
    public static final String EXIT_OPTION = "0";
    public static final String EXIT_MSG = "System Exiting...";

    //input messages
    public static final String PATH_FILE = "Enter the whole path and filename:";
    public static final String X_VALUE = "Enter the value of X:";
    public static final String Y_VALUE = "Enter the value of Y:";
    public static final String ASC_DESC = "Order by Ascending (A) or Descending (D)?:";

    //error messages
    public static final String PATH_NOT_FOUND = "Cannot find the path specified.";
    public static final String EMPTY_MAP = "Map is still empty.";
    public static final String WRITE_ERROR = "An error occured while writing the file.";
    public static final String PRINT_UNABLE = "Unable to print the data.";


    //for file reading
    //delimiter for the key-value
    // public static final String KV_DELIMITER = " ";
    public static final String KV_DELIMITER = Character.toString ((char) 31);;

    //delimiter for the cells
    public static final String CELL_DELIMITER = "\t";

    //for showData()
    //delimiter for the key-value
    public static final String KV_DISPLAY = " ";

    //delimiter for the cells
    public static final String CELL_DISPLAY = " ";

    //list of possible randomly generated characters
    public static final char[] RANDOM_CHAR = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                             'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                             '1','2','3','4','5','6','7','8','9','0',
                             '!','@','#','$','%','^','&','*','(',')','-','_','+','=',
                             '/','<','.',',','>','?','`','~','|',':',';','\\','\'','"'
                            };

    private TableConstant() {
        //prevent instantation
    }

}