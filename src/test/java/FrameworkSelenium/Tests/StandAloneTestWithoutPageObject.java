package FrameworkSelenium.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestWithoutPageObject {

	public static void main(String[] args) {
		String email = "halukfaruk@gmail.com";
		String password = "Haluk123";
		String productName = "ZARA COAT 3";
		String country = "Turkey";
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-lg-4")));

		List<WebElement> products = driver.findElements(By.cssSelector("div.col-lg-4"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		String toast = "#toast-container";
		String animation = ".ng-animating";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(toast)));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(animation))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".items h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector("li [type='button']")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "tur").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(1)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click(); 
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}
}
