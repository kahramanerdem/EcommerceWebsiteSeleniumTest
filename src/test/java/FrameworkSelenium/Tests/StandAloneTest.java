package FrameworkSelenium.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameworkSelenium.TestComponents.BaseTest;
import FrameworkSelenium.pageobject.CartPage;
import FrameworkSelenium.pageobject.CheckOutPage;
import FrameworkSelenium.pageobject.ConfirmationPage;
import FrameworkSelenium.pageobject.OrderPage;
import FrameworkSelenium.pageobject.ProductCatalogue;

public class StandAloneTest extends BaseTest{
	String country = "Turkey";
	String confirm = "THANKYOU FOR THE ORDER.";
	
	@Test(dataProvider="getData",groups="Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry(country);
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage= confirmationPage.getConformationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(confirm));
	}
	@Test(dataProvider="getData",dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest(HashMap<String,String> input) {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalogue.goToOrderPage();		
		Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("productName")));			
	}	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") 
				+ "//src//test//java//FrameworkSelenium//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
