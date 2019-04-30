package pages;

import elements.Button;
import elements.Label;
import elements.TextInput;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RegisterPage {

    private RemoteWebDriver driver;
    private TextInput usernameInput;
    private By usernameInputSelector = By.id("name");
    private TextInput emailRegistrationInput;
    private By emailInputSelector = By.id("email");
    private TextInput passwordInput;
    private By passworInputSelector = By.id("password");
    private Button signupButton;
    private By signupButtonSelector = By.id("signup");
    private Label welcomeMessage;
    private By welcomeMessageSelector = By.xpath("//span[contains(text(),'Witaj w Trello!')]");

    public RegisterPage(RemoteWebDriver driver) {
        this.driver = driver;
        this.usernameInput = new TextInput(this.driver, usernameInputSelector);
        this.emailRegistrationInput = new TextInput(this.driver, emailInputSelector);
        this.passwordInput = new TextInput(this.driver, passworInputSelector);
    }

    private RegisterPage prepareRegistrationData() {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        usernameInput.type(person.getFullName());
        emailRegistrationInput.type(person.getEmail());
        passwordInput.type(person.getPassword());
        return new RegisterPage(driver);
    }

    public RegisterPage fillInRegistrationData() {
        prepareRegistrationData();
        return new RegisterPage(driver);
    }

    public RegisterPage clickRegisterButton() {
        this.signupButton = new Button(this.driver, signupButtonSelector);
        signupButton.click();
        return new RegisterPage(driver);
    }

    public String getWelcomeMessage() {
        this.welcomeMessage = new Label(this.driver, welcomeMessageSelector);
        return welcomeMessage.read();
    }

    public boolean isUserRegistered() {
        String welcomeMessage = "Witaj w Trello!";
        return welcomeMessage.equals(getWelcomeMessage());
    }
}
