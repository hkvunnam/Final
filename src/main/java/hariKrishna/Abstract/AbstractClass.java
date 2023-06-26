package hariKrishna.Abstract;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractClass {
	
	WebDriver driver;
	WebDriverWait wait; 
	
	
	public AbstractClass(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void elementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	

}
