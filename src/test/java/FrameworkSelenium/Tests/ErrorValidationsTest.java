package FrameworkSelenium.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import FrameworkSelenium.TestComponents.BaseTest;
import FrameworkSelenium.pageobject.CartPage;
import FrameworkSelenium.pageobject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
//	String email = "halukfaruk@gmail.com";
//	String password = "Haluk123";
//	String productName = "ZARA COAT 33";
	String country = "Turkey";
	String confirm = "THANKYOU FOR THE ORDER.";
//	
//	@Test(dependsOnMethods= {"ProductErrorValidition"},retryAnalyzer=Retry.class)
//	public void LoginErrorValidation() throws IOException, InterruptedException {
//
//		landingPage.loginApplication("halukfaruk@gmail.com", "Haluk13");
//		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMesaage());
//	}
	@Test(dependsOnMethods= {"ProductErrorValidition"})
	public void LogOutErrorValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("halukfaruk@gmail.com", "Haluk13");	
		Assert.assertEquals(landingPage.getErrorMesaage(),"Logout Successfully");
	}
	@Test
	public void emptyUserInformation() {
		landingPage.loginApplication("","");
		String errorMessage =landingPage.emptySubmit();
		Assert.assertEquals(errorMessage, "*Email is required"+""+"*Password is required");	
	}

	@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidition() throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication("sselenium@gmail.com", "Merhaba1");
		productCatalogue.getProductList();
		productCatalogue.addProductToCart("ZARA COAT 3");
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}