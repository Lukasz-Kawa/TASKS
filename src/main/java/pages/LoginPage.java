package pages;

import elements.Button;
import elements.Label;
import elements.TextInput;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage {

    private RemoteWebDriver driver;
    private TextInput emailInput;
    private By emailInputSelector = By.id("user");
    private TextInput passwordInput;
    private By passworInputSelector = By.id("password");
    private Button loginButton;
    private By loginButtonSelector = By.id("login");
    private Label errorMessage;
    private By errorMessageSelector = By.xpath("//p[@class='error-message']");

    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        this.emailInput = new TextInput(this.driver, emailInputSelector);
        this.passwordInput = new TextInput(this.driver, passworInputSelector);
    }

    private LoginPage prepareLoginData() {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        emailInput.type(person.getEmail());
        passwordInput.type(person.getPassword());
        return new LoginPage(driver);
    }

    public LoginPage fillInPreparedData() {
        prepareLoginData();
        return new LoginPage(driver);
    }

    public LoginPage clickLoginButton() {
        this.loginButton = new Button(this.driver, loginButtonSelector);
        loginButton.click();
        return new LoginPage(driver);
    }

    public String getErrorMessageInformation() {
        this.errorMessage = new Label(this.driver, errorMessageSelector);
        return errorMessage.read();
    }

    public boolean isLoginRequestFailed() {
        String requestError = "To nie jest konto dla tego e-maila";
        return requestError.equals(getErrorMessageInformation());
    }
}


