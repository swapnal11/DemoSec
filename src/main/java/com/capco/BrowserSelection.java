package com.capco;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserSelection {

	
	public static WebDriver selectBrowser() throws Exception {

		FileInputStream fis = null;
		File configfile = new File("C:\\Users\\SHNN\\eclipse-workspace\\DemoSecond\\src\\test\\java\\Configuration.properties");
		fis = new FileInputStream(configfile);
		Properties prop = new Properties();
		prop.load(fis); 
		WebDriver driver = null;
		String browser = prop.getProperty("browser");

		while (browser == null) {
			System.out.println("Browser is not specified in Configuration file. Terminating process !!!");
			System.exit(0);
		}
		
		if (browser.equalsIgnoreCase("Edge")) {			
			System.setProperty("webdriver.edge.driver" ,prop.getProperty("edgedriverpath"));
			driver = new EdgeDriver();
			System.out.println("Browser selected for testing is : Edges");

		} else if (browser.equalsIgnoreCase("chrome1")) {
			
			System.setProperty("webdriver.chrome.driver",prop.getProperty("chromedriverpath"));
			driver = new ChromeDriver();
			System.out.println("Browser selected for testing is :  Google Chrome");
		}
		else {
			System.out.println("Selected browser value should be either Edge or chrome or ie --> Update in Configuration file is required");
			System.exit(0);
		}

		return driver;

	}


}
