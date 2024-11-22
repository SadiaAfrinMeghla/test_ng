package TestRunner;

import Config.Setup;
import Config.UserModel;
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.RegistrationPage;
import utils.Utils;

import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class RegistrationTestRunner extends Setup {
    @Test(priority = 1,description = "user can register by providing all info " )
    public void userRegistration() throws InterruptedException, IOException, ParseException {
        RegistrationPage UserReg= new RegistrationPage(driver);

        Faker faker = new Faker();// to use fake data


        UserReg.btnSubmit.click();
        String firstname= faker.name().firstName();
        String lastname=faker.name().lastName();
        String email = faker.name().username() + "@gmail.com";
        String password= "123";
        String phonenumber= "01711"+ Utils.generateRandomNumber(100000,999999);
        String address=faker.address().fullAddress();
//-------------------------------------------------------------------------------------------------------------
        UserModel userModel= new UserModel();
        userModel.setFirstname(firstname);
        userModel.setFirstname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userModel.setAddress(address);

        UserReg.doReg(userModel);


        sleep(1000);
        WebDriverWait wait= new WebDriverWait( driver, Duration.ofSeconds(50));

        WebElement messageDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='Toastify__toast-body']/div[2]")));
        String toastMessage = messageDiv.getText();
        System.out.println("Toast message: " + toastMessage);

        String successMessageExpected="successfully!";

       Assert.assertTrue(toastMessage.contains(successMessageExpected));








//To store
        JSONObject userObj = new JSONObject();
        userObj.put("firstname", firstname);
        userObj.put("lastname", lastname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber", phonenumber);
        userObj.put("address", address);



        Utils.saveUSerInfo("./src/test/resources/users.json", userObj);


    }
//
//    @Test(priority = 2, description = "user can reg providing the mandatory field ")
//    public void userRegByMandatoryFields() throws IOException, ParseException {
//
//        RegistrationPage UserReg= new RegistrationPage(driver);
//
//        Faker faker = new Faker();// to use fake data
//        UserReg.btnSubmit.click();
//        String firstname= faker.name().firstName();
//        String email = faker.name().username() + "@gmail.com";
//        String password= "123";
//        String phonenumber= "01711"+ Utils.generateRandomNumber(100000,999999);
//
//
//
//        UserModel userModel= new UserModel();
//        userModel.setFirstname(firstname);
//        userModel.setEmail(email);
//        userModel.setPassword(password);
//        userModel.setPhonenumber(phonenumber);
//        UserReg.doReg(userModel);
//
//        JSONObject userObj = new JSONObject();
//        userObj.put("firstname", firstname);
//        userObj.put("email",email);
//        userObj.put("password",password);
//        userObj.put("phoneNumber", phonenumber);
//
//
//
//
//        Utils.saveUSerInfo("./src/test/resources/users.json", userObj);
//
//
//    }

 }


