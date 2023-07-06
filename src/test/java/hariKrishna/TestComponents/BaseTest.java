package hariKrishna.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Table.Cell;

import hariKrishna.PageObject.LoginPage;

public class BaseTest {

	WebDriver driver;
	public LoginPage loginPage;

	private WebDriver initializeBrowser() throws IOException {
		Properties pro = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\hariKrishna\\Data\\GlobalData.properties");
		pro.load(file);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: pro.getProperty("browser");
		if (browserName.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.contains("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.contains("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	@BeforeMethod
	public LoginPage landngPage() throws IOException {
		loginPage = new LoginPage(initializeBrowser());
		loginPage.goTo();
		return loginPage;
	}

	@AfterMethod
	public void close() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonData(String file) throws IOException {
		String path = FileUtils.readFileToString(new File(file), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(path, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
	}

	public Object[][] getExcelData(String path, String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int sheets = workbook.getNumberOfSheets();
		XSSFSheet sheet = null;
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetName)) {
				sheet = workbook.getSheetAt(i);
				break;
			}
		}
		int rowIndex = sheet.getLastRowNum();
		XSSFRow row = sheet.getRow(0);
		int columnIndex = row.getLastCellNum();
		Object[][] data = new Object[rowIndex][columnIndex];
		for (int i = 0; i < rowIndex; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < columnIndex; j++) {
				XSSFCell cell = row.getCell(j);
				if (cell.getCellType().equals(CellType.STRING)) {
					data[i][j] = cell.getStringCellValue();
				} else {
					data[i][j] = NumberToTextConverter.toText(cell.getNumericCellValue());
				}
			}
		}
		return data;
	}

	public HashMap<String, Object> getSql(String dB, String user, String pass, String query) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mySql://localhost:3306/" + dB, user, pass);
		Statement sat = con.createStatement();
		ResultSet res = sat.executeQuery(query);
		HashMap<String, Object> data = new HashMap<>();
		while (res.next()) {
			data.put("user", res.getObject("username"));
			data.put("pass", res.getObject("password"));
		}
		return data;
	}

	public ExtentReports getReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "//reports//index.html");
		reporter.config().setDocumentTitle("Final Automation");
		reporter.config().setReportName("Results Automation");

		ExtentReports reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Hari ", "Tester");
		return reports;
	}
	
	public String getScreeshot(WebDriver driver,String testName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"//reports//screenshots//"+ testName + ".png"));
		return System.getProperty("user.dir")+"//reports//screenshots//"+ testName + ".png";
	}

}
