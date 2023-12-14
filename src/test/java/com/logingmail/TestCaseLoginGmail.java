package com.logingmail;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.fail;

public class TestCaseLoginGmail {
  private WebDriver driver;
    private boolean acceptNextAlert = true;
  private final StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
public void setUp() throws Exception {
  //WebDriverManager.chromedriver().setup();
  //driver = new ChromeDriver();
  //WebDriverManager.edgedriver().setup();
  //driver = new EdgeDriver();
  WebDriverManager.firefoxdriver().setup();
  driver = new FirefoxDriver();
  DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setCapability("proxy", new Proxy().setHttpProxy("192.168.1.1:8080"));
  driver = new FirefoxDriver();
  
      String baseUrl = "https://www.google.com/";
      driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
      JavascriptExecutor js = (JavascriptExecutor) driver;
}
  @Test
  public void testLoginGmailTestCase() throws Exception {
    //driver.get("https://accounts.google.com/InteractiveLogin/signinchooser?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Fhl%3Des&emr=1&hl=es&ltmpl=default&ltmplcache=2&osid=1&passive=true&rm=false&scc=1&service=mail&ss=1&ifkv=ASKXGp2eRB1iMxYyvzaoHUUhVjhecLkHDXZtGgvvnr37nxX2BbK7uBLlM2e8Xo9r4UC1Q9DGU5Eh&theme=glif&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    //driver.get("https://accounts.google.com/InteractiveLogin/signinchooser?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%26ogbl%2F&emr=1&ltmpl=default&ltmplcache=2&osid=1&passive=true&rm=false&scc=1&service=mail&ss=1&ifkv=ASKXGp1OlySKAAGY4gU2pWBFuCkfw2FEBTRblrVYkNd39jchqlS4EYAg9htgQICIhVGoBNqQMtZE&theme=glif&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    //driver.get("https://www.google.com/intl/es/gmail/about/");
    driver.get("https://accounts.google.com/signin");
    //driver.findElement(By.xpath("//a[normalize-space()='Inicia sesión']")).click();
    driver.findElement(By.id("identifierId")).isDisplayed();
    driver.findElement(By.xpath("//input[@id='identifierId']")).clear();
    driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("lfja333@gmail.com");
    driver.findElement(By.xpath("//span[normalize-space()='Siguiente']")).click();
    driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).click();
    driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("Mariareina.11");
    driver.findElement(By.xpath("//span[normalize-space()='Siguiente']")).click();
    driver.get("https://mail.google.com/mail/u/0/?hl=es");
    driver.findElement(By.xpath("//header[@id='gb']/div[2]/div[3]/div/div[2]/div/a")).click();
    driver.switchTo().frame(4);
    driver.get("https://accounts.google.com/InteractiveLogin/signinchooser?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&emr=1&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&osid=1&passive=1209600&service=mail&ifkv=ASKXGp29cAa674yAfOE1ZmicjSS7O0I8t0KY_XKsf8DbDUkBo8ocRc4UvE-va6lEvg_oFhVQPlvz&theme=glif&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    driver.findElement(By.xpath("//div[contains(text(),'Sign out')]")).click();
    driver.close();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
   // driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
