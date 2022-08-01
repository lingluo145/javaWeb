package com.team.domain;

public class Printer implements Equipment {

    private String name;
    private String type;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public Printer(){
        super();
    }

    public Printer(String name,String type){
        super();
        this.name = name;
        this.type = type;
    }

    public String getDscription(){
        return name + "(" + type + ")";
    }
}
