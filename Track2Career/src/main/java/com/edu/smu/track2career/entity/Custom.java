package com.edu.smu.track2career.entity;

public class Custom {
    private boolean isImportant;
    private String name;

    public Custom(boolean isImportant, String name) {
        this.isImportant = isImportant;
        this.name = name;
    }

    public boolean isIsImportant() {
        return isImportant;
    }

    public String getName() {
        return name;
    }
    
}
