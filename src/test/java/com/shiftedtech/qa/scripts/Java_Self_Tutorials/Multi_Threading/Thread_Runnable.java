package com.shiftedtech.qa.scripts.Java_Self_Tutorials.Multi_Threading;

class Hola implements Runnable{   //Instead of Thread class, we'll implement the Runnable Interface
    public void run(){          // Runnable is a functional interface, i.e., it contains only 1 method (i.e. "run()")
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

class Hey implements Runnable{
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

public class Thread_Runnable {
    public static void main(String[] args) {
        Runnable obj1 = new Hola();
        Runnable obj2 = new Hey();

        Thread t1 = new Thread(obj1);  //Thread can take an object which has a reference of Runnable interface
        Thread t2 = new Thread(obj2);

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

    }
}

