package com.team.domain;

public class NoteBook implements Equipment {

    private String model;//机器型号
    private double price;//价格

    public void setModel(String model){
        this.model = model;
    }

    public String getModel(){
        return model;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public NoteBook(){
        super();
    }

    public NoteBook(String model,double price){
        super();
        this.model = model;
        this.price = price;
    }

    @Override
    public String getDscription() {
        return model + "(" + price + ")";
    }
}
