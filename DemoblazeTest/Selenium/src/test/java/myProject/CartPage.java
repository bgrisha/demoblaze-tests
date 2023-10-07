package myProject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String isProductInCart(String productName) {
        // Define a locator to check if the product is in the cart
        By productLocator = By.xpath("//td[contains(text(), '" + productName + "')]");

        try {
            // Check if the product is present in the cart
            WebElement productElement = driver.findElement(productLocator);
            if (productElement.isDisplayed()) {
                System.out.println("is product= " +productElement.getText());
                return productElement.getText(); // Return the actual product name found in the cart
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Product is not found in the cart
        }
        return null; // Return null if the product is not found in the cart
    }

    public String getTotalAmountToPay() {
        // Locate and retrieve the total amount to be paid
        WebElement totalAmountElement = driver.findElement(By.id("totalp"));
        return totalAmountElement.getText();
    }
    public void placeOrderWithAlert() {
        try {
            // Locate and click the "Place Order" button
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Place Order') and contains(@class, 'btn btn-success')]"))).click();

            // Fill in the contact email, contact name, and message in the alert dialog

            WebElement nameInput = driver.findElement(By.xpath("//*[@id=\"name\"]"));
            WebElement countryInput = driver.findElement(By.xpath("//*[@id=\"country\"]"));
            WebElement messagecity = driver.findElement(By.xpath("//*[@id=\"city\"]"));
            WebElement cardInput = driver.findElement(By.xpath("//*[@id=\"card\"]"));
            WebElement monthInput = driver.findElement(By.xpath("//*[@id=\"month\"]"));
            WebElement yearInput = driver.findElement(By.xpath("//*[@id=\"year\"]"));
            WebElement sendMessageButton = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]"));


            // Fill in the  contact name, and country,message,card month,year  in the alert dialog
            nameInput.sendKeys("grisha");
            countryInput.sendKeys("Israel");
            messagecity.sendKeys("Tel Aviv");
            cardInput.sendKeys("123456");
            monthInput.sendKeys("09");
            yearInput.sendKeys("2023");

            // Click the "purchase" button
            sendMessageButton.click();
            Thread.sleep(10000);
        } catch (org.openqa.selenium.TimeoutException e) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean isAlertVisible() {
        // Locate the alert element
        WebElement alertElement = driver.findElement(By.xpath("/html/body/div[10]"));

        // Check if the alert element is displayed
        return alertElement.isDisplayed();
    }
}
