package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        //logger.info("Hello****** @BeforeSuite");
    }

    //метод который будет выполнять логин нашего user
    @Test
    public void loginExistedUserPositiveTest1() {
        app.getUserHelper().clickLoginLink();
        //click on login link
        //click(By.xpath("//a[.='LOGIN']"));

//нажимаем емейл  in input By.name("email")
        //enter email
        // type(By.name("email"), "positiveexample@example.com");
        //enter password

        //type(By.name("password"), "Qwery12345@");
        // fillInRegistrationForm(new User("positiveexample@example.com", "Qwery12345@"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("positiveexample@example.com")
                .setPasswort("Qwery12345@"));
        //click on login button
        //click(By.name("login"));
        app.getUserHelper().clickOnLoginButton();//стратегия поиска по имени, локатор кнопка registration
        //Assert that button "Sign Out" is present
        //Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2(ITestContext context) {
        //logger.info("********* @Test");
        //click on login link
        String email = "positiveexample@example.com";
        String password = "Qwery12345@";
        context.setAttribute("email", email);
        context.setAttribute("password", password);
        app.getUserHelper().login(email, password);

    }

    @Test
    public void loginNegtiveWOEmailTest() {
//проверяем получится ли залогиниться без email
        app.getUserHelper().clickLoginLink();
        //нажимаем емейл  in input By.name("email")
        //type(By.name("password"), "Qwery12345@");
        app.getUserHelper().fillInRegistrationForm(new User()
                .setPasswort("Qwery12345@"));
        app.getUserHelper().clickOnLoginButton();//стратегия поиска по имени, локатор кнопка registration
        Assert.assertEquals(app.getUserHelper().alertTextPresent(), "Wrong email or password");
        //Assert that button "Sign Out" is present
        //Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
        //Assert.assertTrue(app.getUserHelper().isAlertPresent());
    }

    @AfterMethod(enabled = true)
    public void postconditions() {
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }

    }
}
