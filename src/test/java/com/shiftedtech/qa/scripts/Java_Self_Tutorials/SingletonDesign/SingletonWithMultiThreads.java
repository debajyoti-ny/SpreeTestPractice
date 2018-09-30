package com.shiftedtech.qa.scripts.Java_Self_Tutorials.SingletonDesign;

import java.lang.Thread;
import java.lang.Runnable;

public class SingletonWithMultiThreads {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable(){
            public void run()
            {
                Abcd obj = Abcd.getInstance();
            }
        });

        Thread t2 = new Thread(new Runnable(){
            public void run()
            {
                Abcd obj = Abcd.getInstance();
            }
        });

        t1.start();
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        t2.start();
    }
}

//class Abcd{
//	static Abcd obj; // lazy instantiation
//
//	private Abcd(){
//		System.out.println("Instance Created !!");
//	}
//
//	public static synchronized Abcd getInstance(){ // using keyword synchronized will make sure only one object is created because
//		if(obj == null)							// both the threads cannot run at the same time. However, it will take about 100x more time.
//		{
//			obj = new Abcd();
//		}
//		return obj;
//	}
//}

//class Abcd{
//	static Abcd obj; // lazy instantiation
//
//	private Abcd(){
//		System.out.println("Instance Created !!");
//	}
//
//	public static Abcd getInstance(){ // we can minimize the 100x more time to likely about 10x more time by using Thread.sleep method.
//		if(obj == null)
//		{
//			obj = new Abcd();
//		}
//		return obj;
//	}
//}

class Abcd{
    static Abcd obj; // Double-Checked Locking instantiation

    private Abcd(){
        System.out.println("Instance Created !!");
    }

    public static Abcd getInstance(){ // we can minimize the 100x/10x more time to likely about 5x more time by using the synchronized class.
        if(obj == null)
        {
            synchronized(Abcd.class)
            {
                if(obj == null)
                {
                    obj = new Abcd();
                }
            }
        }
        return obj;
    }
}
