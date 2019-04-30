package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Test {

    RemoteWebDriver driver;

    @BeforeClass
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
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
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "PNG", new File(System.getProperty("user.dir") + "/ErrorScreenshots/FullPageScreenshot.png"));
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
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "PNG", new File(System.getProperty("user.dir") + "/ErrorScreenshots/FullPageScreenshot.png"));
        }
    }
}

