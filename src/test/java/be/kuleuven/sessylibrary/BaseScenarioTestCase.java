package be.kuleuven.sessylibrary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.Duration;

public abstract class BaseScenarioTestCase {

    private Class<? extends RemoteWebDriver> driverClass;
    protected WebDriver driverInstance;
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(10);

    private String getFullPathOf(String resourceName) throws URISyntaxException {
        return Paths.get(getClass().getClassLoader().getResource(resourceName).toURI()).toFile().getAbsolutePath();
    }

    protected BaseScenarioTestCase() {
        try {
            var os = System.getProperty("os.name").toLowerCase();
            if(os.contains("mac")) {
                // See https://github.com/mozilla/geckodriver for newer Firefox versions. Also works on ARM64
                System.setProperty("webdriver.chrome.driver", getFullPathOf("geckodriver"));
                this.driverClass = FirefoxDriver.class;
            } else if(os.contains("win")) {
                // See https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/ - assumes x64
                System.setProperty("webdriver.edge.driver", getFullPathOf("msedgedriver.exe"));
                this.driverClass = EdgeDriver.class;
            } else {
                // See latest in https://chromedriver.storage.googleapis.com/index.html - assumes AMD64
                System.setProperty("webdriver.chrome.driver", getFullPathOf("chromedriver_linux64"));
                this.driverClass = ChromeDriver.class;
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected WebDriver driver() {
        if(driverInstance == null) {
            try {
                driverInstance = driverClass.getDeclaredConstructor().newInstance();
            } catch(Exception ex) {
                throw new IllegalArgumentException("Could not initialize driver " + driverClass.getName(), ex);
            }
        }

        return driverInstance;
    }

    protected void waitUntilText(WebElement el, String text) {
        new WebDriverWait(driver(), WAIT_SECONDS).until(ExpectedConditions.textToBePresentInElement(el, text));
    }

    protected void waitUntilText(By by, String text) {
        new WebDriverWait(driver(), WAIT_SECONDS).until(ExpectedConditions.textToBe(by, text));
    }

    protected void waitFor(By by) {
        new WebDriverWait(driver(), WAIT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @BeforeAll
    public static void startDropWizardApp() throws Exception {
        new SessyLibApplication().run("server", "app.yml");
    }

    @AfterEach
    protected void tearDownDriver() {
        if(driverInstance != null) {
            driverInstance.close(); // also quits automatically.
        }
    }

}
