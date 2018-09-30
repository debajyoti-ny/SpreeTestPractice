package com.shiftedtech.qa.scripts.Java_Self_Tutorials.ReflectionAPI;

class A{

}
public class ClassMethods {
    public static void main(String[] args) throws Exception{
        Class classObj = Class.forName("com.shiftedtech.qa.scripts.Java_Self_Tutorials.ReflectionAPI.A");
        System.out.println(classObj.isInterface()); //Check if "A" is an interface -- will return false
                                                    //There are many methods like this of Class class.
        System.out.println(classObj.getSuperclass()); //Gives the name of the super class(Object Class for this example)
    }
}
