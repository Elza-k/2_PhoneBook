package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {
    @Test
    public void createAccountPositiveTest1() {
        //тест уже открывается с домашней страницы.
        // нажимаем на логин локатор //a[.='LOGIN']
        app.getUserHelper().clickLoginLink();
        //нажимаем емейл  in input By.name("email")
        //driver.findElement(By.name("email")).click();
        //fillInRegistrationForm(new User("positiveexample3@example.com", "Qwery12345@"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("positiveexample@example.com")
                .setPasswort("Qwery12345@"));
        //нажимаем на пароль  in input By.name("password")
        //driver.findElement(By.name("password")).click();
        //driver.findElement(By.name("password")).clear();//очищаем поле

        //нажимаем на кнопку registration button By.name("registration")
        app.getUserHelper().clickRegistrationButton();//стратегия поиска по имени, локатор кнопка registration
        // Assert that button //button[.='Sign Out'] is present
        Assert.assertTrue(    app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void createAccountPositiveTest2() {
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com";//System.currentTimeMillis добавляет к емейлу числа в миллисекундах, позволяет генерировать каждый раз новый емейл
        String password = "Password1@";
        app.getUserHelper().register(email, password);//тест уже открывается с домашней страницы.
        // нажимаем на логин локатор //a[.='LOGIN']

        /*app.getUserHelper().register("positiveexample2@example.com", "Qwery12345@");
        //нужно сделать метод логаут
        app.getUserHelper().logout();
        app.getUserHelper().login("positiveexample2@example.com", "Qwery12345@");*/
    }
    @Test
    public void createAccountLoginPositiveTest() {
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password1@";
        app.getUserHelper().register(email, password);
        app.getUserHelper().logout();
        app.getUserHelper().login(email, password);
    }

    //Negative Tests
    @Test
    public void createAccountNegativeTest() {
        //тест уже открывается с домашней страницы.
        // нажимаем на логин локатор //a[.='LOGIN']
        SoftAssert softAssert = new SoftAssert();
        app.getUserHelper().clickLoginLink();
        //нажимаем емейл  in input By.name("email")
        //driver.findElement(By.name("email")).click();
        //fillInRegistrationForm(new User("positiveexample3@example.com", "Qwery12345@"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("positiveexample@example.com")
                .setPasswort("Qwery12345@"));
        //нажимаем на пароль  in input By.name("password")
        //driver.findElement(By.name("password")).click();
        //driver.findElement(By.name("password")).clear();//очищаем поле

        //нажимаем на кнопку registration button By.name("registration")
        app.getUserHelper().clickRegistrationButton();//стратегия поиска по имени, локатор кнопка registration
        // Assert that button //button[.='Sign Out'] is present
        //Assert.assertFalse(isSignOutButtonPresent());
        softAssert.assertTrue(    app.getUserHelper().isAlertPresent());
        //Assert.assertTrue(isAlertPresent());
        //Assert.assertTrue(isError409Present());//появляется ли окно с ошибкой
        softAssert.assertTrue(    app.getUserHelper().isError409Present());

        softAssert.assertAll();
    }
@AfterMethod
    public void postconditions() {
    try {
        app.getUserHelper().logout();
    } catch (Exception e) {
        //throw new RuntimeException(e);
    }
}
}
 /*  В Java, SoftAssert — это класс, предоставляемый библиотекой TestNG, который используется для выполнения "мягких"
        * утверждений (soft assertions) в тестах. В отличие от "жестких" (hard assertions), которые немедленно прерывают
         * выполнение теста при ошибке, мягкие утверждении позволяют продолжить выполнение теста даже если одно из утверждений не прошло.
        * Цель: SoftAssert используется для проверки нескольких условий в рамках одного теста, не прерывая его выполнение при первой неудаче
         */