package com.shiftedtech.qa.scripts.Java_Self_Tutorials.Multi_Threading;

/**
 * Created by Debajyoti Paul on 6/7/2018 at 12:34 PM
 */


public class Thread_Runnable_Anonymous_Class {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {   //Anonymous class
            @Override
            public void run() {
                for(int i=0; i<5; i++){
                    System.out.println("Hi");
                    try { Thread.sleep(1000); } catch (Exception e) {}
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {  //Anonymous class
            @Override
            public void run() {
                for(int i=0; i<5; i++){
                    System.out.println("Hello");
                    try { Thread.sleep(1000); } catch (Exception e) {}
                }
            }
        });

        t1.start();
        try { Thread.sleep(10); } catch (Exception e) {}
        t2.start();

        t1.join();
        t2.join();   // The purpose of the join method is to wait the thread t2 to finish and then execute the "Main Thread"

        System.out.println("Is thread still Active? -- " + t1.isAlive());   // isAlive method returns true if thread is still Active or else returns false;
        System.out.println("Is thread still Active? -- " + t2.isAlive());   // Here, it will return false

        System.out.println("Bye..");    // This operation will be performed by the Main Thread after the
                                        // thread t1 and t2 stops since we have used the join method or else
                                        // it would have executed somewhere in between those threads.

    }
}
