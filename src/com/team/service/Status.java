package com.team.service;

/**
 * @description:表示员工状态
 * @author: Chen
 * @date: 2022/6/22 16:57
 * @param:
 * @return:
 **/
public class Status {
    private  final String NAME;
    private Status(String NAME){
        this.NAME = NAME;
    }

    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

    public String getNAME(){
        return NAME;
    }

    public String toString(){
        return NAME;
    }

}
