package by.paulent1y.pages;

import by.paulent1y.utility.Util;
import org.openqa.selenium.By;
import static by.paulent1y.utility.WebPageActions.*;
public class RegistrationPage {

    private final String pass;
    private final String mail;

    private final String url = "https://malkite.bg/en/register.html";
    private final By countrySelectField = By.xpath("//*[@id=\"CustomerBillingCountry\"]");
    private final By emailTextField = By.xpath("//*[@id=\"CustomerEmail\"]");
    private final By passwordTextField = By.xpath("//*[@id=\"CustomerPassword\"]");
    private final By confirmPasswordTextField = By.xpath("//*[@id=\"CustomerPasswordConfirm\"]");
    private final By firstnameTextField = By.xpath("//*[@id=\"CustomerBillingFirstName\"]");
    private final By lastnameTextField = By.xpath("//*[@id=\"CustomerBillingLastName\"]");
    private final By phoneTextField = By.xpath("//*[@id=\"CustomerBillingPhone\"]");
    private final By cityTextField = By.xpath("//*[@id=\"CustomerBillingCity\"]");
    private final By adressTextField = By.xpath("//*[@name=\"CustomerBillingAddress1\"]");
    private final By privacyCheckBox = By.xpath("//*[@id=\"c-privacy-policy__checkbox\"]");
    private final By registerButton = By.xpath("//*[@id=\"registerButton\"]");
    private final By logoutButton = By.xpath("//*[@class=\"c-header__profile_link c-header__profile-logout\"]");


    public void fillData(){
        selectValueFrom(countrySelectField, "176");
        typeToElement(emailTextField, mail);
        typeToElement(passwordTextField, pass);
        typeToElement(confirmPasswordTextField, pass);
        typeToElement(firstnameTextField, "Marco");
        typeToElement(lastnameTextField, "Polo");
        typeToElement(phoneTextField, "+1239450493");
        typeToElement(cityTextField, "London");
        typeToElement(adressTextField, "Baker St. 221b");

    }

    public void submitRegistration(){
        clickElement(privacyCheckBox);
        clickElement(registerButton);
    }

    public boolean isRegistrationSuccessful(){
        if (!elementExists(logoutButton))
            return false;
        Util.saveCredsToFile(mail, pass);
        return true;
    }

    public RegistrationPage() {
        openUrl(url);
        pass = Util.generatePassword();
        mail = Util.generateEmail();

    }

}
