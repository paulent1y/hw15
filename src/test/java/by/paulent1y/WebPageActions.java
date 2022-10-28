package by.paulent1y;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebPageActions {

    public static void openUrl(String url) {
        Driver.getDriver().get(url);
        Driver.getDriver().manage().window().maximize();
    }

    public static void clickElement(By locator) {
        waitForElement(locator);
        Driver.getDriver().findElement(locator).click();
    }

    public static void clickOneOf(By locator1, By locator2){
        if (elementExists(locator1)) {
            clickElement(locator1);
        }
        else if (elementExists(locator2)) {
            clickElement(locator2);
        }
        else throw new RuntimeException("There is no " + locator1 + " either " + locator2 + " element" );
    }
    public static void typeToElement(By locator, String text) {
        waitForElement(locator);
        Driver.getDriver().findElement(locator).sendKeys(text);
    }

    public static void waitForElement(By locator, int maxDuration) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(maxDuration))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElement(By locator) {
        waitForElement(locator, 5);
    }

    public static void selectValueFrom(By locator, String value){
        new Select(Driver.getDriver().findElement(locator)).selectByValue(value);
    }

    public static List<WebElement> waitForElements(By locator) {
        List<WebElement> l = Driver.getDriver().findElements(locator);
        return l;
    }

    public static String getTextFromElement(By locator){
        waitForElement(locator);
        return Driver.getDriver().findElement(locator).getText();
    }

    public static boolean elementExists(By locator) {
        waitForElement(locator);
        return (Driver.getDriver().findElements(locator).size()>0);
    }

    public static void closePage(){
        Driver.closeDriver();
    }
}
