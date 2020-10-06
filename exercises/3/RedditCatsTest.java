// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class RedditCatsTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver", "Windows/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-notifications");
    driver = new ChromeDriver(options);
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void testCat() {
    driver.get("https://www.reddit.com/r/cats/");
    driver.manage().window().setSize(new Dimension(1920, 1016));
    assertThat(driver.findElement(By.cssSelector(".\\_2yYPPW47QxD4lFQTKpfpLQ")).getText(), is("Cats"));
  }
  @Test
  public void testJoin() {
    driver.get("https://www.reddit.com/r/cats/");
    driver.manage().window().setSize(new Dimension(1920, 1016));
    assertThat(driver.findElement(By.cssSelector(".\\_1Q_zPN5YtTLQVG72WhRuf3 > .\\_3VgTjAJVNNV7jzlnwY-OFY")).getText(), is("JOIN"));
  }
  @Test
  public void testSignUp() {
    driver.get("https://www.reddit.com/r/cats/");
    driver.manage().window().setSize(new Dimension(1920, 1016));
    {
      WebElement element = driver.findElement(By.xpath("//a[contains(text(),\'sign up\')]"));
      String attribute = element.getAttribute("href");
      vars.put("herf", attribute);
    }
    assertEquals(vars.get("herf").toString(), "https://www.reddit.com/register/?dest=https%3A%2F%2Fwww.reddit.com%2Fr%2Fcats%2F");
  }
  @Test
  public void testRule11and12() {
    driver.get("https://www.reddit.com/r/cats/");
    driver.manage().window().setSize(new Dimension(1920, 1016));
    js.executeScript("window.scrollTo(0,565)");
    js.executeScript("window.scrollTo(0,1208)");
    js.executeScript("window.scrollTo(0,1700)");
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".\\_8ZLJI1-ZiP7pHJ_yO1L4Z:nth-child(11) .\\_3P6gMBKOhtWWrytWm-8hc:nth-child(1)"));
      assert(elements.size() > 0);
    }
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".\\_8ZLJI1-ZiP7pHJ_yO1L4Z:nth-child(12) .\\_3P6gMBKOhtWWrytWm-8hc:nth-child(1)"));
      assert(elements.size() == 0);
    }
  }
  @Test
  public void testSmellyCat() {
    driver.get("https://www.reddit.com/r/cats/");
    driver.manage().window().setSize(new Dimension(1920, 1016));
    {
      WebElement element = driver.findElement(By.cssSelector(".Z_HUY3BUsGOBOtdmH94ZS"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.id("header-search-bar")).click();
    driver.findElement(By.id("header-search-bar")).sendKeys("smelly cat");
    driver.findElement(By.id("header-search-bar")).sendKeys(Keys.ENTER);
    assertThat(driver.findElement(By.cssSelector(".\\_3j9XjJayuKq7dJ8huVnCuS")).getText(), is("smelly cat"));
    driver.findElement(By.cssSelector(".N0zmIZbfRSCGk2rUOGHSS")).click();
    assertThat(driver.findElement(By.cssSelector(".\\_3j9XjJayuKq7dJ8huVnCuS")).getText(), is("smelly cat"));
  }
  @Test
  public void testRule3() {
    driver.get("https://www.reddit.com/r/cats/");
    driver.manage().window().setSize(new Dimension(1920, 1016));
    js.executeScript("window.scrollTo(0,571)");
    js.executeScript("window.scrollTo(0,1700)");
    assertThat(driver.findElement(By.xpath("//div[@id=\'SHORTCUT_FOCUSABLE_DIV\']/div[2]/div/div/div/div[2]/div[3]/div[2]/div/div[5]/div/div[2]/div[3]/div/div[2]/div")).getText(), is("No NSFW, animal abuse, or cruelty"));
  }
}
