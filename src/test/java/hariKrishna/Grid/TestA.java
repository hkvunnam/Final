package hariKrishna.Grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import hariKrishna.PageObject.LoginPage;

public class TestA {

	@Test
	public void loginFail() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setPlatform(Platform.ANY);
		caps.setBrowserName("chrome");
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.121:4444"), caps);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		LoginPage log = new LoginPage(driver);
		log.goTo();
		log.login("hk@email.com", "asjcbw49");
		Assert.assertEquals("Incorrect email or password.", log.getError());
		driver.close();
	}

}
