package com.team.service;

import com.team.domain.*;

import static com.team.service.Data.*;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.team.service
 * @Auther:Chen
 * @CreateTime:2022--06--22 17--10
 * @Decription:负责将Data汇总的数据封装到EMPLOYEE[]数组中，同时提供相关操作EMPLOYEE[]的方法。
 */
public class NameListService {
    private Employee[] employees;
    //给employee数组及数组元素进行初始化
    public NameListService(){
        //1、根据项目提供的Data类构建的大小的emplee数组
        //2、在根据Data类中的数据构建不同的对象，（本项目中三种）
        //3、将对象保存在数组中
        employees = new Employee[Data.EMPLOYEES.length];

        for(int i = 0; i < employees.length; i++){
            int type = Integer.parseInt(Data.EMPLOYEES[i][0]);
            //获取基本信息
            int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
            String name = Data.EMPLOYEES[i][2];
            int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
            double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);
            Equipment equipment = null;
            double bonus;
            int stock;

            switch (type){
                case EMPLOYEE:
                    employees[i] = new Employee(id,name,age,salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id,name,age,salary,equipment);
                    break;
                case DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id,name,age,salary,equipment,bonus);
                    break;
                case ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id,name,age,salary,equipment,bonus,stock);
                    break;
                default:
                    break;
            }
        }
    }

    private Equipment createEquipment(int index) {
        int key = Integer.parseInt(Data.EQUIPMENTS[index][0]);

        String modelorname = EQUIPMENTS[index][1];
        switch(key){
            case PC:
                String display = EQUIPMENTS[index][2];
                return new PC(modelorname,display);
            case NOTEBOOK:
                double price = Double.parseDouble(EQUIPMENTS[index][2]);
                return new NoteBook(modelorname,price);
            case PRINTER:
                String type = EQUIPMENTS[index][2];
                return new Printer(modelorname,type);
        }
        return null;
    }

    public Employee[] getAllEmployees() {
        return employees;
    }

    public Employee getEmploee(int id) throws TeamException{
        for(int i =0; i < employees.length; i++){
            if(employees[i].getId() == id){
                return employees[i];
            }
        }
        throw new TeamException("找不到指定员工");
    }
}
