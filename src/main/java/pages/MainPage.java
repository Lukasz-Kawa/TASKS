package pages;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MainPage {

    private RemoteWebDriver driver;
    private Button logInButton;
    private By loginButtonSelector = By.xpath("//a[@class='btn btn-sm btn-link text-white']");
    private Button registerButton;
    private By registerButtonSelector = By.xpath("//a[@class='btn btn-sm bg-white font-weight-bold']");

    MainPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public MainPage navigateTo() {
        driver.get("http://trello.com/pl");
        return new MainPage(driver);
    }

    public LoginPage chooseLogInButton() {
        this.logInButton = new Button (this.driver, loginButtonSelector);
        logInButton.click();
        return new LoginPage(driver);
    }

    public RegisterPage chooseRegisterButton() {
        this.registerButton = new Button (this.driver, registerButtonSelector);
        registerButton.click();
        return new RegisterPage(driver);
    }
}