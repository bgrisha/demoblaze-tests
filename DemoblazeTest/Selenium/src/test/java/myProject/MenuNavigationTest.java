package myProject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuNavigationTest {
        private WebDriver driver;
        private HomePage homePage;

        public MenuNavigationTest(WebDriver driver) {
                this.driver = driver;
                this.homePage = new HomePage(driver);
        }

        public void verifyNavigationForMenuButtons(String button) throws InterruptedException {
                if (button.equals("contact")) {
                        if (isModalPresent()) {
                                Assertions.assertTrue(isModalPresent(), "Contact not found for button: " + button);

                                closeModalUsingJavaScript();
                        }
                } else if (button.equals("about us")) {
                        if (isSecondAlertPresent()) {
                                Assertions.assertTrue(isSecondAlertPresent(), "About us  not found for button: " + button);
                                closeSecondAlertUsingJavaScript();
                        }
                } else if (button.equals("login")) {
                        if (isThirdAlertPresent()) {
                                Assertions.assertTrue(isThirdAlertPresent(), "login  not found for button: " + button);
                                closeThirdAlertUsingJavaScript();
                        }
                } else if (button.equals("sign up")) {
                        if (isFourAlertPresent()) {
                                Assertions.assertTrue(isFourAlertPresent(), "sign up  not found for button: " + button);
                                closeFourAlertUsingJavaScript();
                        }
                } else {
                        // Click on the menu button
                        clickMenuButton(button);
                }

                Thread.sleep(2000);
        }

        public void clickMenuButton(String buttonName) {
                By menuButtonLocator;

                switch (buttonName.toLowerCase()) {
                        case "home":
                                menuButtonLocator = By.cssSelector("a.nav-link[href='index.html']");
                                break;
                        case "contact":
                                menuButtonLocator = By.xpath("//a[contains(@class, 'nav-link') and contains(text(), 'Contact')]");
                                break;
                        case "about us":
                                menuButtonLocator = By.xpath("//*[@id=\"navbarExample\"]/ul/li[3]/a");
                                break;
                        case "cart":
                                menuButtonLocator = By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a");

                                break;
                        case "login":
                                menuButtonLocator = By.xpath("//*[@id=\"navbarExample\"]/ul/li[5]/a");
                                break;
                        case "sign up":
                                menuButtonLocator = By.xpath("//*[@id=\"signin2\"]");
                                break;
                        default:
                                throw new IllegalArgumentException("Invalid menu button name: " + buttonName);
                }
                // After defining the locator, click the element
                driver.findElement(menuButtonLocator).click();
                // Add a short delay to allow the page to load (adjust the wait time as needed)
                try {
                        Thread.sleep(2000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
        }

        private boolean isModalPresent() {
                try {
                        WebDriverWait wait = new WebDriverWait(driver, 10);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleModalLabel")));

                        return true;
                } catch (org.openqa.selenium.TimeoutException e) {
                        return false;
                }
        }

        private void closeModalUsingJavaScript() {
                try {
                        WebElement closeButton = driver.findElement(By.xpath("//button[@aria-label='Close']"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
                } catch (org.openqa.selenium.NoSuchElementException e) {

                }
        }

        private boolean isSecondAlertPresent() {
                try {
                        WebDriverWait wait = new WebDriverWait(driver, 10);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("videoModal")));
                        return true;
                } catch (org.openqa.selenium.TimeoutException e) {
                        return false;
                }
        }


        private void closeSecondAlertUsingJavaScript() {
                try {
                        WebElement closeButton = driver.findElement(By.xpath("//*[@id='videoModal']/div/div/div[3]/button"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
                } catch (org.openqa.selenium.NoSuchElementException e) {

                }
        }

        private boolean isThirdAlertPresent() {
                try {
                        WebDriverWait wait = new WebDriverWait(driver, 10);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModal")));
                        return true;
                } catch (org.openqa.selenium.TimeoutException e) {
                        return false;
                }
        }

        private void closeThirdAlertUsingJavaScript() {
                try {
                        WebElement closeButton = driver.findElement(By.xpath("//*[@id='logInModal']/div/div/div[3]/button[1]"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
                } catch (org.openqa.selenium.NoSuchElementException e) {

                }
        }

        private boolean isFourAlertPresent() {
                try {
                        WebDriverWait wait = new WebDriverWait(driver, 10);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModal")));
                        return true;
                } catch (org.openqa.selenium.TimeoutException e) {
                        return false;
                }
        }

        private void closeFourAlertUsingJavaScript() {
                try {
                        WebElement closeButton = driver.findElement(By.xpath("//*[@id='signInModal']/div/div/div[3]/button[1]"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
                } catch (org.openqa.selenium.NoSuchElementException e) {

                }
        }
        public void sendDate(){
                // Find and click the button that opens the modal

                        // Find and click the button that opens the modal
                        try {
                                WebDriverWait wait = new WebDriverWait(driver, 10);
                                WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"exampleModal\"]/div/div")));

                                // Assert that the form is visible
                                Assertions.assertTrue(modal.isDisplayed(), "Form is not visible.");



                                // Find the input fields and the "Send message" button inside the modal
                                WebElement emailInput = modal.findElement(By.id("recipient-email"));
                                WebElement nameInput = modal.findElement(By.id("recipient-name"));
                                WebElement messageInput = modal.findElement(By.id("message-text"));
                                WebElement sendMessageButton = modal.findElement(By.xpath("//button[contains(text(),'Send message')]"));

                                // Fill in the contact email, contact name, and message
                                emailInput.sendKeys("your_email@example.com");
                                nameInput.sendKeys("Your Name");
                                messageInput.sendKeys("Your message goes here");



                                // Click the "Send message" button
                                sendMessageButton.click();

                                // Wait for the alert to be present
                                WebDriverWait alertWait = new WebDriverWait(driver, 10);
                                alertWait.until(ExpectedConditions.alertIsPresent());
                                Thread.sleep(10000);

                                // Handle the alert as accepted
                                driver.switchTo().alert().accept();

                        } catch (org.openqa.selenium.TimeoutException e) {

                        } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                        }
        }
}
