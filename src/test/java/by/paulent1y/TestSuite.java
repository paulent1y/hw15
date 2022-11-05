package by.paulent1y;

import by.paulent1y.pages.ComparisonPage;
import by.paulent1y.pages.DemoWebsite.DragAndDropPage;
import by.paulent1y.pages.DemoWebsite.ResizePage;
import by.paulent1y.pages.LoginPage;
import by.paulent1y.pages.OrderingPage;
import by.paulent1y.pages.RegistrationPage;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static by.paulent1y.utility.WebPageActions.*;

public class TestSuite {

    //сделал основные страницы с полями через By, потому что логика через них уже прописана в WebPageActions,
    //дополнительные страницы поделал через @FindBy, и там уже Actions которые работают напрямую с WebElement
    //работает в принципе и тот и тот приятно, хотел попробовать разные способы
    //осознаю что по-хорошему для цельного проекта нужно взять что то одно


    @DisplayName("Normal registration test")
    @Test
    @Description("Test for registration with randomly generated mail and password")
    public void registrationNormalTest(){
        RegistrationPage page = new RegistrationPage();
        page.fillData();
        page.submitRegistration();
        Assertions.assertTrue(page.isRegistrationSuccessful());
    }

    @DisplayName("Product comparison test")
    @RepeatedTest(1)
    @Description("Test for comparing two phone models")
    public void comparisonTest(){
        ComparisonPage page = new ComparisonPage();
        page.addToComparison();
        page.switchToSecondProduct();
        page.addToComparison();
        page.compare();
        Assertions.assertTrue(page.isCompared());
    }

    @DisplayName("Product ordering")
    @Test
    @Description("Ordering a t-shirt wit entering shipping adress")
    public void itemOrderingTest(){
        OrderingPage page = new OrderingPage();
        page.clickRandomItem();
        page.chooseOptions();
        page.addToCartAndProceed();
        page.fillOrderingData();
        page.confirmOrder();
        Assertions.assertTrue(page.isOrderedSuccessful());
    }

    @DisplayName("Normal login test")
    @Test
    @Description("Login test with credentials from previous registrations")
    public void loginTest(){
        LoginPage page = new LoginPage();
        page.fillAndSubmit();
        Assertions.assertTrue(page.isLogged());
    }

    @DisplayName("Drag&Drop test")
    @Test
    @Description("Simple test for drag&drop action")
    public void actions1Test(){
        DragAndDropPage page = new DragAndDropPage();
        page.dragToDropSpot();
        Assertions.assertEquals("Dropped!", page.getDroppableText());
    }

    @DisplayName("Click&Hold test")
    @Description("Simple test for clicking and holding element for its resizing")
    @ParameterizedTest
    @CsvSource({"50,50", "100,250", "300,0", "0,200"})
    public void actions2test(int xOffset, int yOffset){
        ResizePage page = new ResizePage();
        page.resizeElement(xOffset, yOffset);
        Assertions.assertEquals(page.getElementWidth(),Integer.toString(150+xOffset)+"px");
        Assertions.assertEquals(page.getElementHeight(),Integer.toString(150+yOffset)+"px");
    }

    @AfterEach
    public void closeResources(){
        closePage();
    }


}
