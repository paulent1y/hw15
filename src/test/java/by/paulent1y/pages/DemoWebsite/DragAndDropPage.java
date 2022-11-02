package by.paulent1y.pages.DemoWebsite;

import by.paulent1y.utility.Driver;
import by.paulent1y.utility.WebPageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static by.paulent1y.utility.WebPageActions.waitForElement;

public class DragAndDropPage {
    @FindBy(xpath = "//div[@id=\"draggable\"]")
    private WebElement draggable;
    @FindBy(xpath = "//div[@id=\"droppable\"]")
    private WebElement droppable;
    @FindBy(xpath = "//*[@id=\"example-1-tab-1\"]/div/iframe")
    private WebElement frame1;

    public DragAndDropPage() {
        String url = "https://www.way2automation.com/way2auto_jquery/droppable.php";
        WebPageActions.openUrl(url);
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void dragToDropSpot(){
        Driver.getDriver().switchTo().frame(frame1);
        Actions builder = new Actions(Driver.getDriver());
        builder.dragAndDrop(draggable, droppable).
                perform();
    }

    public String getDroppableText(){
        return droppable.getText();
    }


}
