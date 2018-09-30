package com.shiftedtech.qa.scripts.Java_Self_Tutorials.Multi_Threading;

/**
 * Expected answer for the program below is 2000 which is achieved only by making the "increment"
 * method "synchronized". If it's not synchronized, then answer will be anywhere between 1000 and 2000
 * because there will be a conflict many times between the two threads while accessing the value of count.
 *
 * By making the method synchronized, we are making it thread safe... meaning, only 1 thread can access
 * the method at a time.
 */

class Counter{

    int count;      //By default, value is 0

    public synchronized void increment(){
        count++;
    }

}
public class Thread_Synchronized {

    public static void main(String[] args) throws InterruptedException {

        Counter c1 = new Counter();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    c1.increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    c1.increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();  //to pause the execution of one thread until the other thread finishes its work
        t2.join();

        System.out.println("Count -- " + c1.count);

    }

}
