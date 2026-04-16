package ru.Irina.NauJava.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SeleniumAuthTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testLoginAndLogoutFlow() {
        String testUser = "seleniumUser" + System.currentTimeMillis();

        driver.get("http://localhost:" + port + "/registration");
        driver.findElement(By.name("login")).sendKeys(testUser);
        driver.findElement(By.name("password")).sendKeys("testPass123");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        driver.get("http://localhost:" + port + "/login");
        driver.findElement(By.name("username")).sendKeys(testUser);
        driver.findElement(By.name("password")).sendKeys("testPass123");
        driver.findElement(By.cssSelector("button[type='submit'], input[type='submit']")).click();

        assertTrue(driver.getPageSource().contains("Выйти из системы"));

        driver.findElement(By.xpath("//input[@value='Выйти из системы']")).click();

        assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}