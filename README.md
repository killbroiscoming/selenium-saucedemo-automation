# Selenium Java Test Automation Framework

A UI test automation framework built with Java, Selenium WebDriver, TestNG, and Maven. The project automates core e-commerce user flows on Sauce Demo, including login, adding products to the cart, removing products, cart validation, and checkout.

## Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Allure Report
- Git and GitHub
- IntelliJ IDEA

## Project Structure

```text
selenium-java-testng-automation-framework
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── base
│   │   │   │   └── BasePage.java
│   │   │   ├── pages
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── ProductsPage.java
│   │   │   │   ├── CartPage.java
│   │   │   │   ├── CheckoutPage.java
│   │   │   │   ├── CheckoutOverviewPage.java
│   │   │   │   └── CheckoutCompletePage.java
│   │   │   └── utils
│   │   │       ├── ConfigReader.java
│   │   │       └── ScreenshotUtil.java
│   │   └── resources
│   │       └── config.properties
│   │
│   └── test
│       ├── java
│       │   ├── base
│       │   │   └── BaseTest.java
│       │   ├── listeners
│       │   │   ├── RetryAnalyzer.java
│       │   │   ├── RetryListener.java
│       │   │   └── ScreenshotListener.java
│       │   └── tests
│       │       ├── LoginTest.java
│       │       ├── ProductsTest.java
│       │       ├── CartTest.java
│       │       └── CheckoutTest.java
│       └── resources
│
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Covered Test Flows

The framework currently covers:

- Valid user login
- Invalid user login
- Products page validation after login
- Add a product to the cart
- Verify cart badge count after adding a product
- Remove a product from the cart
- Verify cart badge count after removing a product
- Open the cart and verify a selected product
- Complete the checkout process
- Verify the checkout confirmation message
- Cancel checkout information and return to the cart
- Verify the selected product remains in the cart after cancelling checkout
- Continue shopping from the cart page and return to the product page

## Framework Design

This project follows the Page Object Model design pattern.

- **Page classes** contain locators and page-specific actions.
- **Test classes** contain test scenarios and assertions.
- **BasePage** contains reusable Selenium actions such as click, type, wait, and get text.
- **BaseTest** manages browser setup and teardown.
- **ConfigReader** loads test data and environment values from `config.properties`.
- **Listeners** manage retry logic and screenshots for failed tests.

## Prerequisites

Install the following before running the project:

- Java 17 or later
- Maven
- Google Chrome
- IntelliJ IDEA or another Java IDE
- Git

Check your installation:

```bash
java -version
mvn -version
git --version
```

## Setup Instructions

Clone the repository:

```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/selenium-java-testng-automation-framework.git
```

Open the project in IntelliJ IDEA and wait for Maven dependencies to download.

Create a local configuration file at:

```text
src/main/resources/config.properties
```

Copy the example configuration file:

```bash
cp src/main/resources/config.properties.example src/main/resources/config.properties
```

On Windows Command Prompt, use:

```cmd
copy src\main\resources\config.properties.example src\main\resources\config.properties
```

Update `config.properties` if needed:

```properties
baseUrl=https://www.saucedemo.com/
username=standard_user
password=secret_sauce
firstname=Test
lastname=User
postalCode=SW1A1AA
```

`config.properties` should not be committed to GitHub. Keep it in `.gitignore`.

## Running Tests

Run all tests:

```bash
mvn clean test
```

Run the TestNG suite:

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

You can also run individual test classes or methods directly from IntelliJ IDEA.

## Reporting

TestNG reports are generated after test execution:

```text
target/surefire-reports/
test-output/
```

The project also supports Allure reporting.

Generate and open the Allure report after running tests:

```bash
allure serve allure-results
```

The Allure report can include:

- Test execution status
- Passed and failed tests
- Failure details
- Failed-test screenshots
- Retry attempts

## Screenshot Capture

When a test fails, `ScreenshotListener` captures a screenshot and attaches it to the Allure report. Screenshots can also be saved locally in the `screenshots/` folder.

## Retry Mechanism

The project includes `RetryAnalyzer` to retry tests that fail because of temporary browser timing or UI-loading issues.

Retries should only handle temporary failures. A consistently failing test should be investigated and fixed.

## Future Improvements

- Add GitHub Actions CI/CD pipeline
- Run tests in headless mode
- Add cross-browser testing for Chrome, Firefox, and Edge
- Add TestNG groups for smoke and regression tests
- Add data-driven testing with TestNG DataProvider
- Add environment support for QA and staging
- Add API automation tests using Rest Assured
- Improve Allure reports with more test steps and attachments
- Add logging with SLF4J or Log4j2
- Add parallel test execution
- Add Docker support
- Add a GitHub Actions build-status badge

## Author

Lisa Zhong

## Notes

This project was created as a practice automation framework to demonstrate UI test automation skills using Java, Selenium WebDriver, TestNG, Maven, and the Page Object Model pattern.