package com.example.demo;

public class ValueChange implements InterfaceValueChange {
    private int value;
    
    ValueChange(){}

    @Override
    public void onChange(int value) {
        this.setValue(value);
    }
    
    /**
     * @return int return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}
