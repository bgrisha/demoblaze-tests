package myProject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage {
    private WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }
    public List<String> getProductNamesFromCategoryPage() {
        List<String> productNames = new ArrayList<>();

        // Define the locator for the product elements on the category page
        List<WebElement> productElements = driver.findElements(By.className("product")); // Update with the actual locator

        // Iterate through the product elements and extract product names
        for (WebElement productElement : productElements) {
            // Extract the product name from the product element
            String productName = productElement.findElement(By.tagName("h4")).getText(); // Update with the actual locator

            // Add the product name to the list
            productNames.add(productName);
        }

        return productNames;
    }

    // Add this method to click on the "Monitors" category and the first monitor
    public void clickOnMonitorsCategoryAndFirstMonitor() throws InterruptedException {
        // Click on the "Monitors" category
        WebElement monitorsCategory = driver.findElement(By.linkText("Monitors"));


        monitorsCategory.click();

        // Wait for some time to ensure the page loads completely (you can replace this with an explicit wait)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String monitorName = monitorsCategory.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a")).getText(); // Get the monitor name
           driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a")).click();


        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
        Thread.sleep(5000);
        // Switch to the alert dialog
        Alert alert = driver.switchTo().alert();

        // Get the text from the alert dialog (if needed)
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);

        // Accept the alert (click OK)
        ((Alert) alert).accept();

        Assert.assertNotNull(monitorName, "Monitor was not added successfully");
        Thread.sleep(5000);

    }


}