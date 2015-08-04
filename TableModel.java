package com.manio.tables;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TableModel{

    //current file open
    private String currFile;

    private Map<String, List<Config>> mapTable = new LinkedHashMap<String, List<Config>>();


    public void setData(Map<String, List<Config>> mapTable) {
		this.mapTable = mapTable;
	}

    public Map<String, List<Config>> getData() {
    	return this.mapTable;
    }

    public void setCurrFile(String pathFile) {
        currFile = pathFile;
    }

    public String getCurrFile() {
        return currFile;
    }

}