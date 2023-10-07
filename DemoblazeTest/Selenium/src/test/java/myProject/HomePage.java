package myProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage() {
        driver.get("https://www.demoblaze.com");
    }

    public void clickProduct(String productName) {
        driver.findElement(By.xpath("//a[contains(text(), '" + productName + "')]")).click();
    }

    public void clickMenuButton(String buttonName) {
        By menuButtonLocator;

        switch (buttonName.toLowerCase()) {
            case "home":
                menuButtonLocator = By.cssSelector("a.nav-link[href='index.html']");
                break;
            case "contact":
                menuButtonLocator = By.linkText("Contact");
                break;
            case "about us":
                menuButtonLocator = By.linkText("About us");
                break;
            case "cart":
                menuButtonLocator = By.linkText("Cart");
                break;
            case "login":
                menuButtonLocator = By.linkText("Log in");
                break;
            case "sign up":
                menuButtonLocator = By.id("signin2");
                break;
            default:
                throw new IllegalArgumentException("Invalid menu button name: " + buttonName);
        }

        driver.findElement(menuButtonLocator).click();
    }


}