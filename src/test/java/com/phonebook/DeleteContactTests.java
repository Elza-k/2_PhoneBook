package com.phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {
    private final String CONTACT_NAME = "TestName";


    @BeforeMethod
    public void preconditions() {
       app.getUserHelper(). login("positiveexample@example.com", "Qwery12345@");
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
    }

    @Test
    public void createAnddeleteOneContactTest() {
        int sizeBefore = app.getContactHelper().actualSizeOfContacts();
        System.out.println(sizeBefore);
        app.getContactHelper().deleteOneContact();

        app.wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className(app.getContactHelper().CONTACT_LOCATOR), sizeBefore));
        int sizeAfterDelete = app.getContactHelper().actualSizeOfContacts();
        System.out.println(sizeAfterDelete);

        Assert.assertEquals(sizeBefore - 1, sizeAfterDelete, "Count is not equal");//будут ли они равны если мы уменьшим число на один от фактического результата
    }

    @Test
    public void deleteAllContactsTests() {
        app.getContactHelper().deleteAllComtacts();
        Assert.assertEquals(  app.getContactHelper().actualSizeOfContacts(), 0, "Count is not equal");//мы ожидаем, что после удаления контактов-будт 0 контактов. И если  0 равен 0, то ассерт пройдет
    }


}
