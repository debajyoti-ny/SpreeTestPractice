package com.shiftedtech.qa.scripts.Java_Self_Tutorials;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Debajyoti Paul on 4/12/2018 at 2:59 PM
 */
public class BubbleSort {

    @Test
    public void sorting(){
        String[] states = {"Alabama", "New York", "California", "New Jersey", "Texas", "Alaska"};
        String[] modifiedStates = {"Alabama", "New York", "California", "New Jersey", "Texas", "Alaska"};
       // bubbleSort(modifiedStates);

        String temp = null;
        int result;
        for (int i = 0; i < modifiedStates.length; i++) {
            for (int j = 1; j < (modifiedStates.length - i); j++) {
                result = modifiedStates[j - 1].compareTo(modifiedStates[j]);
                if (result > 0) {
                    temp = modifiedStates[j - 1];
                    modifiedStates[j - 1] = modifiedStates[j];
                    modifiedStates[j] = temp;
                }
            }
        }

        System.out.println("Actual States -- " + Arrays.deepToString(states));
        System.out.println("Modified States -- " + Arrays.deepToString(modifiedStates));
    }

    public String[] bubbleSort(String[] arr){
        String temp = null;
        int result;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < (arr.length - i); j++) {
                result = arr[j - 1].compareTo(arr[j]);
                if (result > 0) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
