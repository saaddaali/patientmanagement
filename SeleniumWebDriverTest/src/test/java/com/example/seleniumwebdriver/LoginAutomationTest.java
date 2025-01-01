package com.example.seleniumwebdriver;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // This allows ordering of tests
public class LoginAutomationTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Configurer WebDriver et les options de Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1680, 944));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)  // This specifies that this test will run first
    public void testLoginValid() {
        driver.get("http://localhost:4200");

        WebElement primaryButton = driver.findElement(By.cssSelector(".primary-button"));
        assertNotNull(primaryButton, "Le bouton principal est introuvable.");
        primaryButton.click();

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("doctor");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123");

        WebElement submitButton = driver.findElement(By.cssSelector(".submit"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/app/doctor"));

        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:4200/app/doctor", currentUrl, "La redirection après connexion a échoué.");
    }

    @Test
    @Order(2)  // This specifies that this test will run second
    public void testInvalidCredentialsToast() {
        driver.get("http://localhost:4200");

        WebElement primaryButton = driver.findElement(By.cssSelector(".primary-button"));
        assertTrue(primaryButton.isDisplayed(), "Le bouton principal est introuvable.");
        primaryButton.click();

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("doctor");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("wrongpassword");

        WebElement submitButton = driver.findElement(By.cssSelector(".submit"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast-detail")));

        String toastText = toastMessage.getText();
        assertTrue(toastText.matches("Invalid credentials"), "Le message de toast est incorrect. Message reçu: " + toastText);
    }

    @Test
    @Order(3)  // This specifies that this test will run third
    public void testAccountLockedAfterThreeWrongAttempts() {
        driver.get("http://localhost:4200");

        WebElement primaryButton = driver.findElement(By.cssSelector(".primary-button"));
        assertNotNull(primaryButton, "Le bouton principal est introuvable.");
        primaryButton.click();

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("doctor");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("wrongpassword");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitButton = driver.findElement(By.cssSelector(".submit"));

        for (int i = 0; i < 5; i++) {
            submitButton.click();
        }

        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast-detail")));
        String toastText = toastMessage.getText();

        // Regex to match the "Account is locked" message with dynamic time
        String regex = "Account is locked. Please try again in";
        assertTrue(toastText.contains(regex), "Le message de toast est incorrect. Message reçu: " + toastText);
    }

    @Test
    @Order(4)  // This specifies that this test will run last
    public void testFillAllFields() {
        driver.get("http://localhost:4200");

        WebElement primaryButton = driver.findElement(By.cssSelector(".primary-button"));
        assertTrue(primaryButton.isDisplayed(), "Le bouton principal est introuvable.");
        primaryButton.click();

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("doctor");

        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.cssSelector(".submit"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast-detail")));

        String toastText = toastMessage.getText();

        assertTrue(toastText.matches("Fill in all fields"), "Le message de toast est incorrect. Message reçu: " + toastText);
    }
}
