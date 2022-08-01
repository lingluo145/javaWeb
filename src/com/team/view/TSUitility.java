package com.team.view;

import java.util.*;


public class TSUitility {
    private static Scanner scanner = new Scanner(System.in);


    /**
     * 下面的方法用于界面菜单的选择。该方法读取键盘，如果用户
     * 键入’1’-’4’中的任意字符，则方法返回。返回值为用户键入字符。
     * @return char
     */
    public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' &&
                    c != '3' && c != '4') {
                System.out.print("选择错误，请重新输入：");
            } else break;
        }
        return c;
    }

    /**
     * 只读取字符串的第一位，多出来的都提示错误。
     */
    String str = readKeyBoard(1, false);


    /**
     * 从键盘读取一个字符，并将其作为方法的返回值。
     * @return
     */
    public static char readChar() {
        String str = readKeyBoard(1, false);
        return str.charAt(0);
    }


    /**
     *description:该方法项目等待，直到用户按空格之后再进行下一步
     **/
    public static void readReturn(){
        System.out.print("按回车键继续....");
        readKeyBoard(100,true);
    }




    /**
     * 从键盘读取一个长度不超过2位的整数，并将其作为方法的返回值。
     * @return
     */
    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误，请重新输入：");
            }
        }
        return n;
    }





    /**
     * 用于确认选择的输入。该方法从键盘读取‘Y’或’N’，并将其作为方法的返回值。
     * @return
     */
    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("选择错误，请重新输入：");
            }
        }
        return c;
    }


    /**
     * 读入键盘中的值
     * @param limit
     * @param blankReturn
     * @return
     */
    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
                continue;
            }
            break;
        }

        return line;
    }


}
