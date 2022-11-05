package by.paulent1y.pages;

import by.paulent1y.utility.WebPageActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static by.paulent1y.utility.WebPageActions.elementExists;


public class ComparisonPage {

    //мне показалось что будет очень умной штукой, сделать чтобы тест работал с обеими версиями сайта
    //но оказалось что не особо. При первом варианте кнопки жмутся сразу
    //а при втором - сначала проходит ожидание первой кнопки, потом только ищет вторую, и так три раза
    //воспользовался правилом "работает - не трогай"

    private final By privacyButton = By.xpath("//button[@id=\"onetrust-accept-btn-handler\"]");
    private final By AddToComparisonButtonV1 = By.xpath("(//div[@class=\"product-community\"])[1]/ul/li/a");
    private final By AddToComparisonButtonV2 = By.xpath("(//div[@class=\"box-medium__content\"])[1]/div/a");
    private final By compareButtonV1 = By.xpath("//div[@id=\"compare-products\"]/a[1]");
    private final By compareButtonV2 = By.xpath("//div[@class=\"comparison-footer-widget__gradient\"]");
    private final By compareViewV1 = By.xpath("//div[@id=\"compare-view\"]");
    private final By compareViewV2 = By.xpath("//*[@class=\"comparison-layer\"]");
    String url1 = "https://www.euro.com.pl/telefony-komorkowe,_Samsung,d6.bhtml";
    String url2 = "https://www.euro.com.pl/telefony-komorkowe,_xiaomi,d6.bhtml";

    public ComparisonPage() {
        WebPageActions.openUrl(url1);
        WebPageActions.clickElement(privacyButton);
    }

    @Step("Switching to page with another models")
    public void switchToSecondProduct(){
        WebPageActions.openUrl(url2);
    }

    @Step("Adding model to comparison")
    public void addToComparison(){
        WebPageActions.clickOneOf(AddToComparisonButtonV1, AddToComparisonButtonV2);
    }

    @Step("Pressing compare button")
    public void compare(){
        WebPageActions.clickOneOf(compareButtonV1, compareButtonV2);
    }

    @Step("Checking if entered comparing menu")
    public boolean isCompared(){
        return WebPageActions.elementExists(compareViewV1) || elementExists(compareViewV2);
    }



}
