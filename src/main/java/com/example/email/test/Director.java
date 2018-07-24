package com.example.email.test;

public class Director {

    Builder builder;
    public Director(Builder builder){
        this.builder=builder;
    }

    public Car build(){
        builder.setWheel();
        builder.setSeat();
        builder.setShell();
        return builder.getCar();
    }
}
