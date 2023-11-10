package FrameworkSelenium.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import FrameworkSelenium.TestComponents.BaseTest;
import FrameworkSelenium.pageobject.CartPage;
import FrameworkSelenium.pageobject.CheckOutPage;
import FrameworkSelenium.pageobject.ConfirmationPage;
import FrameworkSelenium.pageobject.LandingPage;
import FrameworkSelenium.pageobject.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionlml extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommers Page")
	public void I_landed_on_Ecommers_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String userName, String password) {
		productCatalogue = landingPage.loginApplication(userName,password);
	}
	
	@When ("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When ("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("Turkey");
		confirmationPage = checkOutPage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage= confirmationPage.getConformationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then ("{string} message is displayed")
	public void something_message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMesaage());
		driver.close();
	}
	}
