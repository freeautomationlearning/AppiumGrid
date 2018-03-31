/**
 * 
 */
package com.automation.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @author CHIRAG
 *
 */
public class LoginPageObject {

	@FindBy(id="usernameOrEmail")
	@AndroidFindBy(id="org.wordpress.android:id/nux_username")
	public WebElement usernameTextField;
	
	@AndroidFindBy(id="org.wordpress.android:id/nux_password")
	@FindBy(id="password")
	public WebElement passwordTextField;
	
	@AndroidFindBy(xpath= "//*[@text='Sign in']/../following::android.widget.TextView[1]")
	//org.wordpress.android:id/nux_sign_in_button		
	@FindBy(xpath="//button[text()='Log In']")
	public WebElement loginButton;
}
