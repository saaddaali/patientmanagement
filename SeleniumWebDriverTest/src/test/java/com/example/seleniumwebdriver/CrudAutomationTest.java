package com.example.seleniumwebdriver;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // This allows ordering of tests
public class CrudAutomationTest {

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
    @Order(1)
    public void createPatient() {
        // Open the application and set the window size
        driver.get("http://localhost:4200/");
        driver.manage().window().setSize(new Dimension(1680, 944));

        // Log in to the application
        driver.findElement(By.cssSelector(".primary-button")).click();
        driver.findElement(By.id("username")).sendKeys("doctor");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.cssSelector(".submit")).click();

        // Navigate to the "Patients" section
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement patientsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Patients")));
        patientsLink.click();

        // Perform actions on the "Patients" section
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".patient-count"))).click();

        // Add a new patient
        driver.findElement(By.cssSelector(".p-button-primary > .p-button-label")).click();
        driver.findElement(By.id("15")).sendKeys("TestSelinium");
        driver.findElement(By.id("11")).sendKeys("Test@selinium.com");
        driver.findElement(By.cssSelector(".field:nth-child(3) > .p-input-icon-right > .p-inputtext")).sendKeys("123456789");
        driver.findElement(By.cssSelector(".w-full > .ng-tns-c185893541-18")).sendKeys("10/12/2002");

        // Interact with the date picker
        driver.findElement(By.cssSelector(".p-datepicker-today > .p-ripple")).click();

        // Select options from dropdowns
        driver.findElement(By.xpath("//p-dropdown[@id='2']/div/span")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".p-element:nth-child(1) > .p-ripple"))).click();

        // Fill remaining patient details
        driver.findElement(By.id("4")).sendKeys("0606060606");
        driver.findElement(By.xpath("//p-dropdown[@id='5']/div/div[2]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".p-dropdown-item"))).click();
        driver.findElement(By.id("3")).sendKeys("Marrakech");

        // Save the patient details
        driver.findElement(By.cssSelector(".p-button-primary:nth-child(2) > .p-button-label")).click();

        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast-detail")));

        String toastText = toastMessage.getText();
        assertTrue(toastText.matches("Patient added"), "Le message de toast est incorrect. Message reçu: " + toastText);

    }

    @Test
    @Order(2)
    public void updatePatient() {
        // Open the application and set the window size
        driver.get("http://localhost:4200/");
        driver.manage().window().setSize(new Dimension(1680, 944));

        // Log in to the application
        driver.findElement(By.cssSelector(".primary-button")).click();
        driver.findElement(By.id("username")).sendKeys("doctor");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.cssSelector(".submit")).click();

        // Navigate to the "Patients" section
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement patientsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Patients")));
        patientsLink.click();

        // Sélection et modification d'un patient
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ng-star-inserted:nth-child(1) > td .p-button-info > .p-button-icon")));
        editButton.click();

        // Mise à jour du champ et enregistrement
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("15")));
        // Interact with the input field
        nameField.clear();
        nameField.sendKeys("TestSeliniumUpdated");
        driver.findElement(By.cssSelector(".p-button-primary:nth-child(2)")).click();

        // Vérification du message de confirmation (toast)
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast-detail")));
        String toastText = toastMessage.getText();
        assertTrue(toastText.contains("Patient updated"), "Le message de toast est incorrect. Message reçu : " + toastText);
    }


    @Test
    @Order(3)
    public void deletePatient() {
        // Open the application and set the window size
        driver.get("http://localhost:4200/");
        driver.manage().window().setSize(new Dimension(1680, 944));

        // Log in to the application
        driver.findElement(By.cssSelector(".primary-button")).click();
        driver.findElement(By.id("username")).sendKeys("doctor");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.cssSelector(".submit")).click();

        // Navigate to the "Patients" section
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement patientsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Patients")));
        patientsLink.click();

        // deletion d'un patient
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ng-star-inserted:nth-child(1) > td .p-button-danger > .p-button-icon")));
        deleteButton.click();

        // deletion d'un patient
        WebElement confirmationButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".p-confirm-dialog-accept > .p-button-label")));
        confirmationButton.click();

        // Vérification du message de confirmation (toast)
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast-detail")));
        String toastText = toastMessage.getText();
        assertTrue(toastText.contains("Patient Supprimé"), "Le message de toast est incorrect. Message reçu : " + toastText);
    }

}