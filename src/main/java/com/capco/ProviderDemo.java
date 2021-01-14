package com.capco;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ProviderDemo {

	private static String filePath ="C:\\Users\\SHNN\\eclipse-workspace\\DemoSecond\\src\\main\\java\\com\\capco\\demo.xlsx";
	static ExcelReader reader=new ExcelReader();
	public static WebDriver driver;
	PracticePage practicePage;
	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public static void startTest()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
	}

	@Test(dataProvider = "getData")
	public void read(Object[] obj) throws InterruptedException {	
		practicePage.clickOn_RadioButtonAndCheckbox(obj[0].toString());
		practicePage.clickOn_Checkbox(obj[1].toString());
		practicePage.clickOn_DropDown(obj[2].toString());
		practicePage.select_MultipleExample(obj[3].toString());
		practicePage.clickOn_AlertButton(obj[4].toString(),driver);	
		practicePage.clickOn_HideButton(obj[5].toString(), driver);
	}

	@DataProvider(name="getData")
	public Object[][] readExcel() throws InvalidFormatException, IOException {
		return ExcelReader.readExcel(filePath);
	}

	@BeforeMethod
	public void setUp() throws Exception
	{
		driver=BrowserSelection.selectBrowser();		   
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://letskodeit.teachable.com/p/practice");
		practicePage=new PracticePage(driver);

	}

	@AfterMethod
	public void burnDown(){
		driver.quit();
		report.endTest(test);
		report.flush();

	}

}
