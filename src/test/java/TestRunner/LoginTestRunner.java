package TestRunner;

import Config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
@Test(priority = 1, description = "Admin login without wrong creds",enabled = false)
    public void adminLoginWrongCreds() throws InterruptedException {

        loginPage= new LoginPage(driver);
        loginPage.doLogin("admin@test1.com", "admin12365");

        String errorMessageActual = driver.findElement(By.tagName("p")).getText();
        String errorMessageExpected = "Invalid";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
    clearCreds();

    }



    @Test(priority = 2, description = "Admin login with right creds" , enabled = false)
    public void doLogin() throws InterruptedException {
        loginPage= new LoginPage(driver);
        loginPage.doLogin("admin@test.com", "admin123");


        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected));

    }
@Test(priority = 3, description = "User can log in from data")
    public void userLogin() throws IOException, ParseException {
    loginPage=new LoginPage(driver);
        JSONParser parser= new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        jsonArray.get(jsonArray.size()-1);


        JSONObject useObj=(JSONObject) jsonArray.get(jsonArray.size()-1);

        String email= (String) useObj.get("email");
        String password= (String) useObj.get("password");


        loginPage.doLogin(email,password);

    Assert.assertTrue(loginPage.btnProfileIcon2.getFirst().isDisplayed(), "Profile icon is not displayed, login may have failed.");

      loginPage.doLogout();



}

    public void clearCreds() throws InterruptedException {
    loginPage= new LoginPage(driver);
    loginPage.txtEmail.sendKeys(Keys.CONTROL,"a");
    loginPage.txtEmail.sendKeys(Keys.BACK_SPACE);

    loginPage.txtPassword.sendKeys(Keys.CONTROL,"a");
    loginPage.txtPassword.sendKeys(Keys.BACK_SPACE);

    }

}
