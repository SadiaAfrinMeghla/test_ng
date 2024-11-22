package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

@FindBy(id ="email")
public WebElement txtEmail;
@FindBy(id= "password")
public WebElement txtPassword;

@FindBy(css = "button[type='submit']")
public WebElement btnLogin;

@FindBy(css= "[data-testid=AccountCircleIcon]")
public List<WebElement> btnProfileIcon2;

@FindBy(css = "[role=menuitem]")
public List<WebElement> btnprofileLogout;



public LoginPage(WebDriver driver){

    PageFactory.initElements(driver,this);
}

public void doLogin(String email,String password){

    txtEmail.sendKeys(email);
    txtPassword.sendKeys(password);
    btnLogin.click();
}

public void doLogout(){
    btnProfileIcon2.getFirst().click();
    btnprofileLogout.get(1).click();



}


}

