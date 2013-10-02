package com.technopark;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LocalDriverFactory {

	static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            driver = new FirefoxDriver();
            return driver;
        }

        if (browserName.toLowerCase().contains("chrome")) {
        		System.setProperty("webdriver.chrome.driver", "chromedriver");
        	
            driver = new ChromeDriver();
            return driver;
        }
        return driver;
    }
	
}
