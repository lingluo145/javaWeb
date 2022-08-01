package com.GenericTest;

import org.junit.Test;

import java.util.*;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:GenericTest
 * @Auther:Chen
 * @CreateTime:2022--07--06 16--18
 * @Decription:T000
 */
public class genericTest {
    @Test
    public void test1() {
        ArrayList list = new ArrayList<>();
        //需求：存放学生成绩
        list.add(77);
        list.add(79);
        list.add(87);
        list.add(84);
        //问题一：类型不安全
//        list.add("Tom");

        for(Object score : list){
            //问题二：强转时，可能出现ClassCastException
            int stuScore = (Integer) score;
            System.out.println(stuScore);
        }
    }
    //集合中使用泛型的情况
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(77);
        list.add(79);
        list.add(87);
        list.add(84);
        //编译时，就会进行类型检查，保证数据安全
//        list.add("Tom")

        //方式一
//        for(Integer score : list){
//            //避免了强转的操作
//            int stuScore =  score;
//            System.out.println(stuScore);
//        }
        //方式二
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3(){
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("Tom",44);
        map.put("June",77);
        map.put("Chen",60);
        //泛型的嵌套
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println(key + "=" + value);
        }

    }
}
