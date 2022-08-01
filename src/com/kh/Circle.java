package com.kh;

public class Circle {
    public static void main(String[] arge){
        CircleArea c1 = new CircleArea();
        c1.radius = 3;
        double area = c1.findArea();
        System.out.println("面积为：" + area);
    }
}

class CircleArea{
    double radius;
    public double findArea(){
        double area = Math.PI * radius * radius;
        return area;
    }
}
