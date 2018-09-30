package com.shiftedtech.qa.scripts.Java_Self_Tutorials;

public class Division {
    public static void main(String[] args) {
        int a = 23;
        int b = 5;
        int n = 0;

        while(a>b){
            a = a - b;
            n++;
        }
        System.out.println("Division result is " + n);
    }
}
