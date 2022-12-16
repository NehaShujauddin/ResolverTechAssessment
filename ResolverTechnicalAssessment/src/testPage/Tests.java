package testPage;

import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.Assert;

public class Tests {
	public String baseUrl = "https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=0bf4c12eb7&attid=0.1&permmsgid=msg-f:1752227507645654581&th=185129a26ee32635&view=att&disp=inline&ser=1&saddbat=ANGjdJ_BnXyEuYIIa4kOI9f6hV3TvdbgmT2uWVwHzJxKPPaKR6QHHu6YzlshHkeOczLCUvB21lixk6CsP4suk0i6wsHvD50F6xUURHVHf6OqOZnkQPmJ_GvU6I2YOw2bSkXJx_FK7DEkzMgKo_9dx9PAQ5rUnU6GvCgacIA4IlCWCZ4J5I1hc33NV1Fq4svAhvvj0izPHY-b5zyKzzMV1O8wA-sj68bBku-SdhzAqwGhrcmN0xZObmWcwCM0z_pQSusu7MZ8d94mUh3M6a0DzzsHAVYybXtqzT3QxbSj9PweiGx2fzZToDbrAMY6wgl9fDE1uIUwTRUJSPTZ0eJKez4ijY3Eu-xrFkXlzqZwpqnxFJ33sv4Q2XYJiEk8r8IDMj-5jcypdkmEZ1KBoGtwhFHUlASpDuThZmw81vbopbN2MkX90fYmjco6obHEi_tlKn-ANZHN1qc6RRss9UiNXPZCTQDXSA905vN9zV0bL9uFDp6-OeIaJ0JhCMVaqWiqu_Uwrfhv9L_3mKl3TvjL3EP51rxG48pKBz73A7nQCK7qo0Wnk4X3Q1Tq2JG5SO9wGRuYynZeOS7epKn0jleoUGESKPKW-8NTCGjPYh1P1JU_UbFy_84OJykfmGMQjKYmw_8VmGGvFtJs0dlI7Iy3E2Q_oLoDR-70IxkXTP54fd2K9cud7LGbbLyC6_qJE_y1DhipuVb_2T6Q2orP81q8m34Ba0X6vIsynBKlPpsLA__Ik1OdB5RZcFX97auor_sgf8cKGMFmq_nRk_Aqc1rn2MV9KgGokmEpSBmk8H2BuQSqq_SVQ3lEO39_D9Ij4tFAif5SrIBSavI0TPLBT6m1zpEUiP9BE0O9t4OdRWt21ZUQ-XJ3IJ5EszAcwKMwsHM6jafH7czcK77NjwLVuuv6vz9XYsa1YxoMP3hiwxH-hkIwT2urTvPX3CL7h6SBeHQ";
	public WebDriver driver;
	// Method to find the value of any cell on the grid
	public String tableData(int x, int y)
	{
		String cellText = "";
		// identify table
    	WebElement simpleTable = driver.findElement(By.xpath("//*[@id=\"m_test-6-div\"]/div/table"));
    	// count rows with size() method
    	List<WebElement> rows = simpleTable.findElements(By.tagName("tr"));
    	int rows_cnt = rows.size();
    	//iterate the rows of table
    	for (int i = 0;i < rows_cnt; i++) 
    	{
    		if(i==x)
    		{
    			// count the columns with size() method
    			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
    			int cols_cnt = cols.size();
    			// iterate the columns of table
    			for (int j = 0;j < cols_cnt; j++) 
    			{
    				if(j==y)
    				{
    				// get the cell text
    				cellText = cols.get(j).getText();
    				}
    			}
    		}
    	}
	return cellText;  
    }
		
    @BeforeTest
    public void navigateToHomePage() {
    	// Path of the chrome driver that will be the local directory passed
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Neha Shujauddin\\eclipse\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
		// Navigate to the home page of the site to be tested
    	driver.get("baseUrl");
    	// Maximize the window size of the browser
    	driver.manage().window().maximize();
    }
  
    @Test
    public void login() {
    	WebElement emailId = driver.findElement(By.id("m_inputEmail"));
        WebElement password = driver.findElement(By.id("m_inputPassword"));
        WebElement signIn = driver.findElement(By.xpath("//button[text()='Sign in']"));
        // Assert if email address and password inputs are present 
        Assert.assertEquals(true, emailId.isDisplayed());
        Assert.assertEquals(true, password.isDisplayed());
        Assert.assertEquals(true, signIn.isDisplayed());
        // Enter the email address
        emailId.sendKeys("xyz@gmail.com");
        // Enter the password
        password.sendKeys("xyz12345");
        // Click on Sign in button
        signIn.click();
    }
    
    @Test
    public void listItem() {
    	// Assert the number of values in the list group
    	List<WebElement> listGroup = driver.findElements(By.xpath("//div[@id='m_test-2-div']//ul[@class='m_list-group']//li[contains(@class='m_list-group-item')]"));
    	Assert.assertEquals(3, listGroup.size());
      	// Assert the second list item's value
    	Assert.assertEquals("List Item 2", listGroup.get(1).getText());
      	// Assert the second list item's badge value
    	WebElement listItem2Badge = driver.findElement(By.xpath("listGroup[1]//span[contains(@class='m_badge_primary')]"));
    	Assert.assertEquals(6, listItem2Badge.getText());     
    }
    
    @Test
    public void selectOption() {
    	// Assert that "Option 1" is the default selected value
    	Select dropdown = new Select(driver.findElement(By.className("m_dropdown-menu")));
    	String defaultOption = dropdown.getFirstSelectedOption().getText();
    	Assert.assertEquals("Option 1", defaultOption);
    	// Select "Option 3" from the select list
    	dropdown.selectByVisibleText("Option 3");
    }
    
    @Test
    public void buttonsEnabledDisabled() {
    	// Assert that the first button is enabled 
    	WebElement firstButton = driver.findElement(By.xpath("//div[@id='m_test-4-div']//button[contains(@class='m_btn-primary')]"));
    	Assert.assertEquals(true, firstButton.isEnabled());
    	// Assert that the second button is disabled 
    	WebElement secondButton = driver.findElement(By.xpath("//div[@id='m_test-4-div']//button[contains(@class='m_btn-secondary')]"));
    	Assert.assertEquals(false, secondButton.isEnabled());
    }
    
    @Test
    public void buttonWait() {
    	// Wait for the button to be displayed
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='m_test-5-div']//button[@id='m_test5-button']")));
    	WebElement button = driver.findElement(By.xpath("//div[@id='m_test-5-div']//button[@id='m_test5-button']"));
    	button.click();
    	// Assert if the success message is displayed
    	WebElement alertMessage = driver.findElement(By.id("m_test5-alert"));
    	Assert.assertEquals(true, alertMessage.isDisplayed());
    	Assert.assertEquals("You clicked a button!", alertMessage.getText());
    	// Assert that button is now disabled
    	Assert.assertEquals(false, button.isEnabled());               
    }
    
    @Test
    public void cellValue() {
    	// Calling the method to find the value of the cell at coordinates 2,2
    	String cellValue = tableData(2,2);
    	// Assert the value of the cell at coordinates 2,2
		Assert.assertEquals("Ventosanzap", cellValue);
    }  
      
    @AfterTest
    public void endSession() {
    	// close the browsers launched during the session
    	driver.quit();
    }
}
