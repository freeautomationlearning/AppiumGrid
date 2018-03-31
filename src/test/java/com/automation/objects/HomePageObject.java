package com.automation.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePageObject {

	@FindBy(xpath="//a[@title='Log In']")
	public WebElement logIn;
}
