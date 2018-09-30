package com.shiftedtech.qa.framework.utils;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Arrays;

public class RegexArrayMatcher extends TypeSafeMatcher<String[]> {

    private final String[] actualElements;

    public RegexArrayMatcher(final String[] elements) {
        this.actualElements = elements;
    }

    @Override
    protected boolean matchesSafely(String[] expectedElements) {
        boolean result = false;
        if(expectedElements.length != actualElements.length)
        {
            result = false;
        }
        else
        {
            result = true;
            for(int i = 0; i < expectedElements.length; i++){
                result = result && expectedElements[i].matches(actualElements[i]);
            }
        }
        return result;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("matches regular expression= '" + Arrays.toString(actualElements) + "'");
    }

    // matcher method you can call on this matcher class
    public static RegexArrayMatcher matchesRegexArray(final String[] regex) {
        return new RegexArrayMatcher(regex);
    }
}