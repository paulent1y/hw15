package by.paulent1y.pages;


import by.paulent1y.utility.Util;
import org.openqa.selenium.By;

import static by.paulent1y.utility.WebPageActions.*;

public class LoginPage {

    private final String url = "https://malkite.bg/en/";
    private final String mail;
    private final String pass;
    private final By loginButton = By.xpath("//*[@class=\"c-header__profile_link c-header__profile-login\"]");
    private final By logoutButton = By.xpath("//*[@class=\"c-header__profile_link c-header__profile-logout\"]");
    private final By mailTextField = By.xpath("//*[@id=\"Email\"]");
    private final By passwordTextField = By.xpath("//*[@id=\"Password\"]");
    private final By submitLoginButton = By.xpath("//*[@id=\"loginButton\"]");


    public LoginPage() {
        String[] temp = Util.getCredsFromFile().split(",");
        mail=temp[0];
        pass=temp[1];
        openUrl(url);
    }

    public void fillAndSubmit(){
        clickElement(loginButton);
        typeToElement(mailTextField, mail);
        typeToElement(passwordTextField, pass);
        clickElement(submitLoginButton);
    }

    public boolean isLogged(){
        return elementExists(logoutButton);
    }


}
