package com.capco;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PracticePage {

	WebDriver driver;
	JavascriptExecutor jse;
	WebElement element;
	Select select;
	
	public PracticePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "multiple-select-example")
	private WebElement MultipleSelect;

	@FindBy(id = "benzradio")
	private WebElement RadioBtn;

	@FindBy(id = "carselect")
	private WebElement DropDown;

	@FindBy(id = "benzcheck")
	private WebElement CheckBox;

	@FindBy(id = "openwindow")
	private WebElement HandelWindow;

	@FindBy(id = "name")
	private WebElement AlertEnterName;

	@FindBy(id = "alertbtn")
	private WebElement AlertButton;

	@FindBy(id = "confirmbtn")
	private WebElement ConfirmButton;

	@FindBy(xpath = "//*[@id='product']")
	private WebElement WebTable;

	@FindBy(xpath = "//input[@id='name']")
	private WebElement DisplayEnterName;

	@FindBy(id = "hide-textbox")
	private WebElement HideButton;

	@FindBy(id = "name")
	private WebElement HideTextbox;

	@FindBy(id = "show-textbox")
	private WebElement ShowButton;

	@FindBy(id = "mousehover")
	private WebElement MouseHover;

    @FindBy(xpath = "//div[@class='mouse-hover-content']//a[text()='Top']")
    private WebElement Top_MouseHover;


	@FindBy(xpath = "//h1[text()='Practice Page']")
	private WebElement PracticePage_Header;

	@FindBy(id = "search-courses")
	private WebElement SearchCourse_Iframe; 

	public void clickOn_RadioButtonAndCheckbox(String radioButton) {
		RadioBtn.click();				
	}

	public void clickOn_Checkbox(String checkbox) {		
		CheckBox.click();
		System.out.println("Checkbox  "+checkbox+" is selected");        

	}

	public void clickOn_DropDown(String string) {
		DropDown.click();
		System.out.println("Dropdown Element  "+string+" is selected");
	}

	public void select_MultipleExample(String string) throws InterruptedException {
		MultipleSelect.click();
		System.out.println("Multiple Element  "+string+" is selected");
	}

	public void  clickOn_OpenWindow(){
		String parentHandle = driver.getWindowHandle();	
		WebElement openWindow = driver.findElement(By.id("openwindow"));
		openWindow.click();
		Set<String> handles = driver.getWindowHandles();
		for (String handle: handles) {
		}
		driver.switchTo().window(parentHandle);	
	}

	public void  clickOn_OpenTab() throws InterruptedException{
		String parentHandle = driver.getWindowHandle();
		WebElement opentab= driver.findElement(By.id("opentab"));
		opentab.click();
		Thread.sleep(20);
		String currenturl = driver.getCurrentUrl();
		String expectedurl = "https://letskodeit.teachable.com/courses";
		if(currenturl == expectedurl)
		{
			System.out.println("New tab is opened");
		}
		driver.navigate().to("https://letskodeit.teachable.com/p/practice");
		driver.switchTo().window(parentHandle);
	}

	public void  clickOn_Confirmbutton(String name) throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(By.id(name)).clear();
		driver.findElement(By.id(name)).sendKeys("Demo");
		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void  clickOn_AlertButton(String string,WebDriver driver) throws InterruptedException{
		Thread.sleep(1000);
        AlertEnterName.clear();
        AlertEnterName.sendKeys(string);
        AlertButton.click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
	}

	public void  clickOn_ShowButton()
	{
		ShowButton.click();
	}

	public void  clickOn_HideButton(String string,WebDriver driver)
	{
		System.out.println("Text Box Displayed: " + HideTextbox.isDisplayed());
		HideTextbox.sendKeys(string);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", HideTextbox);
		HideButton.click();
		System.out.println("Clicked on hide button");
	}


	public void  clickOn_MouseHover(String string,WebDriver driver) throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(Top_MouseHover).click().perform();
		System.out.println("Mouse Hover" +string+" is selected");
	}

	public void  clickOn_iFrameExample(String course,WebDriver dr) throws InterruptedException
	{
		Thread.sleep(3000);
		dr.switchTo().frame("courses-iframe");
		SearchCourse_Iframe.sendKeys(course);

	}






}

