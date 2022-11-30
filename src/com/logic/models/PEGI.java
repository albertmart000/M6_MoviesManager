package com.logic.models;

public enum PEGI {
    
    PUBLIC(0), PEGI7(7), PEGI12(12), PEGI16(16), PEGI18(18);
    
    public int minYear;

    private PEGI(int minYear) {
        this.minYear = minYear;
    }

    public int getMinYear() {
        return minYear;
    }  
    
    
    
    
}
