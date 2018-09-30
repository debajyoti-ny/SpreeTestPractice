package com.shiftedtech.qa.scripts.ShiftTestTutorials.JavaRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REGEXClass {

    public static void main(String[] args) {
        String string = "I am a String, Yes I am";
        System.out.println(string);
        String youString = string.replaceAll("I", "You");
        System.out.println(youString);

        String alphaNumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(alphaNumeric.replaceAll(".", "X"));

        //Starts-with => ^
        System.out.println(alphaNumeric.replaceAll("^abcD", "X-"));

        String newalphaNumeric = "ajbcDeeeF12Ghhiiiijkl99z";
        System.out.println(newalphaNumeric.replaceAll("^abcDee", "Hello"));

        System.out.println(newalphaNumeric.matches("^hello"));
        System.out.println(newalphaNumeric.matches("^abcDeee"));
        System.out.println(newalphaNumeric.matches("abcDeeeF12Ghhiiiijkl99z"));

        //ends-with => $
        System.out.println(newalphaNumeric.replaceAll("99z$", "snow"));

        System.out.println(newalphaNumeric.replaceAll("[bei]", "_"));
        System.out.println(newalphaNumeric.replaceAll("[aei][Fj]","%"));

        System.out.println("star".replaceAll("[s]tar", "we"));

        String hasWhiteSpace = "I have blank_ spaces in this string\n";
        System.out.println(hasWhiteSpace);
        System.out.println(hasWhiteSpace.replaceAll("\\s", ""));
        //System.out.println(hasWhiteSpace.replaceAll("\\t", "\n"));
        System.out.println(hasWhiteSpace.replaceAll("\\S", ""));
        System.out.println(hasWhiteSpace.replaceAll("\\w", "x")); //replace 0-9 and a-z includin _
        System.out.println(hasWhiteSpace.replaceAll(".", "x"));

        String anotherWhiteSpaceString = "I @123--have $blank_ spaces in this string";
        System.out.println(anotherWhiteSpaceString.replaceAll("\\W", "X"));

        System.out.println(hasWhiteSpace.replaceAll("\\b", "<p>"));

        String str = "I live in \"New York City\". And It's Cold.";

        //Quantifier
        String anotherAlphaNumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(anotherAlphaNumeric.replaceAll("^abcDeee", "X"));
        System.out.println(anotherAlphaNumeric.replaceAll("^abcDe{3}", "YY"));
        System.out.println(anotherAlphaNumeric.replaceAll("^abcDe+", "YYY"));
        System.out.println(anotherAlphaNumeric.replaceAll("^abcDe*", "="));
        System.out.println(anotherAlphaNumeric.replaceAll("^abcDe{2,5}", "--"));
        System.out.println(anotherAlphaNumeric.replaceAll("h+i*j", "#"));

        StringBuilder htmlText = new StringBuilder("<h1>This is a heading</h1>");
        htmlText.append("<h2>This is another heading</h2>");
        htmlText.append("<p>This is a paragraph</p>");
        htmlText.append("<p>");
        htmlText.append("<b>THis is a bold paragraph</b>");
        htmlText.append("</p>");
        htmlText.append("<h2>This is another heading of something</h2>");
        htmlText.append("<p>This is a summary paragraph</p>");
        System.out.println(htmlText);

        String headingPattern = "<h2>";

        Pattern pattern = Pattern.compile(headingPattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        matcher.reset();
        int count =0;
        while (matcher.find()){
            count++;
            System.out.println("Occrance: " + count + " : " + matcher.start() + " : " + matcher.end());
        }


        String h2GroupPattern1 = "(<h2>.*</h2>)";

        Pattern groupPattern1 = Pattern.compile(h2GroupPattern1);
        Matcher groupMatcher1 = groupPattern1.matcher(htmlText);
        System.out.println(groupMatcher1.matches());

        groupMatcher1.reset();
        while (groupMatcher1.find()){
            System.out.println("Occ:" + groupMatcher1.group());
        }

        String h2GroupPattern = "(<h2>.*?</h2>)";

        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());


        groupMatcher.reset();
        while (groupMatcher.find()){
            System.out.println("Occ:" + groupMatcher.group());
        }

        String h2GroupTextPattern = "(<h2>)(.*?)(</h2>)";
        Pattern textGroupPattern = Pattern.compile(h2GroupTextPattern);
        Matcher textMatchPattern = textGroupPattern.matcher(htmlText);

        while (textMatchPattern.find()){
            System.out.println("Occurrence: " + textMatchPattern.group(2));
        }




    }

}
