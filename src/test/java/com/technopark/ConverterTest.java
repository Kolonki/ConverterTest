package com.technopark;

import junit.framework.Assert;

import org.testng.annotations.Test;

public class ConverterTest {
	
	private static final String converterUrl = "http://go.mail.ru/search?mailru=1&q=курс+валют";
	
	@Test
	public void zeroTest() {
	  
		invokeBrowser(converterUrl);
		ConverterPage page = new ConverterPage(LocalDriverManager.getDriver());
		
		page.typeInput("0");
		double output = Double.parseDouble(page.getOutput().replaceAll(",", "."));
		Assert.assertEquals(output, 0.0);

	}
	
	@Test
	public void emptyTest() {
		invokeBrowser(converterUrl);
		ConverterPage page = new ConverterPage(LocalDriverManager.getDriver());
		
		page.typeInput("  ");
		Assert.assertFalse(page.outputColor().equals("rgba(0, 0, 0, 1)"));
	}
	
	@Test
	public void oneTest() {
		invokeBrowser(converterUrl);
		ConverterPage page = new ConverterPage(LocalDriverManager.getDriver());
		
		page.typeInput("1");
		double output = Double.parseDouble(page.getOutput().replaceAll(",", "."));
		Assert.assertEquals(output, 35.0, 5.0); //35 +/- 5
	}
	
	@Test
	public void floatTest() {
		invokeBrowser(converterUrl);
		ConverterPage page = new ConverterPage(LocalDriverManager.getDriver());
		
		page.typeInput("1.5");
		double output = Double.parseDouble(page.getOutput().replaceAll(",", "."));
		Assert.assertEquals(output, 52.5, 10); //52.5 +/- 10
	}
	
	@Test
	public void negativeTest() {
		invokeBrowser(converterUrl);
		ConverterPage page = new ConverterPage(LocalDriverManager.getDriver());
		
		page.typeInput("-5");
		Assert.assertFalse(page.outputColor().equals("rgba(0, 0, 0, 1)"));
	}
	
	@Test
	public void wordTest() {
		invokeBrowser(converterUrl);
		ConverterPage page = new ConverterPage(LocalDriverManager.getDriver());
		
		page.typeInput("hello");
		Assert.assertFalse(page.outputColor().equals("rgba(0, 0, 0, 1)"));
	}
	
	@Test
	public void correctInputTest() {
		invokeBrowser(converterUrl);
		ConverterPage page = new ConverterPage(LocalDriverManager.getDriver());
		
		page.typeInput("120");
		Assert.assertTrue( page.outputColor().equals("rgba(0, 0, 0, 1)"));
	}
	
	@Test
	public void bigNumberTest() {
		invokeBrowser(converterUrl);
		ConverterPage page = new ConverterPage(LocalDriverManager.getDriver());
		
		page.typeInput("99999999999999999999999");
		String output = page.getOutput();
		Assert.assertTrue( output.contains("×10") );
	}
	
	

	private void invokeBrowser(String url) {
		System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + LocalDriverManager.getDriver().hashCode());
        LocalDriverManager.getDriver().get(url);
		
	}
	
	
}
