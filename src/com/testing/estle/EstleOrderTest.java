package com.testing.estle;
/*
 * @author nitiranjan
 */

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

class EstleOrderTest {
	static ChromeDriver driver=null;
	static WebDriverWait wait=null;
	
	@BeforeEach
	public void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nitir\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@Test
	public void testMacCosmeticsAddToCart() throws InterruptedException {
		//Go to main url and click New
		driver.get("https://www.maccosmetics.com/");
		driver.findElement(By.xpath("//a[@href='/collections' and contains(.,'New')]")).click();
		Thread.sleep(5000);
		
		//Test Page loads
		Assert.assertTrue(driver.getTitle().equals("MAC New Makeup Collections | MAC Cosmetics - Official Site"));
		
		//Click the first category
		WebElement element = driver.findElement(By.xpath("//img[contains(@data-src,'bronzer')]"));
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(element).click().perform();
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
		
		//Click the first product and add to bag
		WebElement product1=driver.findElementByXPath("//*[@id='product--prod_id-PROD76078']/div[2]/div/div/a");
		WebElement addToCart = driver.findElementByXPath("//*[@id='product--prod_id-PROD76078']/div[4]/a");
		Actions actions=new Actions(driver);
		actions.moveToElement(product1).moveToElement(addToCart).click().perform();
		Thread.sleep(5000);
		
		//Verify the ItemCount in bag is 1
		String amount= driver.findElementById("site-bag-count").getText();
		//System.out.println("Amount: "+amount);
		Assert.assertTrue(amount.equals("1"));
	
	}
	
	@Test
	public void testEstleLauderAddToCart() throws InterruptedException {
		//Go to main url and click New
		driver.get("https://www.esteelauder.com/");
		driver.findElementById("cboxClose").click();;
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@href='/whats-new' and contains(.,'New')]")).click();
		Thread.sleep(5000);
		
		//Test Page loads
		Assert.assertTrue(driver.getTitle().equals("New Beauty Products: Skin Care, Makeup, Fragrance & More | Estée Lauder"));
		
		//Click the first Item and add to cart
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,800)");
		wait = new WebDriverWait(driver, 2);
		driver.findElementByXPath("//*[@data-product-id='PROD61168' and @data-test-id='mpp_quickshop']").click();;
		wait = new WebDriverWait(driver, 2);
		driver.findElementByXPath("//*[@data-product-id='PROD61168' and @data-test-id='add-to-cart']").click();;
		Thread.sleep(5000);
		
		//Verify the ItemCount in bag is 1
		String amount= driver.findElementByXPath("//*[@class='utility-nav__cart-count page-utilities__cart-count']").getText();
		//System.out.println("Amount: "+amount);
		Assert.assertTrue(amount.equals("1"));
		
	
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
	}

}
