package com.shiftedtech.qa.scripts.ShiftTestTutorials;

//import static org.hamcrest.CoreMatchers.*;

import com.shiftedtech.qa.framework.utils.RegexArrayMatcher;
import com.shiftedtech.qa.framework.utils.RegexMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class HamcrestTest {

    @Test
    public void testList(){
        List<String> actual = Arrays.asList("a","b","c","d");
        String[] strings= {"a", "b", "c","d"};

        List<String> expected = Arrays.asList(strings);

        //assertThat(actual, is(expected));

        //assertThat(actual, hasItem("c"));

        //assertThat(actual, hasSize(4));

        //assertThat(actual, contains("a","b","c","d"));

        //assertThat(actual, containsInAnyOrder("a","c","d", "b"));

        //assertThat(actual, not(IsEmptyCollection.empty()));

        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
    }

    @Test
    public void testListInteger(){
        List<Integer> actual = Arrays.asList(1,2,3,4,5);
        List<Integer> expected = Arrays.asList(1,2,3,4,5);

        assertThat(actual, is(expected));

        assertThat(actual, hasItem(3));

        assertThat(actual, hasSize(5));

        assertThat(actual.size(), is(5));

        assertThat(actual, contains(1,2,3,4,5));

        assertThat(actual, containsInAnyOrder(2,1,4,3,5));

        assertThat(actual, everyItem(lessThan(7)));

        assertThat(actual, everyItem(greaterThanOrEqualTo(1)));
    }

    @Test
    public void testArrayItems(){
        String[] actual = {"\"Hello\"", "Today", "Beautiful"};
        assertThat(actual, is(arrayContainingInAnyOrder( "Today", "Beautiful", "\"Hello\"")));
    }

    @Test
    public void testArrayItems2(){
        String[] actual = {"Hello", "Today", "Beautiful"};
        assertThat(actual, is(arrayContaining( "Today", "Beautiful", "Hello")));

    }

    @Test
    public void testArrayItems3(){
        String[] actual = {"Hello", "Today", "Beautiful"};

        assertThat(actual, is(arrayWithSize((3))));

    }


    @Test
    public void testArrayItems4(){
        List<String> actual = Arrays.asList("Hello", "Today", "Beautiful");

        assertThat(actual, contains("Hello", "Today", "Beautiful"));
    }


    @Test
    public void testString1(){
        String str = "world";

        assertThat(str, equalTo("world"));

        assertThat(str, equalToIgnoringCase("World"));
    }

    @Test
    public void testString2(){
        String actual = "Arizona, New York";

        assertThat(actual, startsWith("Ari"));
    }

    @Test
    public void testString3(){
        String actual = "Arizona, New York";

        assertThat(actual, endsWith("York"));
    }

    @Test
    public void testString4(){
        String actual = "Arizona, New York";

        assertThat(actual, endsWith("York"));
    }


    @Test
    public void testString5(){
        String actual = "Arizona, New York";

        assertThat(actual, containsString(", N"));
    }

    @Test
    public void test_RegexCases1(){
        String actual = "Value of 5";

        assertThat(actual, RegexMatcher.matchesRegex("Value of \\d"));
        assertThat(actual, RegexMatcher.matchesRegex("Value of [0-5]"));



    }

    @Test
    public void test_RegexCases2(){
        String actual = "Value 5 is valid";

        assertThat(actual, RegexMatcher.matchesRegex("Value .* (is|was) valid"));
    }

    @Test
    public void test_RegexCases3(){
        String[] actual = {"Red 6", "Black 7", "White 9"};
        String[] expected = {"Red \\d", "Black \\d", "White \\d"};
        assertThat(actual, RegexArrayMatcher.matchesRegexArray(expected));

    }




}
