package com.team.service;

public class Data {
    //将可读性差的数字替换了字符常量
    public static final int EMPLOYEE = 10;
    public static final int PROGRAMMER = 11;
    public static final int DESIGNER = 12;
    public static final int ARCHITECT = 13;

    public static final int PC = 21;
    public static final int NOTEBOOK = 22;
    public static final int PRINTER = 23;

    //employee      (10 , id , name , age , salary)
    //progremmer    (11 , id , name , age , salary)
    //designer      (12 , id , name , age , salary , bonus)
    //architect     (13 , id , name , age , salary , bonus , stock)

    public static final String[][] EMPLOYEES = {
            {"10","1","用户1","22","3000"},
            {"13","2","用户2","32","8000","1500","2000"},
            {"10","3","用户3","42","2600"},
            {"11","4","用户4","52","5500"},
            {"12","5","用户5","62","100","1000"},
            {"11","6","用户6","72","5100"},
            {"13","7","用户7","82","1800","1040","2220"},
            {"11","8","用户8","92","6900"},
            {"11","9","用户9","72","9000"},
            {"12","10","用户10","84","2200","2890"},
            {"11","11","用户11","98","9900"},
            {"12","12","用户12","44","7200","5040"}
    };
    //PC    :       21     model      display
    //NoteBook:     22     model      price
    //Printer:      23     name       type
    public static final String[][] EQUIPMENTS = {
            {},
            {"22","联想T4","6000"},
            {"21","华硕","三星17寸"},
            {"21","联想T4","NEC17寸"},
            {"21","戴尔","NEC17寸"},
            {"23","华硕","三星17寸"},
            {"21","戴尔","NEC17寸"},
            {"21","联想T4","NEC17寸"},
            {"22","戴尔","2881"},
            {"21","外星人","超炫库"},
            {"23","联想T4","NEC17寸"},
            {"21","戴尔","NEC17寸"}
    };

}
