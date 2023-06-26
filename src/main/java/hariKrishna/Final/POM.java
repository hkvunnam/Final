package hariKrishna.Final;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import hariKrishna.PageObject.LoginPage;

public class POM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		LoginPage log = new LoginPage(driver);
		log.goTo();
		log.login("hk@email.com", "asjcbw49");
		Assert.assertEquals("Incorrect email or password.", log.getError());
		driver.close();
	}

}
