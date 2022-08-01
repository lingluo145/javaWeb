package com.NewInstanceTest;

import org.junit.Test;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.NewInstanceTest
 * @Auther:Chen
 * @CreateTime:2022--07--12 15--17
 * @Decription:T000
 */
public class NewInstance {
    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<Person> clazz = Person.class;
        //newInstance()：调用此方法，创建对应的运行时类的对象.内部调用了运行时类的空参构造器
        //方法正常创建运行时类的对象的要求：
        //1、运行时类必须提供空参构造器
        //2、空参的构造器的访问权限得适合。通常设置为public

        //在javaBean中要求提供一个public的空参构造器，原因：
        //1、便于通过反射，创建运行时类的对象
        //2、便于子类继承此运行时类是，默认调用super( )时，保证父类有此构造器
        Person p = clazz.newInstance();
        System.out.println(p);
    }
}
