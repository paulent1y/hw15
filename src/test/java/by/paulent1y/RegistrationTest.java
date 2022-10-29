package by.paulent1y;

import by.paulent1y.utility.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static by.paulent1y.utility.WebPageActions.*;

public class RegistrationTest {
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
//        typeToElement(By.xpath("//*[@id=\"\"]"), "");

//        closePage();
    }

}
