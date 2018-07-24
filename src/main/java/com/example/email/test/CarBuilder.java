package com.example.email.test;

public class CarBuilder implements Builder{
    Car car=null;
    public CarBuilder(){
        car=new Car();
    }
    @Override
    public void setShell() {
        car.setShell("车壳");
        System.out.println("构造车壳");
    }

    @Override
    public void setSeat() {
      car.setSeat("座椅");
        System.out.println("构造座椅");

    }

    @Override
    public void setWheel() {
       car.setWheel("轮子");
        System.out.println("构造轮子");

    }

    @Override
    public Car getCar() {
        return null;
    }
}
