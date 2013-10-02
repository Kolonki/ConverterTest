package com.technopark;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ConverterPage {

	private final WebDriver driver;
		
	private By inputLocator = By.id("ival");
	private By outputLocator = By.id("oval");
		
	public ConverterPage(WebDriver driver) {
		this.driver = driver;
		if (!driver.getTitle().startsWith("курс валют")) {
			throw new IllegalStateException("This is not money page");
		}
	}
		
	public ConverterPage typeInput(String input) {
			
		WebElement element = driver.findElement(inputLocator);
		element.clear();
		element.sendKeys(input);
			
		return this;
	}	
	
	public String getOutput() {
		
		WebElement element = driver.findElement(outputLocator);
		return element.getText();
		
	}
	
	public boolean isInputCorrect () {
		WebElement element = driver.findElement(inputLocator);
		System.out.println(element.getCssValue("color") );
		
		return (element.getCssValue("color").equals("rgba(0, 0, 0, 1)"));
	}
}
