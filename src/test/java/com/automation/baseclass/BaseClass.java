/**
 * 
 */
package com.automation.baseclass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.utlity.GlobalParameters;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * @author CHIRAG
 *
 */
public class BaseClass {

	private static WebDriver driver;
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder server;
	
	String platformRunAs;
	/*
	 * myPlatform should be web or mobile
	 * 
	 */
	final String myPlatform = "web";
	
	
	@Parameters({"platform","runOn"})
	@BeforeClass
	public void setup(@Optional(myPlatform)String platform, @Optional("chrome_normal")String runOn) throws MalformedURLException
	{
		System.out.println(platform);
		GlobalParameters.runType = platform;
		String path = System.getProperty("user.dir");
		switch (platform) {
		case "web":
		
			if(runOn.equalsIgnoreCase("chrome"))
			{
				System.out.println("Chrome Browser is opening..... ");
				System.setProperty("webdriver.chrome.driver", path+"//drivers//chromedriver.exe");
				driver= new ChromeDriver();
			}else if(runOn.equalsIgnoreCase("firefox"))
			{
				System.out.println("Firefox Browser is opening..... ");
				System.setProperty("webdriver.gecko.driver", path+"//drivers//geckodriver.exe");
				driver= new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.get("https://wordpress.com");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			break;

		case "mobile":
			System.out.println("Application is opening... ");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("app", path+"//App//wordpress.apk");
			cap.setCapability("platformName", "Android");
			cap.setCapability("deviceName", "Moto G");
			cap.setCapability("appWaitActivity", "org.wordpress.android.ui.accounts.SignInActivity");
			cap.setCapability("appPackage", "org.wordpress.android");
			if(runOn.equals("192.168.56.101:5555"))
			{
				cap.setCapability("udid", "192.168.56.101:5555");
				driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4729/wd/hub"), cap);
			}else if(runOn.equals("ZY25683TPXL"))
			{
				cap.setCapability("udid", "ZY25683TPXL");
				driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4726/wd/hub"), cap);
			}else if(platformRunAs.equals("mobile"))
			{
				driver = new AndroidDriver<MobileElement>(service.getUrl(), cap);
			}
			System.out.println("mobile will launch");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			break;
		default:
			System.out.println("Incorrect Platform...");
			break;
		}		
		
	}

	@AfterClass
	public void tearDown()
	{
		//driver.quit();
		/*if(mobileRunAs.equals("normal"))
		{
			//driver.quit();
		}*/
		
	}

	public WebDriver getDriver()
	{
		return this.driver;
	}


	@Parameters({"runAs"})
	@BeforeTest
	public void startAppoumserver(@Optional(myPlatform)String platform)
	{
		System.out.println(platform);
		if(platform.equalsIgnoreCase("mobile"))
		{
			platformRunAs="mobile";
			server = new AppiumServiceBuilder();
			server.usingAnyFreePort();
			server.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			server.withArgument(GeneralServerFlag.LOG_LEVEL, "error:error");
			service = AppiumDriverLocalService.buildService(server);
			service.start();
			System.out.println("Appium Server is Started...");
		}

	}

	@Parameters({"runAs"})
	@AfterTest
	public void stopAppiumServer(@Optional(myPlatform) String platform)
	{
		if(platform.equalsIgnoreCase("mobile"))
		{
			service.stop();
			System.out.println("Appium Server is Stoped...");
		}
	}
}
