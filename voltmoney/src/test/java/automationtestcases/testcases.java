package automationtestcases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class testcases {

    private static Object wait;

    public static void main(String[] args) {
        WebDriver driver = null;
        WebDriverWait wait = null;

        try {
            // Setup ChromeDriver
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            System.out.println("Starting VoltMoney Loan Eligibility Test...");

            driver.get("https://voltmoney.in/check-loan-eligibility-against-mutual-funds");
            System.out.println("Opened VoltMoney loan eligibility page successfully");

            // Test Case 1: Verify page loaded successfully
            System.out.println("\n=== Test Case 1: Verify page loaded successfully ===");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.css-qoivwp")));
            System.out.println("Page loaded successfully");

            // Test Case 2: Verify page title and content
            System.out.println("\n=== Test Case 2: Verify page title and content ===");
            String pageTitle = driver.getTitle();
            if (pageTitle.toLowerCase().contains("volt")
                    || pageTitle.toLowerCase().contains("loan")
                    || pageTitle.toLowerCase().contains("eligibility")) {
                System.out.println("✅ Page title verified: " + pageTitle);
            } else {
                System.out.println("❌ Page title verification failed: " + pageTitle);
            }

            // Test Case 3: Invalid mobile number + clear
            System.out.println("\n=== Test Case 3: Invalid mobile number ===");
            try {
                WebElement invalidMobile = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.css-qoivwp")));
                invalidMobile.clear();
                invalidMobile.sendKeys("1234567");
                // Trigger validation by focusing another input
                driver.findElement(By.cssSelector("input[placeholder='Enter PAN']")).click();
                Thread.sleep(3000);

                WebElement msg = driver.findElement(By.xpath("//*[contains(text(),'valid mobile number')]"));
                System.out.println("✅ Validation message: " + msg.getText());

                // Clear mobile field robustly
                ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", invalidMobile);
                invalidMobile.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);
                System.out.println("Cleared invalid mobile input.");
            } catch (Exception e) {
                System.out.println("Error in Test Case 3: " + e.getMessage());
            }

            // Test Case 4: Invalid PAN number
            System.out.println("\n=== Test Case 4: Invalid PAN number ===");
            try {
                WebElement panInvalid = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Enter PAN']")));
                panInvalid.clear();
                panInvalid.sendKeys("ABCD");  // invalid PAN
                driver.findElement(By.cssSelector("input.css-qoivwp")).click(); // trigger validation
                Thread.sleep(3000);

                WebElement panMsg = driver.findElement(By.xpath("//*[contains(text(),'valid PAN')]"));
                System.out.println("✅ PAN Validation message: " + panMsg.getText());

                // Clear PAN field robustly
                ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", panInvalid);
                System.out.println("Cleared invalid PAN input.");
            } catch (Exception e) {
                System.out.println("Error in Test Case 4.5: " + e.getMessage());
            }

            // Test Case 5: Valid mobile number
            System.out.println("\n=== Test Case 5: Valid mobile number ===");
            WebElement mobileInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.css-qoivwp")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", mobileInput);
            Thread.sleep(500);
            mobileInput.sendKeys("9876543210");
            String mobileVal = mobileInput.getAttribute("value");
            System.out.println(mobileVal.matches("\\d{10}") ?
                    "✅ Entered valid mobile number: " + mobileVal :
                    "❌ Failed to enter valid mobile number: " + mobileVal);

            // Test Case 6: Valid PAN number (Character by Character)
            System.out.println("\n=== Test Case 6: Valid PAN number (Character by Character) ===");
            try {
                WebElement panValid = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Enter PAN']")));

                // Manually clear input using backspaces
                panValid.click();
                String existingValue = panValid.getAttribute("value");
                for (int i = 0; i < existingValue.length(); i++) {
                    panValid.sendKeys(Keys.BACK_SPACE);
                    Thread.sleep(100); // Optional small delay
                }

                String validPAN = "ABCDE1234F"; // Valid format: 5 letters, 4 digits, 1 letter

                if (validPAN.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
                    for (char ch : validPAN.toCharArray()) {
                        panValid.sendKeys(Character.toString(ch));
                        Thread.sleep(200); // Optional delay to simulate typing
                    }

                    System.out.println("✅ PAN format is valid and entered character by character: " + validPAN);

                    // Click outside to trigger validation
                    driver.findElement(By.cssSelector("input.css-qoivwp")).click();
                    Thread.sleep(3000); // Wait for potential error message

                    List<WebElement> errorElements = driver.findElements(By.xpath("//*[contains(text(),'valid PAN')]"));
                    if (errorElements.isEmpty()) {
                        System.out.println("✅ No validation error displayed for valid PAN.");
                    } else {
                        System.out.println("❌ Unexpected validation error: " + errorElements.get(0).getText());
                    }
                } else {
                    System.out.println("❌ PAN format is invalid and was not entered: " + validPAN);
                }
            } catch (Exception e) {
                System.out.println("Error in Test Case 4.6: " + e.getMessage());
            }
            // Test Case 7:Click "Check eligibility for FREE" button
                        System.out.println("\n=== Test Case 7: Click 'Check eligibility for FREE' button ===");
                       try {
                           // Wait a bit for form to be ready
                           Thread.sleep(8000);

            // Find and click the button (based on website content)
                            WebElement checkEligibilityButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button']")));

                          // Scroll to button and click
                          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkEligibilityButton);
                          Thread.sleep(2000);
                           ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkEligibilityButton);
                          System.out.println("✅ 'Check eligibility for FREE' button clicked successfully");

                          // Wait for OTP screen to appear
                         Thread.sleep(3000);

                       } catch (Exception e) {
                          System.out.println("❌ Error clicking eligibility button: " + e.getMessage());
                           e.printStackTrace();
                     }

            // Test Case 8: Handle OTP Screen
            System.out.println("\n=== Test Case 5: Handle OTP Screen ===");

            try {
                WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
                String otp = "123456";

                // ✅ Step 1: Wait for OTP screen container
                wait1.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("div[class*='otp'], div[class*='OTP'], div[class*='mfcInit_otpWrapper']")));

                // ✅ Step 2: Wait for at least one OTP input field to be visible
                wait1.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[type='text'], input[type='tel'], input[type='number']")));

                List<WebElement> otpInputs = driver.findElements(
                        By.cssSelector("input[type='text'], input[type='tel'], input[type='number']"));

                // ✅ Step 3: If only one input is found, treat it as the full OTP input
                if (otpInputs.size() < otp.length()) {
                    System.out.println("⚠️ Less OTP inputs found than expected, using single input.");

                    WebElement singleInput = otpInputs.get(0);

                    wait1.until(ExpectedConditions.elementToBeClickable(singleInput));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", singleInput);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", singleInput); // JS click to avoid overlay issues

                    try {
                        singleInput.clear();
                    } catch (InvalidElementStateException e) {
                        singleInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                        singleInput.sendKeys(Keys.BACK_SPACE);
                    }

                    singleInput.sendKeys(otp);
                    System.out.println("✅ Complete OTP entered in single input: " + otp);
                } else {
                    // ✅ Handle digit-by-digit OTP inputs
                    for (int i = 0; i < otp.length(); i++) {
                        WebElement input = otpInputs.get(i);
                        wait1.until(ExpectedConditions.elementToBeClickable(input));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);  // JS click to avoid interception

                        try {
                            input.clear();
                        } catch (InvalidElementStateException e) {
                            input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                            input.sendKeys(Keys.BACK_SPACE);
                        }

                        input.sendKeys(Character.toString(otp.charAt(i)));
                        System.out.println("✅ OTP digit " + (i + 1) + " entered: " + otp.charAt(i));

                        Thread.sleep(200); // slight delay to simulate real typing
                    }
                }

                Thread.sleep(2000); // wait for UI update or error/success message

                // ✅ Step 4: Try to capture OTP error/success message
                try {
                    WebElement otpMessage = driver.findElement(By.cssSelector(".mfcInit_errorText__H04Oh"));
                    System.out.println("✅ OTP Message found: " + otpMessage.getText());
                } catch (NoSuchElementException e) {
                    System.out.println("ℹ️ No OTP message with primary selector, trying alternative...");
                    try {
                        WebElement altMessage = driver.findElement(By.xpath(
                                "//*[contains(text(),'OTP') or contains(text(),'Invalid') or contains(text(),'Error')]"));
                        System.out.println("✅ OTP Message found: " + altMessage.getText());
                    } catch (NoSuchElementException ex) {
                        System.out.println("ℹ️ No OTP message found with any selector");
                    }
                }

            } catch (Exception e) {
                System.out.println("❌ OTP test case failed: " + e.getMessage());
                e.printStackTrace();
            }

            // Test Case 8: Click on edit button and successfully navigate back to the previous screen
            System.out.println("\n=== Test Case 8: Click on edit button and successfully navigate back to the previous screen ===");
            try {
                WebElement labelElement =  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='mfcInit_label2_3__VFG0w']"))));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", labelElement);
                Thread.sleep(1000);
                labelElement.click();
                Thread.sleep(5000);
                WebElement confirmationElement =driver.findElement(By.xpath("//div[contains(text(),'No impact on CIBIL score')]"));
                if (confirmationElement.isDisplayed()) {
                    System.out.println("✅ Successfully navigated back to previous page after clicking edit");
                } else {
                    System.out.println("❌ Could not verify navigation to previous page");
                }

                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("❌ Failed to click on edit or verify navigation: " + e.getMessage());
            }

            // Test Summary
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
