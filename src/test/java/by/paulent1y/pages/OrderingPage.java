package by.paulent1y.pages;

import by.paulent1y.utility.Driver;
import by.paulent1y.utility.Util;
import by.paulent1y.utility.WebPageActions;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static by.paulent1y.utility.WebPageActions.*;

public class OrderingPage {
    //там в других классах есть комменты по поводу работы
    private final String url = "https://magento.softwaretestingboard.com/men/tops-men.html";
    private final By productItem = By.xpath("//li[@class=\"item product product-item\"]");
    private final By sizeButton = By.xpath("//div[@class=\"swatch-option text\"][1]");
    private final By colorButton = By.xpath("//div[@class=\"swatch-option color\"][1]");
    private final By addToCartButton = By.xpath("//*[@id=\"product-addtocart-button\"]");
    private final By goToCartButton = By.xpath("//*[text()=\"shopping cart\"]");
    private final By proceedButton = By.xpath("//span[text()=\"Proceed to Checkout\"]");
    private final By loaderImg1 = By.xpath("//div[@id=\"checkout-loader\"]");
    private final By countrySelectField = By.xpath("//*[@name=\"country_id\"]");
    private final By regionSelectField = By.xpath("//*[@name=\"region_id\"]");
    private final By emailTextField = By.xpath("//input[@id=\"customer-email\"]");
    private final By firstnameTextField = By.xpath("//input[@name=\"firstname\"]");
    private final By lastnameTextField = By.xpath("//input[@name=\"lastname\"]");
    private final By streetTextField = By.xpath("//input[@name=\"street[0]\"]");
    private final By cityTextField = By.xpath("//input[@name=\"city\"]");
    private final By postcodeTextField = By.xpath("//input[@name=\"postcode\"]");
    private final By telephoneTextField = By.xpath("//input[@name=\"telephone\"]");
    private final By shippingAgreementCheck = By.xpath("//div[@id=\"checkout-shipping-method-load\"]/table/tbody/tr");
    private final By shippingMethodButton = By.xpath("//div[@id=\"shipping-method-buttons-container\"]/div");
    private final By loaderImg2 = By.xpath("//div[@class=\"loading-mask\"]");
    private final By a = By.xpath("//span[text()=\"Apply Discount Code\"]");
    private final By placeOrderButton = By.xpath("//*[text()=\"Place Order\"]");
    private final By successElement = By.xpath("//div[@class=\"checkout-success\"]");


    public OrderingPage() {
        WebPageActions.openUrl(url);
    }

    @Step("Clicking \"place order\"")
    public void confirmOrder(){
        waitForHide(loaderImg2);
        clickElement(placeOrderButton);
    }

    @Step("Checking if item was ordered successful by checking element presence")
    @Severity(SeverityLevel.CRITICAL)
    public boolean isOrderedSuccessful(){
        return elementExists(successElement);
    }
    @Step("Filling fields with data")
    @Description("Data is filled with randomly generated email and some predefined person data")
    public void fillOrderingData() {
        waitForHide(loaderImg1);
        selectValueFrom(countrySelectField, "US");
        selectValueFrom(regionSelectField, "1");
        typeToElement(emailTextField, Util.generateEmail());
        typeToElement(firstnameTextField, "Peppa");
        typeToElement(lastnameTextField, "Pig");
        typeToElement(streetTextField, "Baker st.");
        typeToElement(cityTextField, "London");
        typeToElement(postcodeTextField, "12834-1412");
        typeToElement(telephoneTextField, "+1234567890");
        clickElement(shippingAgreementCheck);
        clickElement(shippingMethodButton);
    }

    @Step("clicking item")
    @Description ("Item is choosen randomly from all items on a page")
    public void clickRandomItem() {
        List<WebElement> products = Driver.getDriver().findElements(productItem);
        products.get(new Random().nextInt(products.size())).click();
    }

    @Step("Choosing options for item")
    public void chooseOptions() {
        clickElement(sizeButton);
        clickElement(colorButton);
    }

    @Step("Adding item to cart and proceeding")
    public void addToCartAndProceed() {
        clickElement(addToCartButton);
        clickElement(goToCartButton);
        clickElement(proceedButton);
    }
}
