package FrameworkSelenium.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkSelenium.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	@FindBy(css="li [type='button']")
	WebElement checkOutEle;	
	@FindBy(css=".items h3")
	private List<WebElement> cartProducts;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckOutPage goToCheckOut() {
		checkOutEle.click();
		return new CheckOutPage(driver);
	}
	
}
