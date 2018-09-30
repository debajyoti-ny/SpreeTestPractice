package com.shiftedtech.qa.framework.keyWord_Functionality;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI_TestNG;
import com.shiftedtech.qa.framework.utils.ExcelReader;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

public class KeywordDrivenScriptBase extends ScriptBaseCompositePOI_TestNG{

    private static String KW_OBJECT_REPOSITORY = System.getProperty("user.dir") + "/src/test/Resources/KW-Scripts/ObjectRepo.xlsx";
    private HashMap<String, TestObject> objectRepo = null;

    @BeforeClass
    public void beforeClass(){

    }

    @BeforeMethod
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        super.setUp();

        objectRepo = new HashMap<String, TestObject>();
        loadObjectRepository();
    }

    @AfterMethod
    public void tearDown(){
        super.tearDown();
    }

    public void loadObjectRepository(){
        loadObjectRepository(KW_OBJECT_REPOSITORY);
    }

    public void loadObjectRepository(String... fileNames){

        for(String file: fileNames){
            ExcelReader excelReader = new ExcelReader(file);
            String data[][] = excelReader.getExcelSheetData("OR", true);

            for(int i=0; i<data.length; i++){
                System.out.println("**************** TestObject [ " + i + " ]**********************");

                TestObject to = new TestObject();
                to.setId(data[i][0]);
                to.setPage(data[i][1]);
                to.setElement_name(data[i][2]);
                to.setDescription(data[i][3]);
                to.setFindBy(data[i][4]);
                to.setUsing(data[i][5]);

                System.out.println(to.toString());

                String KEY = to.getPage().toUpperCase() + "." + to.getElement_name().toUpperCase();
                if(!objectRepo.containsKey(KEY)){
                    objectRepo.put(KEY, to);
                }
            }
        }
    }

    public TestObject getTestObjectFromObjectRepo(String page, String element_name){
        String KEY = page.toUpperCase() + "." + element_name.toUpperCase();
        TestObject to = null;
        if(objectRepo.containsKey(KEY)){
            to = objectRepo.get(KEY);
        }else{
            throw new RuntimeException("Key: " + KEY + " does not exist");
        }

        return to;
    }

    public void keywordProcess(String fileName, String sheetName){
        String[][] data = null;
        ExcelReader excelReader = new ExcelReader(fileName);
        data = excelReader.getExcelSheetData(sheetName, true);

        for(int i=0; i<data.length; i++){

            TestStep ts = new TestStep();
            ts.setStep(data[i][0]);
            ts.setKeyword(data[i][1]);
            ts.setPage(data[i][2]);
            ts.setTestObject(data[i][3]);
            ts.setKeywordData(data[i][4]);

            System.out.println(ts.toString());

            if(ts.isMatching("Click")){
                TestObject to = getTestObjectFromObjectRepo(ts.getPage(), ts.getTestObject());
                homePage.clickElement(to.findElementBy());
            }else if(ts.isMatching("TypeText")){
                TestObject to = getTestObjectFromObjectRepo(ts.getPage(), ts.getTestObject());
                homePage.typeText(to.findElementBy(), ts.getKeywordData());
            }else if(ts.isMatching("verifyText")){
                TestObject to = getTestObjectFromObjectRepo(ts.getPage(), ts.getTestObject());
                homePage.verifyText(to.findElementBy(), ts.getKeywordData());
            }else if(ts.isMatching("verifyPageTitle")){
                homePage.verifyPageTitle(ts.getKeywordData());
            }else if(ts.isMatching("navigateToLoginPage")){
                homePage.navigateToLoginPage();
            }else if(ts.isMatching("login")){
                String loginData[] = ts.getKeywordData().split("\\|");
                loginPage.loginDetails(loginData[0], loginData[1]);
            }else if(ts.isMatching("verifyLoginSuccess")){
                homePage.signInSuccessMsg();
            }else if(ts.isMatching("verifyLoginNotSuccess")){
                loginPage.signInFailureMsg();
            } else {
                System.out.println("KeyWord is not matching...");
                throw new RuntimeException("Unknown keyword: " + ts.getKeyword());
            }
        }
    }

}
