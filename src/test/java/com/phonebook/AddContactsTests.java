package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactsTests extends TestBase {
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preConditions() {//если кнопки SignOutButton нет, то делаем логин
        if (!app.getUserHelper().isSignOutButtonPresent()) {
            app.getUserHelper().login("positiveexample@example.com", "Qwery12345@");
        }
    }

    @Test(invocationCount = 1, priority = 1)
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().iscontactAdded(CONTACT_NAME));//trye/false если контакт на странице найден
    }



    @Test(priority = 2)
    public void addContactPositiveWODescriptionTest() {
        app.getContactHelper().addNewContactPositiveDataWODescription(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().iscontactAdded(CONTACT_NAME));//trye/false если контакт на странице найден
    }
    @AfterMethod(enabled = false)
    public void postConditions() {
        app.getContactHelper().deleteOneContact();
    }
}
