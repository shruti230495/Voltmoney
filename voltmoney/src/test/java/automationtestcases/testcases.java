package automationtestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class testcases {

    public static void main(String[] args) {
        WebDriver driver = null;
        WebDriverWait wait;

        try {
            // Set up ChromeDriver
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

            // ✅ Test Case 2: Fill mobile number
            System.out.println("\n=== Test Case 2: Fill mobile number ===");
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.css-qoivwp")));
            inputField.clear();
            inputField.sendKeys("9876543210");
            String mobileValue = inputField.getAttribute("value");
            assert mobileValue != null;
            if (mobileValue.matches("\\d{10}")) {
                System.out.println("✅ Mobile number entered successfully: " + mobileValue);
            } else {
                System.out.println("❌ Mobile number validation failed: " + mobileValue);
            }

            // ✅ Test Case 3: Fill PAN number
            System.out.println("\n=== Test Case 3: Fill PAN number ===");
            WebElement panField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Enter PAN']")));
            panField.clear();
            panField.sendKeys("ABCDE1234F");
            String panValue = panField.getAttribute("value");
            assert panValue != null;
            if (!panValue.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
                System.out.println("❌ PAN number validation failed: " + panValue);
            } else {
                System.out.println("✅ PAN number entered successfully: " + panValue);
            }

            // ✅ Test Case 4: Click "Check eligibility for FREE" button
            System.out.println("\n=== Test Case 4: Click 'Check eligibility for FREE' button ===");
            Thread.sleep(8000); // wait for animations/page readiness
            WebElement checkEligibilityButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button']")));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkEligibilityButton);
            Thread.sleep(2000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", checkEligibilityButton);
            System.out.println("✅ 'Check eligibility for FREE' button clicked successfully");

            // ✅ Test Case 5: Handle OTP Screen
            System.out.println("\n=== Test Case 5: Handle OTP Screen ===");
            Thread.sleep(8000); // give time for OTP screen to load

            try {
                // Try to find OTP input fields
                List<WebElement> otpInputs = Collections.singletonList(driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")));
                String otp = "123456";
                otpInputs.get(0).clear();
                otpInputs.get(0).sendKeys(otp);
                System.out.println("✅ Complete OTP entered in single field: " + otp);

                Thread.sleep(2000); // wait for message

                // Try to detect OTP result message
                try {
                    WebElement otpMessage = driver.findElement(By.cssSelector(".mfcInit_errorText__H04Oh"));
                    System.out.println("✅ OTP Message found: " + otpMessage.getText());
                } catch (Exception e1) {
                    System.out.println("ℹ️ No message found with primary selector, checking alternatives...");
                    try {
                        WebElement messageAlt1 = driver.findElement(By.cssSelector(".error, .message, .alert, .notification"));
                        System.out.println("✅ OTP Message (alt): " + messageAlt1.getText());
                    } catch (Exception e2) {
                        try {
                            WebElement messageAlt2 = driver.findElement(By.xpath("//*[contains(text(), 'OTP') or contains(text(), 'Invalid') or contains(text(), 'Error')]"));
                            System.out.println("✅ OTP Message (text match): " + messageAlt2.getText());
                        } catch (Exception e3) {
                            System.out.println("ℹ️ No OTP message found with any selector");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("❌ Error during OTP input handling: " + e.getMessage());
            }
            // ✅ Summary
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
