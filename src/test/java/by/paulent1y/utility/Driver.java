package by.paulent1y.utility;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


//синглтон класс драйвера

public class Driver {

    private static WebDriver driver;


    public static void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            init();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
