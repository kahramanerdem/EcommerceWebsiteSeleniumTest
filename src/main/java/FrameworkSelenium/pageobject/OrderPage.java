package FrameworkSelenium.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkSelenium.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="td:nth-child(3)")
	private List <WebElement> orderNames;
	
	public Boolean verifyOrderDisplay(String productName) {
		 Boolean match = orderNames.stream().anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(productName));
		 return match;
	}
}
