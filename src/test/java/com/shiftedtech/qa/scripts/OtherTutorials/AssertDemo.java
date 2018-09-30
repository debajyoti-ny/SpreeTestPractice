package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.Assert;
import org.junit.Test;

/**
 * Assert Test shows "Error" (not "Exceptions") when test Fails
 */
public class AssertDemo{
@Test
public void test1(){
    Assert.assertEquals(10,20); //Will fail because values not matching
}

@Test
public void test2(){
    Assert.assertEquals("Values not matching", 10,20); //Test Fails and shows Error Message
}

@Test
public void test3(){
    System.out.println("Test 3 Started...");
    Assert.assertEquals("Values matching", 10,10); //Test Passes and also executes the third line of the method
    System.out.println("Test 3 Finishsed...");
}

@Test
public void test4(){
    System.out.println("Test 4 Started...");
    Assert.assertEquals("Values not matching", 10,20); //Test Fails and doesn't executes the third line of the method
    System.out.println("Test 4 Finishsed...");
}

}
