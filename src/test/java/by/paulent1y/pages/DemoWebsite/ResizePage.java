package by.paulent1y.pages.DemoWebsite;

import by.paulent1y.utility.Driver;
import by.paulent1y.utility.WebPageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static by.paulent1y.utility.WebPageActions.waitForElement;

public class ResizePage {
    @FindBy(xpath = "//div[@class=\"ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se\"]")
    private WebElement corner;
    @FindBy(xpath = "//*[@id=\"resizable\"]")
    private WebElement resizableElement;
    @FindBy(xpath = "//*[@id=\"example-1-tab-1\"]/div/iframe")
    private WebElement frame1;

    public ResizePage() {
        String url = "https://www.way2automation.com/way2auto_jquery/resizable.php#load_box";
        WebPageActions.openUrl(url);
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void resizeElemement(int x, int y){
        Driver.getDriver().switchTo().frame(frame1);
        Actions builder = new Actions(Driver.getDriver());
        builder.
                clickAndHold(corner).
                moveByOffset(x, y).
                release().
                perform();
    }

    public String getElementWidth(){
        return resizableElement.getCssValue("width");
    }

    public String getElementHeight(){
        return resizableElement.getCssValue("height");
    }


}
