package com.appium.cucumber.kakao;

import static org.junit.Assert.*;
import io.appium.java_client.android.*;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProfileChange {

	private AndroidDriver driver;

	@Before
	public void setUp() throws Exception {
		File classpathRoot = new File("D:/Automaion/kakao");
		File appDir = new File(classpathRoot, "/Downloadapk");
		File app = new File(appDir, "카카오톡 v5.3.3.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "3b9162a6");
		capabilities.setCapability("platformVersion", "5.0");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.kakao.talk");
		capabilities.setCapability("unicodeKeyboard", true);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		
		Thread.sleep(5000);
		String phonenumber = driver.findElement(By.id("com.kakao.talk:id/text_edit")).getText();
		try{
		if(phonenumber.substring(0, 3).equals("010"))
		{
			driver.findElement(By.id("com.kakao.talk:id/submit")).click();
		}}
		catch(NoSuchElementException e){
			e.printStackTrace(); 		
		}	
		Thread.sleep(3000);
		
		driver.findElement(By.id("com.kakao.talk:id/sdl__title")).isDisplayed();
		driver.findElement(By.id("com.kakao.talk:id/ok")).click();
		
		Thread.sleep(5000);
		
	}
	
		
	@Given("^프로필 관리 화면으로 이동$")
	public void 프로필_관리_화면으로_이동() throws Throwable {
		List<WebElement> myprofile = driver.findElementsById("com.kakao.talk:id/title");
		List<WebElement> GNB = driver.findElementsById("com.kakao.talk:id/iv_tab_icon");
		try{
		if(myprofile.get(0).isDisplayed()){
			GNB.get(3).click();
		}} 
		catch(NoSuchElementException e){
			e.printStackTrace(); 		
		}
		driver.findElement(By.name("프로필 관리")).click();
		
	}

	@When("^프로필 이름 변경$")
	public void 프로필_이름_변경() throws Throwable {	
	  driver.findElement(By.id("com.kakao.talk:id/nicknameButton")).click();
	  Thread.sleep(500);
	  
	  driver.findElement(By.id("com.kakao.talk:id/text_edit")).clear();
	  driver.findElement(By.id("com.kakao.talk:id/text_edit")).sendKeys("심성우");
	  
	  driver.findElement(By.id("com.kakao.talk:id/dontBtn")).click();
	  
	  driver.findElement(By.id("com.kakao.talk:id/nickname")).equals("심성우");
	    
	}

	@When("^프로필 상태메시지 변경$")
	public void 프로필_상태메시지_변경() throws Throwable {
		driver.findElement(By.id("com.kakao.talk:id/status_message_button")).click();
		  Thread.sleep(500);
		  
		  driver.findElement(By.id("com.kakao.talk:id/text_edit")).clear();
		  driver.findElement(By.id("com.kakao.talk:id/text_edit")).sendKeys("아자아자!!");
		  
		  driver.findElement(By.id("com.kakao.talk:id/dontBtn")).click();
		  Thread.sleep(5000);
		  driver.findElement(By.className("android.widget.Button")).click();
		  
		  driver.findElement(By.id("com.kakao.talk:id/status_message")).equals("아자아자!!");
	}

	@Then("^홈 화면에서 프로필 이름 및 상태메시지 변경 확인$")
	public void 홈_화면에서_프로필_이름_및_상태메시지_변경_확인() throws Throwable {
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Thread.sleep(500);
		List<WebElement> GNB = driver.findElementsById("com.kakao.talk:id/iv_tab_icon");
		GNB.get(0).click();
		Thread.sleep(500);
		
		List<WebElement> profileid = driver.findElementsById("com.kakao.talk:id/name");
		List<WebElement> profilestatus = driver.findElementsById("com.kakao.talk:id/message");
		assertEquals(profileid.get(0).getText(), "심성우");
		assertEquals(profilestatus.get(0).getText(), "아자아자!!");
			
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();

	}
}