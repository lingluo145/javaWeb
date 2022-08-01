package com.PropertiesTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @BelongsProhect:动态代理的举例
 * @BelongsPackage:com.PropertiesTest
 * @Auther:Chen
 * @CreateTime:2022--07--13 14--05
 * @Decription:T000
 */
interface Human{
    String getBelief();

    void eat(String food);
}

//代理类
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "superman Test No.1";
    }

    @Override
    public void eat(String food) {
        System.out.println("喜欢的食物：" + food);
    }
}

class HumanUtil{
    public void method1(){
        System.out.println("========================通用方法1========================");
    }

    public void method2(){
        System.out.println("========================通用方法2========================");
    }
}
/*要想实现动态代理，需要解决的问题
    问题一：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象。
    问题二：当通过代理类的对象调用方法时，如何动态的去调用被代理类的同名方法
 */

//被代理类
class ProxyFactory{
    //调用此方法返回代理类的对象，解决问题一
    public static Object getProxyInstance(Object obj){//obj：被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Object obj;//需要使用被代理类的对象进行赋值

    public void bind(Object obj){
        this.obj = obj;
    }

    //当通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtil humanUtil = new HumanUtil();
        humanUtil.method1();
        ///method：即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj：被代理类的对象
        Object returnValue = method.invoke(obj,args);

        humanUtil.method2();
        //上述方法的返回值作为当前类中的invoke( )的返回值
        return returnValue;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过被代理类对象调用方法，会自动的调用被代理类中同名的方法
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("臭豆腐");


    }
}
