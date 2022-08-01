package com.Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.Collections
 * @Auther:Chen
 * @CreateTime:2022--07--06 13--42
 * @Decription:T000
 */
public class collectionsTest {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(222);
        list.add(88);
        list.add(382);
        list.add(195);

//        //报异常：IndexOutOfBoundsException:Source does not fit in dest
//        ArrayList dest = new ArrayList();
//        Collections.copy(dest,list);

        //使用Array.asList将dest撑开，即使用null值的数组转化为List的形式
        List dest = Arrays.asList(new Object[list.size()]);
        Collections.copy(dest,list);
        System.out.println(dest);
    }
}
