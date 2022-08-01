package com.Thread;


import org.junit.Test;

/**
 * @BelongsProhect:创建三个窗口卖票，总票数为100张，使用实现Runnable接口的方式
 * @BelongsPackage:com.Thread
 * @Auther:Chen
 * @CreateTime:2022--06--28 23--15
 * @Decription:为使多个线程同时进行参数变更的同时，减少重票，错票的情况
 */

class Windows1 implements Runnable{
    private int ticket = 1000;
    Object obj = new Object();//多个线程公用一个对象

    @Override
    public void run(){
        //多个对象公用同一把锁,也可以用this作为唯一的对象当做锁
        synchronized(obj) {
            while (true) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);//睡眠时间100毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "：" + ticket);
                    ticket--;
                } else {
                    break;
                }

            }
        }
    }
}

class TicketSaleTest {
    public static void main(String[] args) {
        Windows1 Win = new Windows1();

        Thread t1 = new Thread(Win);
        Thread t2 = new Thread(Win);
        Thread t3 = new Thread(Win);

        t1.setName("窗口1");
        t2.setName("窗口2-----------");
        t3.setName("窗口3+++++++++++");

        t1.start();
        t2.start();
        t3.start();
    }


}
