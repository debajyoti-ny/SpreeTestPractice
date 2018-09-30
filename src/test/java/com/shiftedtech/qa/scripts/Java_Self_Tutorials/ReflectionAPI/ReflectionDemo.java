package com.shiftedtech.qa.scripts.Java_Self_Tutorials.ReflectionAPI;

import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) throws Exception{
        // loads the class and returns the reference of Class class
        Class classObj = Class.forName("com.shiftedtech.qa.scripts.Java_Self_Tutorials.ReflectionAPI.Test");

        // creates a new instance of the class -- By default, creates an instance of Object type,
        // so we typecast it to get an object of Test class
        Test testObj = (Test)classObj.newInstance();

        // Steps to call a private method... (Mainly used in debugging...)
        // Method name and method parameters (null - because, there aren't any parameters of "show" method)
        Method methodObj = classObj.getDeclaredMethod("show", null);

        methodObj.setAccessible(true); //Setting it to true, so that we can access the private method
        methodObj.invoke(testObj, null); // Invoke method takes the object of the class and the
                                               // parameters of the private method
    }
}
