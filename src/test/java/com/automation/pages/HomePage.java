/**
 * 
 */
package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.automation.baseclass.BaseClass;
import com.automation.objects.HomePageObject;
import com.automation.utlity.GernalKeywors;

/**
 * @author CHIRAG
 *
 */
public class HomePage {

	HomePageObject homePageObject = new HomePageObject();
	
	public HomePage() {
		BaseClass obj = new BaseClass();
		WebDriver driver = obj.getDriver();
		System.out.println(driver);
		PageFactory.initElements(driver, homePageObject);
	}
	
	public LoginPage clickOnSignIn()
	{
		GernalKeywors.click(homePageObject.logIn);
		return new LoginPage();
	}
	
	
}
