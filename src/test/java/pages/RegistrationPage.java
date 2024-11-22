package pages;

import Config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {

    @FindBy(xpath = "//a[@href='/register']")
    public WebElement btnSubmit;

   @FindBy(id= "firstName")
    WebElement txtFirstName;

@FindBy(id = "lastName")
    WebElement txtLastName;
@FindBy(id = "email")
    WebElement txtEmail;

@FindBy(id="password")
WebElement txtPassword;

@FindBy(id = "phoneNumber")
WebElement txtPhoneNumber;

 @FindBy(id = "address")
    WebElement txtAddress;

 @FindBy(css= "[type=radio]")
 List <WebElement> rbGender;


 @FindBy(css = "[type= checkbox]")
 WebElement chkAcceptTerm;

 @FindBy(id = "register")
     WebElement btnRegister;

 public RegistrationPage(WebDriver driver){
     PageFactory.initElements(driver,this);
 }

    public void doReg(String firstName, String lastName, String email,String password, String phoneNumber,String address ){


txtFirstName.sendKeys(firstName);
txtLastName.sendKeys(lastName);
txtEmail.sendKeys(email);
txtPassword.sendKeys(password);
txtPhoneNumber.sendKeys(phoneNumber);
txtAddress.sendKeys(address);
rbGender.get(1).click();
chkAcceptTerm.click();
btnRegister.click();
    }

    public void doReg(UserModel userModel) {
    }
}


