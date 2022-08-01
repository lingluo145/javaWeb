package com.team.domain;

public class PC implements Equipment {
    private String model;//机器型号
    private String display;//显示器名称

    public PC(){
        super();
    }

    public PC(String model,String display){
        super();
        this.model = model;
        this.display = display;
    }

    public void setModel(String model){
        this.model = model;
    }

    public String getModel(){
        return model;
    }

    public void setDisplay(String display){
        this.display = display;
    }

    public String getDisplay(){
        return display;
    }

    @Override
    public String getDscription() {
        return model + "(" + display + ")";
    }
}
