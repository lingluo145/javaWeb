package com.LambdaTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.LambdaTest
 * @Auther:Chen
 * @CreateTime:2022--07--13 23--48
 * @Decription:T000
 */
public class test {
    @Test
    public void test( ){
        List<String> list = Arrays.asList("北京","南京","西京","天津","东京","新蒲京","燕京");
        List<String> filterStrs = filterString(list, new Predicate<String>(){
            @Override
            public boolean test(String s){
                return s.contains("京");
            }
        });
        System.out.println(filterStrs);

        List<String> filterStrs1 = filterString(list , s->s.contains("京"));
        System.out.println(filterStrs1);
    }
    //根据给定的规则，过滤集合中的字符串。此规则有Perdicate的方法决定
    public List<String> filterString(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList<>();
        for(String s : list){
            if(pre.test(s)) {
                filterList.add(s);
            }

        }
        return filterList;
    }

}
