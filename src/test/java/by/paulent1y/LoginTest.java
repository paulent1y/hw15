package by.paulent1y;

import by.paulent1y.utility.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static by.paulent1y.utility.WebPageActions.*;

public class LoginTest {

    @DisplayName("Normal login test")
    @Test
    public void registrationNormalTest(){
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
}
