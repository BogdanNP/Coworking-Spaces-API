package com.example.demo;

public abstract class ClassSingleton {
    public static TestClassSingleton _instance;

    public static TestClassSingleton instance(){
        if(_instance == null){
            _instance = new TestClassSingleton();
        }
        return _instance;
    }
   
}
