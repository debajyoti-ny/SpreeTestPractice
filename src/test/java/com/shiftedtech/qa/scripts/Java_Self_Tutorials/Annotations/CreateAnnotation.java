package com.shiftedtech.qa.scripts.Java_Self_Tutorials.Annotations;

import java.lang.annotation.*;

// Creating custom Annotations
// Marker Annotation -- No values inside the interface
// Single value Annotation -- Single value inside the interface
// Multi value Annotation -- Multiple values inside the interface
// Meta-data annotation
@Target(ElementType.TYPE)             //We want to use it for both class and interface, so we use TYPE
@Retention(RetentionPolicy.RUNTIME)  //Annotation will be available till RUNTIME
@Inherited                          // If we want the annotation to be available to other sub classes
@interface SmartPhone{
    String os() default "Symbian";
    int version() default 1;
}

@SmartPhone(os = "Android", version = 6) //Overrides the default annotation values
class NokiaASeries{
    String model;
    int size;

    public NokiaASeries(String model, int size) {
        this.model = model;
        this.size = size;
    }
}

public class CreateAnnotation {
    public static void main(String[] args) {
        NokiaASeries obj = new NokiaASeries("A70", 5);
        System.out.println("Model " + obj.model + " - " + obj.size + " inch size"); //Normal_Exec print out of the model

        //We want to print if the phone is a "SmartPhone" or not...

        Class classObj = obj.getClass(); //returns the instance of Class class
        Annotation an = classObj.getAnnotation(SmartPhone.class);
        SmartPhone smartPhone = (SmartPhone)an;
        System.out.println(smartPhone.os());

    }
}
