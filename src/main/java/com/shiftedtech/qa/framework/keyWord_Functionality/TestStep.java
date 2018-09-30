package com.shiftedtech.qa.framework.keyWord_Functionality;

public class TestStep {

    private String step;
    private String keyword;
    private String page;
    private String testObject;
    private String keywordData;

    public TestStep(){

    }

    public TestStep(String step, String keyword, String page, String testObject, String keywordData) {
        this.step = step;
        this.keyword = keyword;
        this.page = page;
        this.testObject = testObject;
        this.keywordData = keywordData;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTestObject() {
        return testObject;
    }

    public void setTestObject(String testObject) {
        this.testObject = testObject;
    }

    public String getKeywordData() {
        return keywordData;
    }

    public void setKeywordData(String keywordData) {
        this.keywordData = keywordData;
    }

    public boolean isMatching(String kw){
        if(keyword.trim().equalsIgnoreCase(kw)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "step='" + step + '\'' +
                ", keyword='" + keyword + '\'' +
                ", page='" + page + '\'' +
                ", testObject='" + testObject + '\'' +
                ", keywordData='" + keywordData + '\'' +
                '}';
    }
}
