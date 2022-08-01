package com.kh.CustomerManager.ui;

import com.kh.CustomerManager.bean.Customer;
import com.kh.CustomerManager.service.CustomerList;
import com.kh.CustomerManager.util.CMUtility;

/**
 *
 */
public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public CustomerView() {
        Customer cust = new Customer("陈某", '男',
                21, "1000022", "Chen@qq,com");
        customerList.addCustomer(cust);
    }

    /**
     * 显示《客户信息管理软件》的方法
     */
    public void enterMainMenu() {
        boolean isflag = true;
        while (isflag) {
            System.out.println("\n------------------客户信息管理-------------------\n");
            System.out.println("                1、添加客户");
            System.out.println("                2、修改客户");
            System.out.println("                3、删除客户");
            System.out.println("                4、客户列表");
            System.out.println("                5、退出");
            System.out.print("       请选择：");

            char menu = CMUtility.readMenuSelection();

            switch (menu) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    daleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
//                    System.out.println("退出系統...");
                    System.out.print("确认是否退出系统（Y/N）：");
                    char isExit = CMUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        isflag = false;
                    }
//                    break;

            }

        }


    }


    /**
     * 添加客户的操作
     */
    private void addNewCustomer() {
//        System.out.println("添加客戶信息");
        System.out.println("------------------添加客户-------------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(10);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(13);
        System.out.print("邮箱：");
        String email = CMUtility.readString(30);

        Customer cust = new Customer(name, gender, age, phone, email);
        boolean issuccess = customerList.addCustomer(cust);
        if (issuccess) {
            System.out.println("------------------添加成功-------------------");
        } else {
            System.out.println("------------客户目录已满，添加失败-------------");
        }
    }

    /**
     * 修改客户
     */
    private void modifyCustomer() {
        System.out.println("修改客户信息");
        System.out.println("------------------修改客户-------------------");
        int number = 0;
        Customer cust;
        for (; ; ) {
            System.out.println("请选择待修改的客户编号（-1退出）：");
            number = CMUtility.readInt();
            if (number == -1) {
                return;
            }
            cust = customerList.getCustomer(number - 1);
            if (cust == null) {
                System.out.println("无法找到指定的用户！");
            } else {//找到相应的用户
                break;
            }
        }
        //开始修改用户信息
        System.out.print("姓名(" + cust.getName() + ")：");
        String name = CMUtility.readString(10, cust.getName());
        System.out.print("性别(" + cust.getGender() + ")：");
        char gender = CMUtility.readChar(cust.getGender());
        System.out.print("年龄(" + cust.getAge() + ")：");
        int age = CMUtility.readInt(cust.getAge());
        System.out.print("电话(" + cust.getPhone() + ")：");
        String phone = CMUtility.readString(13, cust.getPhone());
        System.out.print("邮箱(" + cust.getEmail() + ")：");
        String email = CMUtility.readString(30, cust.getEmail());

        Customer newCust = new Customer(name, gender, age, phone, email);

        boolean isReplace = customerList.replaceCustomer(number - 1, newCust);
        if (isReplace) {
            System.out.println("------------------修改成功-------------------");
        } else {
            System.out.println("------------------修改失败-------------------");
        }
    }

    /**
     * 删除客户
     */
    private void daleteCustomer() {
        System.out.println("------------------删除客户-------------------");
        int number;
        for (; ; ) {
            System.out.println("请输入待删除客户编号（-1退出）：");
            number = CMUtility.readInt();
            if (number == -1) {
                return;
            }

            Customer cust = customerList.getCustomer(number - 1);
            if (cust == null) {
                System.out.println("无法找到指定的客户！");
            } else {
                break;
            }
        }
        System.out.println("确认是否删除（Y/N)：");
        char isDelete = CMUtility.readConfirmSelection();
        if (isDelete == 'Y') {
            boolean alreadyDelete = customerList.deleteCustomer(number - 1);
            if(alreadyDelete){
                System.out.println("------------------成功删除-------------------");
            }else{
                System.out.println("------------------删除失败-------------------");
            }
        }


    }

    /**
     * 显示客户列表
     */
    private void listAllCustomers() {
//        System.out.println("显示客户信息");
        System.out.println("------------------客户列表-------------------");
        int total = customerList.getTotal();
        if (total == 0) {
            System.out.println("没有客户记录！");
        } else {
            System.out.println("编号\t\t姓名\t\t\t性别\t\t年龄\t\t电话\t\t\t邮箱");
            Customer[] custs = customerList.getAllCustomers();
            for (int index = 0; index < custs.length; index++) {
                Customer cust = custs[index];
                System.out.println((index + 1) + "\t\t" +
                        cust.getName() + "\t\t\t" + cust.getGender()
                        + "\t\t" + cust.getAge() + "\t\t" +
                        cust.getPhone() + "\t\t" + cust.getEmail());
            }
        }


        System.out.println("----------------客户列表结束------------------");
    }


    public static void main(String[] args) {
        CustomerView view = new CustomerView();
        view.enterMainMenu();
    }
}
