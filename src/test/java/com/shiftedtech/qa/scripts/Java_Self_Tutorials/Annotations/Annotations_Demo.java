package com.shiftedtech.qa.scripts.Java_Self_Tutorials.Annotations;

@FunctionalInterface
interface ABC{ //@FunctionalInterface means the interface will have only 1 method. More than 1 method shows error
    void ab();
//    void def();

}

class A{
    public void showMeTheDetailsOfShift(){
        System.out.println("Hello...");
    }
}

class B extends A{
    @Override
    public void showMeTheDetailsOfShift(){  //If the method name doesn't matches, then the annotation @Override will show an error
        System.out.println("Hi...");
    }

    @Deprecated
    public void javaExample(){  //@Deprecated means method is available but not advised to use
        System.out.println("Learn Java...");
    }
}

public class Annotations_Demo {
    public static void main(String[] args) {
        B b = new B();
        b.showMeTheDetailsOfShift();
        b.javaExample();
    }
}
