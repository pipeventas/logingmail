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
import java.util.Random;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.fail;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxOptions;

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
  // Configurar proxy en firefox que es independiente del PC - para evitar que Google identique que Iniciar sesión con un nave
  //DesiredCapabilities capabilities = new DesiredCapabilities();
  //capabilities.setCapability("proxy", new Proxy().setHttpProxy("192.168.1.1:8080"));
  //driver = new FirefoxDriver();
    // Crear una instancia de la clase FirefoxOptions
      FirefoxOptions options = new FirefoxOptions();
      // Crear una instancia del navegador Firefox utilizando la instancia de FirefoxOptions
      driver = new FirefoxDriver(options);
      driver.get("https://mail.google.com/");
      Proxy proxy = new Proxy();
      proxy.setHttpProxy("192.168.1.1:8080");
      proxy.setSslProxy("192.168.1.1:8080");
      options.setProxy(proxy);
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
    // Generar un intervalo de tiempo aleatorio - para evitar que Google identique que Iniciar sesión con un navegador admitido -Están siendo controlados por software automatizado y no por personas.
    Random random = new Random();
    int delay = random.nextInt(5) + 1;

    // Esperar el intervalo de tiempo aleatorio
    try {
      Thread.sleep(delay * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // Generar un movimiento aleatorio del mouse
    int x = random.nextInt(driver.manage().window().getSize().width);
    int y = random.nextInt(driver.manage().window().getSize().height);
    // Realizar el movimiento del mouse
    Actions actions = new Actions(driver);
    actions.moveByOffset(x, y).perform();

    //driver.findElement(By.xpath("//a[normalize-space()='Inicia sesión']")).click();
    driver.findElement(By.id("identifierId")).isDisplayed();
    driver.findElement(By.xpath("//input[@id='identifierId']")).clear();
    driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("gmltest26@gmail.com");
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
