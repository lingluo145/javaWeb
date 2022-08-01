package com.kh.CustomerManager.service;

import com.kh.CustomerManager.bean.Customer;

public class CustomerList {
    private Customer[] customers;//用来保存客户对象的数组
    private int total = 0;//记录已保存客户对象的数量


    public CustomerList(){

    }


    /**
     * 用来初始化数组的构造器
     * @param totalCustomer：指定数组的长度
     */
    public CustomerList(int totalCustomer){
        customers = new Customer[totalCustomer];
    }

    /**
     * 将指定的客户添加到数组中
     * @param customer
     * @return 成功返回true，失败返回false
     */
    public boolean addCustomer(Customer customer){
        if(total>=customers.length){
            return false;
        }
        customers[total++]=customer;
        return true;
    }

    /**
     * 修改指定客户的信息
     * @param index：数组位置
     * @param cust：新客户信息
     * @return 成功返回true，失败返回false
     */
    public boolean replaceCustomer(int index,Customer cust){
        if(index<0||index>=total){
            return false;
        }
        customers[index] = cust;
        return true;
    }

    /**
     * 删除指定位置的客户信息
     * @param index：数组位置
     * @return 成功返回true，失败返回false
     */
    public boolean deleteCustomer(int index){
        if(index<0||index>=total){
            return false;
        }
        for(int i = index;i < total-2;i++){
            customers[i] = customers[i+1];
        }
        customers[total-1] = null;//注意最后一个需要置空
        total--;
        return true;
    }

    /**
     * 获取客户信息的全部数组
     * @return 客户数组
     */
    public Customer[] getAllCustomers(){
        Customer[] custs = new Customer[total];
        for(int i = 0; i<total; i++){
            custs[i] = customers[i];
        }
        return custs;
    }

    /**
     * 获取指定位置上的客户
     * @param index
     * @return 如果没找到则返回null，否则就返回指定的客户信息
     */
    public Customer getCustomer(int index){
        if(index<0||index>=total){
            return null;
        }
        return customers[index];
    }

    /**
     * 返回客户数组的长度
     * @return 客户数total
     */
    public int getTotal(){
        return total;
    }
}