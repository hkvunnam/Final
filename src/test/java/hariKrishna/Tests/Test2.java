package hariKrishna.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hariKrishna.TestComponents.BaseTest;

public class Test2 extends BaseTest {

	@Test(dataProvider = "json")
	public void loginFail(HashMap<String, String> input) {
		loginPage.login(input.get("email"), input.get("pass"));
		Assert.assertEquals("Incorrect email or password.", loginPage.getError());
	}

	@DataProvider(name = "json")
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\main\\java\\hariKrishna\\Data\\data.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)}};
	}

}
