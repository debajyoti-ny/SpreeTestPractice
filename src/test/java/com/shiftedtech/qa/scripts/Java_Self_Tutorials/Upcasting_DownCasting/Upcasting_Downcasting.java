package com.shiftedtech.qa.scripts.Java_Self_Tutorials.Upcasting_DownCasting;

public class Upcasting_Downcasting {
    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.x);
        b.run();
        System.out.println("-------------------------");

        A ab = new B();  //Upcasting -- Only works if the methods are common in both the classes
                        //Upcasting -- Can't call the B's walk() method bcz it's not common to both the classes
        System.out.println(ab.x);  //Prints out A's value (because Reference is of class A and overriding doesn't apply to data types)
        ab.run();  //Prints out B's method
        System.out.println("-------------------------");

        C c = new C();
        System.out.println(c.x);  //Prints out C's value
        c.walk();       //Prints out C's method
        System.out.println("-------------------------");

        B bc = new C(); //Upcasting...
        System.out.println(bc.x);  //Prints out B's value
        bc.run();  //Prints out C's method (Can only call methods common to both B and C)
        bc.walk(); //Prints out C's method (Can only call methods common to both B and C)
        System.out.println("-------------------------");

        C abc = (C) bc; //Downcasting -- Object "ab" was created in line #21
        abc.dance(); //With downcasting, I can create an object but it doesn't takes new space in memory and use the "dance" method from class C

    }
}

class A{
    int x = 100;
    public void run(){
        System.out.println("A is running...");
    }
}

class B extends A{
    int x = 200;
    public void run(){
        System.out.println("B is running...");
    }

    public void walk(){
        System.out.println("B is walking...");
    }
}

class C extends B{
    int x = 300;
    public void run(){
        System.out.println("C is running...");
    }

    public void walk(){
        System.out.println("C is walking...");
    }

    public void dance(){
        System.out.println("C is dancing...");
    }
}
