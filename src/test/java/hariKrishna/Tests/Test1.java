package hariKrishna.Tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hariKrishna.TestComponents.BaseTest;

public class Test1 extends BaseTest{
	
	@Test(dataProvider="array")
	public void loginFail(String user, String pass) {
		loginPage.login(user, pass);
		Assert.assertEquals("Incorrect email or password.", loginPage.getError());
	}
	
	@DataProvider(name="array")
	public Object[][] sendData() {
		Object [][] data = new Object[2][2];
		data[0][0] = "hk@email.com";
		data[0][1] = "dvi34ioj";
		data[1][0] = "vunn@email.com";
		data[1][1] = "SCN938tfj";
		return data;
	}
	
	@Test(dataProvider="hash")
	public void LoginFail2(String user, String pass) {
		loginPage.login(user, pass);
		Assert.assertEquals("Incorrect email or password.", loginPage.getError());
	}
	
	@DataProvider(name="hash")
	public Object[][] getData() {
		HashMap<String, String> data = new HashMap<>();
		data.put("email", "hk@email.com");
		data.put("pass", "sdvne9urbvn");
		HashMap<String, String> data1 = new HashMap<>();
		data1.put("email", "vunn@email.com");
		data1.put("pass", "sdvnefdb9urbvn");
		return new Object[][] {{data.get("email"), data.get("pass")},{data1.get("email"), data1.get("pass")}}; 
	}

}
