package com.example.demo;

public class UpdateValueChange {
    private InterfaceValueChange valueChange;

    public UpdateValueChange(InterfaceValueChange valueChange){
        this.valueChange = valueChange;
    }

    public void onUpdate(int value){
        valueChange.onChange(value);
    }


}
