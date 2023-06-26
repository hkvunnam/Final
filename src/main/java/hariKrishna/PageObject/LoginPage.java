package hariKrishna.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hariKrishna.Abstract.AbstractClass;

public class LoginPage extends AbstractClass{
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	private WebElement user;
	
	@FindBy(id="userPassword")
	private WebElement pass;
	
	@FindBy(id="login")
	private WebElement log;
	
	@FindBy(id="toast-container")
	WebElement toast;
	
	public void login(String userName, String password) {
		user.sendKeys(userName);
		pass.sendKeys(password);
		log.click();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getError() {
		elementVisible(toast);
		return toast.getText();
	}

}
