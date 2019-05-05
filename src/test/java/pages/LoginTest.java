package pages;

import addins.Snapshot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    private RemoteWebDriver driver;

    @BeforeClass
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @org.junit.Test
    public void isLoginUnsuccessful() throws Exception {
        try {
            MainPage mainPage = new MainPage(driver);
            mainPage
                    .navigateTo()
                    .chooseLogInButton()
                    .fillInPreparedData()
                    .clickLoginButton()
                    .isLoginRequestFailed();
        } finally {
            Snapshot snapshot = new Snapshot(driver, "src/test/Screenshots");
            snapshot.takeSnapshot("Unsucessful login attempt");
        }
    }

    @org.junit.Test
    public void isRegistrationCompleted() throws Exception {
        try {
            MainPage mainPage = new MainPage(driver);
            mainPage
                    .navigateTo()
                    .chooseRegisterButton()
                    .fillInRegistrationData()
                    .clickRegisterButton()
                    .isUserRegistered();
        } finally {
            Snapshot snapshot = new Snapshot(driver, "src/test/Screenshots");
            snapshot.takeSnapshot("Registration Confirmation");
        }
    }

}

