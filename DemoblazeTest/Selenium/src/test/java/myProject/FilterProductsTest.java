package myProject;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import io.qameta.allure.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.Uninterruptibles;
import static org.testng.Assert.*;

@Listeners(AutomationListener.class)
public class FilterProductsTest {


    private WebDriver driver;
    private HomePage homePage;
    private CategoryPage categoryPage;
    private ProductPage productPage;
    private MenuNavigationTest menuNavigationTest;
    private  CartPage cartPage;

    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
 //       System.setProperty("webdriver.chrome.driver", "C:\\myChromeDriver\\chromedriver-win64\\chromedriver.exe");
    }

    @BeforeEach

    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        productPage = new ProductPage(driver);
        menuNavigationTest = new MenuNavigationTest(driver);
        cartPage=new CartPage(driver);

        homePage.goToHomePage();
    }

    @Test
    @Order(1)
    @DisplayName("Filter and Validate Products")
    @Description("Test to filter and validate products")
    public void filterAndValidateProducts() {

        String[] categories = {"Laptops", "Phones", "Monitors"};

        for (String category : categories) {
            // Click on the category link
            homePage.clickProduct(category);

            // Get product names from the category page
            List<String> productNames = categoryPage.getProductNamesFromCategoryPage();

            // Validate each product
            for (String productName : productNames) {
                // Set expectedCategory to the current category being tested
                String expectedCategory = category;

                // Click on the product
                productPage.selectProduct(productName);

                // Validate the product here, check if specific elements exist on the product page
                boolean isProductValid = productPage.validateProduct(expectedCategory);

                //  assert that the product is valid
                assertTrue(isProductValid, "Product validation failed for: " + productName);

                // Go back to the category page to select the next product
                driver.navigate().back();
            }

            // Go back to the main page to select products from the next category
            driver.navigate().back();
        }
    }

    @Test
    @Order(2)
    @DisplayName("Verify Navigation for Menu Buttons")
    @Description("Test to verify navigation for menu buttons")
    public void verifyNavigationForMenuButtons() throws InterruptedException {
        String[] buttons = {"home", "contact", "about us", "cart", "login", "sign up"};

        for (String button : buttons) {
            // Click on the menu button first
            menuNavigationTest.clickMenuButton(button);

            // Call the method from MenuNavigationTest to close alerts
            menuNavigationTest.verifyNavigationForMenuButtons(button);
        }
    }
    @Test
    @Order(3)
    @DisplayName("Verify Navigation for Contact us ")
    @Description("Test to verify sending data to contact us ")
    public void sendDateInContactUs ()  {
        String button="contact";
        menuNavigationTest.clickMenuButton(button);
        menuNavigationTest.sendDate();

    }
    @Test
    @Order(4)
    @DisplayName("choose Monitor ")
    @Description("Test to verify choose monitor ")
    public void addProduct() throws InterruptedException {
        // Click on the "Monitors" category and select the first monitor
        categoryPage.clickOnMonitorsCategoryAndFirstMonitor();

        // Click on the "Cart" button
        String button = "Cart";
        menuNavigationTest.clickMenuButton(button);

        // Define the expected monitor name
        String expectedMonitorName = "Apple monitor 24";

        // Verify that the selected monitor is in the cart
        String productInCart = cartPage.isProductInCart(expectedMonitorName);
        assertNotNull(productInCart, "Monitor is not in the cart");
        assertEquals(productInCart,expectedMonitorName,"Monitor is not in the cart");

        // Retrieve and verify the total amount to pay (you might need to implement a method in CartPage)
        String totalAmountInCart = cartPage.getTotalAmountToPay();
        System.out.println(totalAmountInCart);
        assertEquals("400", totalAmountInCart, "Total amount in the cart is incorrect");

        // Locate and click the "Place Order" button and Fill in at data and Click the "purchase" button
        cartPage.placeOrderWithAlert();

        //  check the visibility of the alert-Thank you for your purchase!
        boolean isAlertVisible = cartPage.isAlertVisible();
        assertTrue(isAlertVisible, "Alert is not visible.");
    }




    @AfterEach
    public void tearDown() {

        driver.quit();
    }
}