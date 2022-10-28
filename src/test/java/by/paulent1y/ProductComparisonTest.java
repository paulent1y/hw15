package by.paulent1y;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static by.paulent1y.WebPageActions.*;

public class ProductComparisonTest {

    @DisplayName("Product comparison test")
    @Test
    public void comparisonTest(){
        //берет просто телефон с верхушки из категории самсунгов
        openUrl("https://www.euro.com.pl/telefony-komorkowe,_Samsung,d6.bhtml");
        clickElement(By.xpath("//button[@id=\"onetrust-accept-btn-handler\"]"));
        clickOneOf(By.xpath("//ul[@class=\"community-list\"]/li/a"),
                By.xpath("//div[@class=\"box-medium__content\"]/div/a"));
        //потом из сяоми
        openUrl("https://www.euro.com.pl/telefony-komorkowe,_xiaomi,d6.bhtml");
        clickOneOf(By.xpath("//ul[@class=\"community-list\"]/li/a"),
                By.xpath("//div[@class=\"box-medium__content\"]/div/a"));
        clickElement(By.xpath("//div[@id=\"compare-products\"]/a[1]"));
        //тыкает "сравнить", проверяет что на странице есть блок сравнения моделей
        Assertions.assertTrue(elementExists(By.xpath("//div[@id=\"compare-view\"]")));
        closePage();
    }
}
