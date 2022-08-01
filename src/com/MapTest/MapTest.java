package com.MapTest;

import org.junit.Test;

import java.util.*;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.MapTest
 * @Auther:Chen
 * @CreateTime:2022--07--05 22--36
 * @Decription:T000
 */
public class MapTest {
    @Test
    public void test1( ){
        Map map1 = new HashMap();
        map1.put("AA",123);
        map1.put(45,123);
        map1.put("AA",87); // 体现了修改了操作
        map1.put("BB",523);
        System.out.println(map1);//{AA=87, BB=523, 45=123}

        Map map2 = new HashMap();
        map2.put("CC",123);
        map2.put("AA",123);
        map1.putAll(map2);
        System.out.println(map1);//{AA=123, BB=523, CC=123, 45=123}

        Object value1 = map1.remove("CCC");//不存在
        Object value2 = map1.remove("CC");
        System.out.println(value1);//null
        System.out.println(value2);//123
        System.out.println(map1);//{AA=123, BB=523, 45=123}

        map1.clear();
        System.out.println(map1);//{}
        System.out.println(map1.size());//0
    }
    @Test
    public void test2(){
        Map map1 = new HashMap();
        map1.put("AA",123);
        map1.put(45,123);
        map1.put("BB",87);
        System.out.println(map1.get(45));//123

        boolean key1 = map1.containsKey("AA");
        boolean key2 = map1.containsKey("CC");//不存在
        System.out.println(key1);//true
        System.out.println(key2);//false
        boolean value = map1.containsValue(87);
        System.out.println(value);//true

        boolean isEmpty = map1.isEmpty();
        System.out.println(isEmpty);//false
        map1.clear();
        isEmpty = map1.isEmpty();
        System.out.println(isEmpty);//true
    }
    @Test
    public void test3(){
        Map map1 = new HashMap();
        map1.put("AA",123);
        map1.put(45,123);
        map1.put("BB",87);

        //遍历所有的key集：ketSet( )
        Set set = map1.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //遍历所有的value集：values( )
        Collection values = map1.values();
//        Iterator iterator1 = values.iterator();
//        while(iterator1.hasNext()){
//            System.out.println(iterator1.next());
//        }
        for(Object obj:values){
            System.out.println(obj);
        }
        //遍历所有的key-value
        // 方式一：entrySet( )
        Set set1 = map1.entrySet();
        Iterator iterator1 = set1.iterator();
        while(iterator1.hasNext()){
            Object next = iterator1.next();
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry)next;
            System.out.println(entry.getKey() + "---->" + entry.getValue());
        }

        //方式二：通过ketSet( )获取所有key的集，再使用Map.get(key)获取对应value
        Set keySet = map1.keySet();
        Iterator iterator2 = keySet.iterator();
        while(iterator2.hasNext()){
            Object key = iterator2.next();
            Object value = map1.get(key);
            System.out.println(key + "---->" + value);
        }
    }
}
