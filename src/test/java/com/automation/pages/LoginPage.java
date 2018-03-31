/**
 * 
 */
package com.automation.pages;

import org.openqa.selenium.support.PageFactory;

import com.automation.baseclass.BaseClass;
import com.automation.objects.LoginPageObject;
import com.automation.utlity.GernalKeywors;
import com.automation.utlity.GlobalParameters;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author CHIRAG
 *
 */
public class LoginPage {

	LoginPageObject loginPageObject = new LoginPageObject();
	
	public LoginPage() {
		BaseClass obj = new BaseClass();
		if(GlobalParameters.runType.equalsIgnoreCase("web"))
		{
			PageFactory.initElements(obj.getDriver(), loginPageObject);
		}else
		{
			PageFactory.initElements(new AppiumFieldDecorator(obj.getDriver()), loginPageObject);
		}
		
	}
	
	public void enterUserName(String username)
	{
		GernalKeywors.sendkeys(loginPageObject.usernameTextField, username);
	}
	
	public void enterPassword(String password)
	{
		GernalKeywors.sendkeys(loginPageObject.passwordTextField, password);
		System.out.println(loginPageObject.loginButton.getText());
	}
}
