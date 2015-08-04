package com.manio.tables;

public class Config implements Comparable<Config>{
    private String key;
    private String value;
    // private int order;
    private int kResult;
    private int vResult;
    private int xIndex;
    private int yIndex;

    //for key and value only
    public Config(String key, String value) {
        this.key = key;
        this.value = value;
    }

    //for key and value with x and y index
    public Config(String key, String value, int xIndex, int yIndex) {
        this.key = key;
        this.value = value;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    //for key
    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    //for value
    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    //for y index
    public void setY(int yIndex) {
        this.yIndex = yIndex;
    }

    public int getY() {
        return this.yIndex;
    }

    //for x index
    public void setX(int xIndex) {
        this.xIndex = xIndex;
    }

    public int getX() {
        return this.xIndex;
    }

    //for search results in key
    public void setKResult(int kResult) {
        this.kResult = kResult;
    }

    public int getKResult() {
        return this.kResult;
    }

    //for search results in value
    public void setVResult(int vResult) {
        this.vResult = vResult;
    }

    public int getVResult() {
        return this.vResult;
    }

    //returns the total search results
    public int getResult() {
        return this.kResult + this.vResult;
    }

    //returns the X and Y axis
    public String axisString() {
        return "[" + this.xIndex + TableConstant.KV_DISPLAY + this.yIndex + "] ";
    }

    //test
    public String searchResult() {
        return "Index " + axisString() + " with data " + toString() + " have " + getKResult() + ":" + getVResult();
    }


    @Override
    public String toString() {
        return "[" + this.key + TableConstant.KV_DISPLAY + this.value + "] ";
    }

    //Comparable of key and value
    public int compareTo(Config config) {
        int keyCompare = this.key.compareTo(config.key);
        int valueCompare = (keyCompare == 0) ? this.value.compareTo(config.value) : keyCompare;
        if (valueCompare > 0) {
            int holder = this.yIndex;
            this.yIndex = config.getY();
            config.setY(holder);
        }
        else if (valueCompare<0) {
            int holder = config.getY();
            config.setY(this.yIndex);
            this.yIndex = holder;
        }
        return valueCompare;
        
    }

}