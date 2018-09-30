package com.shiftedtech.qa.scripts.Java_Self_Tutorials.SingletonDesign;

public class Singleton {
    public static void main(String args[]){
        Abc obj = Abc.getInstance();
        Abc obj1 = Abc.getInstance(); // No matter how many times we try to create an object (or call the getInstance method() ),
        Abc obj2 = Abc.getInstance(); // only one instance is created because it's only one thread (main method).
    }
}

//class Abc{
//	static Abc obj = new Abc();     // Early/Eager Instantiation
//	private Abc(){
//		System.out.println("Instance Created !!");
//	}
//	public static Abc getInstance(){
//		return obj;
//	}
//}

class Abc{
    static Abc obj;     // lazy instantiation
    private Abc(){
        System.out.println("Instance Created !!");
    }

    public static Abc getInstance(){
        if(obj == null)
        {
            obj = new Abc();
        }
        return obj;
    }
}
