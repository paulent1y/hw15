package by.paulent1y;

import by.paulent1y.utility.Driver;
import by.paulent1y.utility.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static by.paulent1y.utility.WebPageActions.*;


public class CartOrderingTest {

    @DisplayName("Product ordering")
    @Test
    public void itemOrderingTest(){
        openUrl("https://magento.softwaretestingboard.com");
        clickElement(By.xpath("//nav[@class=\"navigation\"]/ul/li[3]"));
        clickElement(By.xpath("//dl[@id=\"narrow-by-list2\"]/dd/ol/li[1]/a"));

        //тут одноразовое использование, чтобы случайную покупку из списка выбрать, не вижу смысла в класс выносить
        List<WebElement> products = Driver.getDriver().findElements(By.xpath("//li[@class=\"item product product-item\"]"));
        products.get(new Random().nextInt(products.size())).click();
        clickElement(By.xpath("//div[@class=\"swatch-option text\"][1]"));
        clickElement(By.xpath("//div[@class=\"swatch-option color\"][1]"));
        clickElement(By.xpath("//*[@id=\"product-addtocart-button\"]"));
        clickElement(By.xpath("//*[text()=\"shopping cart\"]"));
        clickElement(By.xpath("//span[text()=\"Proceed to Checkout\"]"));
        waitForElement(By.xpath("//*[@id=\"tooltip\"]"), 15);

        //тут ajax загружается, если без манипуляций с jsexecutor, то оптимально получается подождать пока лоадер пропадет
        waitForHide(By.xpath("//div[@id=\"checkout-loader\"]"));
        selectValueFrom(By.xpath("//*[@name=\"country_id\"]"), "US");
        selectValueFrom(By.xpath("//*[@name=\"region_id\"]"), "1");
        typeToElement(By.xpath("//input[@id=\"customer-email\"]"), Util.generateEmail());
        typeToElement(By.xpath("//input[@name=\"firstname\"]"),"Marco");
        typeToElement(By.xpath("//input[@name=\"lastname\"]"),"Polo");
        typeToElement(By.xpath("//input[@name=\"street[0]\"]"),"Baker st.");
        typeToElement(By.xpath("//input[@name=\"city\"]"),"London");
        typeToElement(By.xpath("//input[@name=\"postcode\"]"),"12345-6789");
        typeToElement(By.xpath("//input[@name=\"telephone\"]"),"+1239450229");
        clickElement(By.xpath("//div[@id=\"checkout-shipping-method-load\"]/table/tbody/tr"));
        clickElement(By.xpath("//div[@id=\"shipping-method-buttons-container\"]/div"));
        //опять ajax
        waitForHide(By.xpath("//div[@class=\"loading-mask\"]"));
        waitForElement(By.xpath("//span[text()=\"Apply Discount Code\"]"), 15);
        clickElement(By.xpath("//*[text()=\"Place Order\"]"));

        Assertions.assertTrue(elementExists(By.xpath("//div[@class=\"checkout-success\"]")));
        closePage();
    }
}
