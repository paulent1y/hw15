package by.paulent1y;

import by.paulent1y.utility.Driver;
import by.paulent1y.utility.Util;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

import static by.paulent1y.utility.WebPageActions.*;

public class TestSuite {
    @DisplayName("Normal registration test")
    @Test
    public void registrationNormalTest(){
        String password = Util.generatePassword();
        String mail = Util.generateEmail();
        openUrl("https://malkite.bg/en/");
        clickElement(By.xpath("//*[@class=\"c-header__profile_link c-header__profile-register\"]"));
        selectValueFrom(By.xpath("//*[@id=\"CustomerBillingCountry\"]"),"176");
        typeToElement(By.xpath("//*[@id=\"CustomerEmail\"]"), mail);
        typeToElement(By.xpath("//*[@id=\"CustomerPassword\"]"), password);
        typeToElement(By.xpath("//*[@id=\"CustomerPasswordConfirm\"]"), password);
        typeToElement(By.xpath("//*[@id=\"CustomerBillingFirstName\"]"), "Marco");
        typeToElement(By.xpath("//*[@id=\"CustomerBillingLastName\"]"), "Polo");
        typeToElement(By.xpath("//*[@id=\"CustomerBillingPhone\"]"), "+1239450493");
        typeToElement(By.xpath("//*[@id=\"CustomerBillingCity\"]"), "London");
        typeToElement(By.xpath("//*[@name=\"CustomerBillingAddress1\"]"), "Baker St. 221b");
        clickElement(By.xpath("//*[@id=\"c-privacy-policy__checkbox\"]"));
        clickElement(By.xpath("//*[@id=\"registerButton\"]"));

        Assertions.assertTrue(elementExists(By.xpath("//*[@class=\"c-header__profile_link c-header__profile-logout\"]")));
        Util.saveCredsToFile(mail, password);
    }

    @DisplayName("Product comparison test")
    //сайт как то рандомно(или нет) включает одну из версий. Тест костыльно работает с обеими версиями (тыкает одну из двух кнопок)
    @RepeatedTest(1)
    public void comparisonTest(){
        openUrl("https://www.euro.com.pl/telefony-komorkowe,_Samsung,d6.bhtml");
        clickElement(By.xpath("//button[@id=\"onetrust-accept-btn-handler\"]"));

        //берет просто телефон с верхушки из категории самсунгов
        clickOneOf(By.xpath("(//div[@class=\"product-community\"])[1]/ul/li/a"),
                By.xpath("(//div[@class=\"box-medium__content\"])[1]/div/a"));
        //потом из сяоми
        openUrl("https://www.euro.com.pl/telefony-komorkowe,_xiaomi,d6.bhtml");
        clickOneOf(By.xpath("(//div[@class=\"product-community\"])[1]/ul/li/a"),
                By.xpath("(//div[@class=\"box-medium__content\"])[1]/div/a/span"));

        clickOneOf(By.xpath("//div[@id=\"compare-products\"]/a[1]"),
                By.xpath("//div[@class=\"comparison-footer-widget__gradient\"]"));
        //тыкает "сравнить", проверяет что на странице есть блок сравнения моделей
        Assertions.assertTrue(elementExists(By.xpath("//div[@id=\"compare-view\"]")) || elementExists(By.xpath("//*[@class=\"comparison-layer\"]")));
    }

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

    @DisplayName("Normal login test")
    @Test
    public void loginTest(){
        String[] s = Util.getCredsFromFile().split(",");
        String mail = s[0];
        String pass = s[1];
        openUrl("https://malkite.bg/en/");
        clickElement(By.xpath("//*[@class=\"c-header__profile_link c-header__profile-login\"]"));
        typeToElement(By.xpath("//*[@id=\"Email\"]"), mail);
        typeToElement(By.xpath("//*[@id=\"Password\"]"), pass);
        clickElement(By.xpath("//*[@id=\"loginButton\"]"));
        Assertions.assertTrue(elementExists(By.xpath("//*[@class=\"c-header__profile_link c-header__profile-logout\"]")));
    }

    @DisplayName("Drag&Drop test")
    @Test
    public void actions1Test(){
        openUrl("https://www.way2automation.com/way2auto_jquery/droppable.php");
        Driver.getDriver().switchTo().frame(waitForElement(By.xpath("//*[@id=\"example-1-tab-1\"]/div/iframe")));
        Actions builder = new Actions(Driver.getDriver());

        builder.
                dragAndDrop(
                        waitForElement(By.xpath("//div[@id=\"draggable\"]")),
                        waitForElement(By.xpath("//div[@id=\"droppable\"]"))).
                perform();
        Assertions.assertEquals("Dropped!", waitForElement(By.xpath("//div[@id=\"droppable\"]")).getText());
    }


    @DisplayName("Click&Hold test")
    @Test
    public void actions2test(){
        openUrl("https://www.way2automation.com/way2auto_jquery/resizable.php#load_box");
//        clickElement(By.xpath("//a[@href=\"#example-1-tab-2\"]"));
        Driver.getDriver().switchTo().frame(waitForElement(By.xpath("//*[@id=\"example-1-tab-1\"]/div/iframe")));
        Actions builder = new Actions(Driver.getDriver());
        WebElement corner = waitForElement(By.xpath("//div[@class=\"ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se\"]"));
        int xoffset = 200, yoffset = 134;
        builder.
                clickAndHold(corner).
                moveByOffset(xoffset, yoffset).
                release().
                perform();
        Assertions.assertEquals(waitForElement(By.xpath("//*[@id=\"resizable\"]")).getCssValue("width"),Integer.toString(150+xoffset)+"px");
        Assertions.assertEquals(waitForElement(By.xpath("//*[@id=\"resizable\"]")).getCssValue("height"),Integer.toString(150+yoffset)+"px");
    }


    @AfterEach
    public void closeResources(){
        closePage();
    }


}
