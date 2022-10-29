package by.paulent1y;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static by.paulent1y.utility.WebPageActions.*;

public class ProductComparisonTest {

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

    @AfterEach
    public void closeResources(){
        closePage();
    }
}
