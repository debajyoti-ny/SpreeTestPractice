package com.shiftedtech.qa.scripts.OtherTutorials.ObjectRepo_Config_File;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Debajyoti Paul on 3/22/2018 at 4:09 PM
 */
public class ConfigReader {

    Properties properties = null;

    public ConfigReader(){
        String src = "./src/Configuration_File/config.properties";
        try{
            File file = new File(src);
            FileInputStream fis = new FileInputStream(file);
            properties = new Properties();
            properties.load(fis);
        } catch(Exception ex){
            System.out.println("Exception is: " + ex.getMessage());
        }
    }

    public String getChromeDriverPath(){
        return properties.getProperty("ChromeDriver");
    }

    public String getFirefoxDriverPath(){
        return properties.getProperty("FirefoxDriver");
    }

    public String getApplicationURL(){
        return properties.getProperty("URL");
    }

    public String getExcelFile(){
        return properties.getProperty("ExcelFile");
    }
}
