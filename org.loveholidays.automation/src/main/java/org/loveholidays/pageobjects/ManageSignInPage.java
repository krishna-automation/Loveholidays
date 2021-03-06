package org.loveholidays.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class ManageSignInPage extends BasePage {

	private By submit = By.cssSelector("button.mmb-login__form__submit__button");
	private By book_ref = By.cssSelector("input#js-reference-field-with-letters");
	private By surname = By.cssSelector("input[name='surname']");
	
	public ManageSignInPage(WebDriver driver) {
	       super(driver);	
	}


   public void bookingReference(String bookref){
	   driver.findElement(book_ref).sendKeys(bookref);
   }
   
   public void surname(String sname){
	   driver.findElement(surname).sendKeys(sname);
   }
   
  
   public void validateReference1(){
	    driver.findElement(surname).sendKeys("Krishna");  
		driver.findElement(submit).click();
		String reference_error1 = driver.findElement(By.cssSelector("input#js-reference-field-with-letters")).getAttribute("validationMessage");
		Assert.assertTrue(reference_error1.contains("Your booking reference starts with LVE or LOV"),"Not an invalid booking reference - not starting with LVE/LOV");
			
   }
		
   public void validateReference2(){
	     
		driver.findElement(submit).click();
		WebDriverWait wait = new WebDriverWait(driver,7);
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));	
		String reference_error2 = driver.findElement(By.cssSelector("p.error")).getText();	
		Assert.assertTrue(reference_error2.contains("Sorry this is not a valid booking reference"), "Not an invalid booking reference -starting with LVE/LOV");
		    } catch(Exception e){
			   				    e.printStackTrace();
		                        }
	
  }	
   	

   
}