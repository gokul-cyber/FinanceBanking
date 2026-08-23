package com.financeme.smoke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FinanceMeSmokeTest {
    @Test
    public void applicationIsReachable() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(System.getProperty("app.url", "http://localhost:8080/h2-console"));
            Assert.assertTrue(driver.getTitle().contains("H2"));
        } finally {
            driver.quit();
        }
    }
}
