package FrameworkSelenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import FrameworkSelenium.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	@FindBy(id="login") 
	WebElement submit;	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	@FindBy(xpath="//div[contains(text(),'*Email is required')]")
	WebElement emptyEmailErrorMessage;
	@FindBy(xpath="//div[contains(text(),'*Password is required')]")
	WebElement emptyPasswordErrorMessage;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	public String getErrorMesaage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String emptySubmit() {
		submit.click();
		String emptyEmail = emptyEmailErrorMessage.getText();
		String emptyPassword = emptyPasswordErrorMessage.getText();
		return emptyEmail+" "+emptyPassword;
		
	}
}
