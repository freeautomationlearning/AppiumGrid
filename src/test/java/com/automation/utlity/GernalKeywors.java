/**
 * 
 */
package com.automation.utlity;

import org.openqa.selenium.WebElement;

/**
 * @author CHIRAG
 *
 */
public class GernalKeywors {

	public static void click(WebElement ele)
	{
		ele.click();
	}
	
	public static void sendkeys(WebElement ele, String text)
	{
		ele.sendKeys(text);
	}
}
