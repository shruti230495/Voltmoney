
# ✅ VoltMoney Loan Eligibility Test Automation

This project automates UI testing for the **VoltMoney loan eligibility flow** using **Selenium WebDriver with Java**.

---

## 🔍 Framework Overview

### ✅ Tech Stack
- **Language**: Java
- **Automation Tool**: Selenium WebDriver
- **Browser**: Google Chrome
- **Build Tool**: None (currently manual execution; can be migrated to Maven/Gradle)
- **IDE**: IntelliJ IDEA (preferred)

### ✅ Test Cases Automated
1. Verify that the page loads successfully.
2. Validate page title and content.
3. Submit an invalid mobile number and verify error handling.
4. Submit an invalid PAN and verify validation.
5. Submit a valid mobile number.
6. Submit a valid PAN (entered character by character).
7. Click the **"Check eligibility for FREE"** button.
8. Handle OTP input (both single-field and digit-by-digit).
9. Click the "Edit" button after OTP step.

---

## 🛠️ Setup Instructions

### 1. Prerequisites
- **Java JDK** 17+ installed
- **Chrome browser** installed (version should match the ChromeDriver used)
- **IntelliJ IDEA** or any Java IDE
- **Selenium libraries added** to project:
  - `selenium-java`
  - `selenium-chrome-driver`
- Optional: Add logging framework or test reporting later

### 2. Setup Steps

1. Clone this repository or copy the `testcases.java` file into your project.
2. Add the required Selenium libraries manually or via Maven:
   ```xml
   <!-- Add this to pom.xml if you migrate to Maven -->
   <dependency>
       <groupId>org.seleniumhq.selenium</groupId>
       <artifactId>selenium-java</artifactId>
       <version>4.14.0</version> <!-- or latest -->
   </dependency>
