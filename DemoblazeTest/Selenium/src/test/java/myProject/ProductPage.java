package myProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectProduct(String productName) {
//        driver.findElement(By.linkText(productName)).click();
        driver.findElement(By.xpath("//a[contains(text(), '" + productName + "')]")).click();

    }

    // Implement the validateProduct method here to validate product details
    public  boolean validateProduct(String expectedCategory) {
        String productTitle;
        try {
            // Retrieve the product title using an XPath locator for the product title element
            productTitle = driver.findElement(By.xpath("//h2[@class='product-title']")).getText();
        } catch (NoSuchElementException e) {
            // Handle the case where the product title element is not found
            return false;
        }

        // Check if the product title contains the category name
        return productTitle.trim().equalsIgnoreCase(expectedCategory.trim());
    }
}