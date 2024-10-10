package com.phonebook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddItemToCartTests extends TestBase {


    @Test
    public void addItemToCartTest() {
        fillInRegistrationFormWithFirstLastName("firstName", "lastName", "positiveexample8@example.com", "Qwery12345@");
        app.getUserHelper().click(By.xpath("//input[@class='button-1 register-continue-button']"));
        app.driver.get("https://demowebshop.tricentis.com/build-your-own-computer");
        app.getUserHelper().click(By.xpath("//img[@alt='Picture of Build your own computer']"));
        app.getUserHelper().click(By.xpath("//input[@id='product_attribute_16_3_6_18']"));//надо выбрать HDD наче дальше не пускает
        app.getUserHelper().click(By.xpath("//input[@id='add-to-cart-button-16']"));
        app.getUserHelper().click(By.xpath("//span[contains(text(),'Shopping cart')]"));

    }

    public void fillInRegistrationFormWithFirstLastName(String firstName, String lastName, String email, String password) {
        app.driver.get("https://demowebshop.tricentis.com/register");
        app.getUserHelper().click(By.xpath("//input[@id='gender-male']"));
        // Заполнение формы регистрации
        app.getUserHelper().type(By.xpath("//input[@id='FirstName']"), "firstName");
        app.getUserHelper().type(By.xpath("//input[@id='LastName']"), "lastName");
        app.getUserHelper().type(By.xpath("//input[@id='Email']"), "positiveexample8@example.com");
        app.getUserHelper().type(By.xpath("//input[@id='Password']"), "Qwery12345@");
        app.getUserHelper().type(By.xpath("//input[@id='ConfirmPassword']"), "Qwery12345@");
        // Нажимаем кнопку "Register"
        app.getUserHelper().click(By.xpath("//input[@id='register-button']"));
        Assert.assertTrue(app.getUserHelper().isElementPresent(By.xpath("//input[@class='button-1 register-continue-button']")), "Пользователь не зарегистрирован, кнопка 'Continue' не отображается!");

        //app.getUserHelper().moveTo(0,500);//экран опустится в самый низ

    }

}



