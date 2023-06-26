package hariKrishna.Tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hariKrishna.TestComponents.BaseTest;

public class Test3 extends BaseTest {

	@Test(dataProvider = "excel")
	public void loginFail(String user, String pass) {
		loginPage.login(user, pass);
		Assert.assertEquals("Incorrect email or password.", loginPage.getError());
	}

	@DataProvider(name = "excel")
	public Object[][] sendData() throws IOException {
		Object[][] data = getExcelData("D:\\Edu\\Java\\data\\dataSheet.xlsx", "LoginPass");
		return data;
	}

	@Test(dataProvider = "sql")
	public void loginFail2(String user, String pass) {
		loginPage.login(user, pass);
		Assert.assertEquals("Incorrect email or password.", loginPage.getError());
	}

	@DataProvider(name = "sql")
	public Object[][] getSqlData() throws SQLException {
		HashMap<String, Object> data = getSql("fail", "root", "Krish@59", "select * from loginfail where id='1'");
		HashMap<String, Object> data1 = getSql("fail", "root", "Krish@59", "select * from loginfail where id='2'");
		HashMap<String, Object> data2 = getSql("fail", "root", "Krish@59", "select * from loginfail where id='3'");
		return new Object[][] { { data.get("user"), data.get("pass") }, { data1.get("user"), data1.get("pass") },
				{ data2.get("user"), data2.get("pass") } };
	}

}
