package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {
    @BeforeMethod
            public void preconditions() {
        app.driver.get("https://telranedu.web.app/home");//независимо от того на каком тесте был наш юзер, он должен вернуться на стартовую страницу
    }
    @Test
    public void isHomeComponentPresentTest() {//находится ли на домашней странице этот элемент
       // driver.findElement(By.xpath("//html/body/div/div/div/div/h1"));
        //driver.findElement(By.cssSelector("div:nth-child(2)>div>div>h1"));
        Assert.assertTrue(app.getHomeHelper().isHomeComponentPresent(), "Item not found on pages");
        System.out.println("'HomeComponent'element found on home page");
    }

}
