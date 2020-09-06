package br.ce.Ana.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testeGoogle {

	public WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Ana\\drivers\\chromedriver.exe");
		this.driver = new ChromeDriver();
	}

	@Test
	public void teste() throws InterruptedException {
//		WebDriver driver = new FirefoxDriver();

		this.driver.get("http://www.google.com");
		Assert.assertEquals("Google", this.driver.getTitle());
		this.driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input"))
				.sendKeys("cachorrinhos fofos" + Keys.ENTER);
		Thread.sleep(2000);

		WebElement input = this.driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[2]/div/div[2]/input"));

		Assert.assertEquals("cachorrinhos fofos", input.getAttribute("value"));

	}

	@After
	public void tearDown() {
		this.driver.quit();
	}
}
