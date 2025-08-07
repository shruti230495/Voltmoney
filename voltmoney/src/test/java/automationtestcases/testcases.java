package automationtestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class testcases {

    public static void main(String[] args) {
        WebDriver driver = null;
        WebDriverWait wait = null;

        try {
            // Set up ChromeDriver (Selenium Manager will auto-resolve path)
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            System.out.println("Starting VoltMoney Loan Eligibility Test...");

            // Open the URL
            driver.get("https://voltmoney.in/check-loan-eligibility-against-mutual-funds");
            System.out.println("Opened VoltMoney loan eligibility page successfully");

            // ✅ Test Case 1: Page loaded successfully
            System.out.println("\n=== Test Case 1: Verify page loaded successfully ===");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.css-qoivwp")));
            System.out.println("Page loaded successfully");

            // ✅ Test Case 2: Verify page title and content
            System.out.println("\n=== Test Case 2: Verify page title and content ===");
            String pageTitle = driver.getTitle();
            if (pageTitle.contains("Volt") || pageTitle.contains("loan") || pageTitle.contains("eligibility")) {
                System.out.println("✅ Page title verified: " + pageTitle);
            } else {
                System.out.println("❌ Page title verification failed: " + pageTitle);
            }

            System.out.println("\n✅ VoltMoney Loan Eligibility Test completed successfully!");

        } catch (Exception e) {
            System.err.println("❌ VoltMoney Loan Eligibility Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed");
            }
        }
    }
}
