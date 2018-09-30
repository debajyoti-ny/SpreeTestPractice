package com.shiftedtech.qa.scripts.Java_Self_Tutorials.Multi_Threading;

class Hi extends Thread{
    public void run(){
        for(int i=0; i<5; i++){
            System.out.println("Hi");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Hello extends Thread{
    public void run(){
        for(int i=0; i<5; i++){
            System.out.println("Hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        Hi obj1 = new Hi();
        Hello obj2 = new Hello();

        obj1.start();  //The start() method can call only the run() method. So our methods has to be named as "run()"
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        obj2.start();

    }
}
