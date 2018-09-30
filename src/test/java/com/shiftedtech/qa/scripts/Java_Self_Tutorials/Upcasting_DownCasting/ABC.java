package com.shiftedtech.qa.scripts.Java_Self_Tutorials.Upcasting_DownCasting;

public class ABC {
    public static void main(String[] args) {
    Parent p = new Parent();
    p.run();

    Parent p1 = new Child(); //Method Hiding
    p1.run();

    Child c = new Child();
    c.run();

    }
}

class Parent{
    public static void run(){
        System.out.println("I am in parent class");
    }

}

class Child extends Parent {

    public static void run(){
        System.out.println("I am in child class");
    }

    public static void walk() { System.out.println("Keep walking..."); }
}
