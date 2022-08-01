package com.stringTest;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @BelongsProhect:完成String内容的五项方法联系
 * @BelongsPackage:com.stringTest
 * @Auther:Chen
 * @CreateTime:2022--06--30 21--24
 * @Decription:T000
 */


public class stringMethod
{
    //2）将一个字符串进行反转。将字符串中指定部分进行反转。
    public String reverse(String str, int startIndex, int endIndex) {
        if(str==null){
            return null;
        }else {
            //方式一：转化为char[]
//        String str3 = "abcdefg";
//        char[] ch1 = str3.toCharArray();
//        int i = 2;
//        int j = ch1.length - 1 -1 ;
//        while(true){
//            char temp;
//            if(i < j){
//                temp = ch1[i];
//                ch1[i] = ch1[j];
//                ch1[j] = temp;
//                i++;
//                j--;
//            }else{
//                break;
//            }
//        }
//        String str4 = new String(ch1);
//        return str4

            //方式二：使用String拼接
//        String reverseStr = str.substring(0,startIndex);
//        System.out.println(reverseStr);
//        for(int i = endIndex; i>=startIndex; i--) {
//            reverseStr += str.charAt(i);
//        }
//        reverseStr += str.substring(endIndex+1);
//        return reverse


            //方式三
            StringBuilder builder = new StringBuilder(str.length());
            builder.append(str.substring(0, startIndex));
            for (int i = endIndex; i >= startIndex; i--) {
                builder.append(str.charAt(i));
            }
            builder.append(str.substring(endIndex + 1));
            return builder.toString();
        }
    }


    //3）获取一个字符串在另一个字符串中出现的次数。
    public int demo3(String mainStr, String compStr){
        if(mainStr.length()<compStr.length()){
            return 0;
        }else {
            int times = 0;//记录关键词出现的次数
            int num = -1;//记录上一次查询到的索引的位置，下一次查询直接接续上一次查询的索引的位置
            int nums = mainStr.lastIndexOf(compStr);//记录最后匹配的索引
            while (true) {
                num = mainStr.indexOf(compStr, num+1);
                if (num == -1) {
                    break;
                }
                times += 1;
            }
            return times;
        }
    }

    //4）获取两个字符串中最大相同子串【允许多个不同但等长的子串】。
    public String[] demo4(String str1,String str2){
        if(str1 != null && str2 != null) {
            StringBuffer sBuffer = new StringBuffer();
            String maxStr = (str1.length()) >= (str2.length()) ? str1 : str2;
            String minStr = (str1.length()) < (str2.length()) ? str1 : str2;
            int length = minStr.length();
            for (int i = 0; i < length; i++) {//固定区间大小，从左到右遍历
                for (int x = 0, y = length - i; y <= length; x++, y++) {
                    String subStr = minStr.substring(x, y);
                    if (maxStr.contains(subStr)) {
                        sBuffer.append(subStr + ",");
                    }
                }
                if(sBuffer.length()!=0){
                    break;
                }
            }
            String[] split = sBuffer.toString().replaceAll(",$","").split("\\,");
            return split;
        }
        return null;

    }

    //5）对字符串中字符进行自然顺序排序
    public String sortStr(String str){
        if(str == null){
            return null;
        }else{
            char[] ch1 = str.toCharArray();
            int length = ch1.length;
            for(int i = 0; i < length; i++){
                for(int j = i+1; j <length; j++){
                    if(ch1[i] > ch1[j]){
                        char temp = ch1[i];
                        ch1[i] = ch1[j];
                        ch1[j] = temp;
                    }
                }
            }
            return new String(ch1);
        }
    }

    public void quickSort(char[] ch,int left, int right){
        if(left > right) {
            return ;
        }else {
            int L = left;
            int R = right;
            char pivot = ch[L];
            while (L < R) {

                while (L<R && ch[R]>=pivot)R--;
                if(L<R){
                    swap(ch,L,R);
                }
                while (L<R && ch[L]<=pivot)L++;
                if(L<R){
                    swap(ch,L,R);
                }
            }
            quickSort(ch,left,R-1);
            quickSort(ch,R+1,right);
        }
    }

    public void swap(char[] ch, int num1, int num2){
        char temp = ch[num1];
        ch[num1] = ch[num2];
        ch[num2] = temp;
    }



    public static void main(String[] args) {
        //1）模拟一个trim方法，去除字符串两端的空格
        String str1 = "  我是  帅逼  ";
        String str2 = str1.trim();
        System.out.println("---" + str2 + "----");



        //2）将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为“abfedcg”
        stringMethod s1 = new stringMethod();
        String str3 = "abcdefg";
        String reverse = s1.reverse(str3, 2, 5);
        System.out.println("反转的结果："+ reverse);



        //3）获取一个字符串在另一个字符串中出现的次数。比如获取“ab”在“abkkcadkabkebfkabkskab”中出现的次数
        stringMethod s3 = new stringMethod();
        String str5 = "abkkcadkabkebfkabkskab";
        String str6 = "ab";
        int i = s3.demo3(str5, str6);
        System.out.println("共出现了"+ i + "次");

        //4）获取两个字符串中最大相同子串。比如str1=”abcwerthelloyuiodef”;str2=”cvhellobnm”
        stringMethod s4 = new stringMethod();
        String str7 = "abcbnmmhwerthelloyuiodef";
        String str8 = "cvhellobnmmh";
        String[] s = s4.demo4(str7, str8);
        System.out.println("最大相同子串为："+ Arrays.toString(s));

        //5）对字符串中字符进行自然顺序排序
        stringMethod s5 = new stringMethod();
        String str9 = "nvasdneeiSADkcASiiicKKcmm554";
        String str10 = s5.sortStr(str8);
        char[] ch1 = str9.toCharArray();
        s5.quickSort(ch1,0,ch1.length-1);
        System.out.println("冒泡排序后的结果为："+ str10);
        System.out.println("快速排序后的结果为："+ new String(ch1));
    }
}
