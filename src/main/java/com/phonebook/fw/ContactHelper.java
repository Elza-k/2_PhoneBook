package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactHelper extends BaseHelper {
    public final String CONTACT_LOCATOR = "contact-item_card__2SOIM";

    public ContactHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean iscontactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));//массив множества эленентов, которые содержатся с локатором h2
        for (WebElement element : contacts) {
            if (element.getText().contains(textToFind))
                return true;
        }
        return false;
    }

    public void addNewContactPositiveData(String name) {
        //click on Add Link
        clickAddLink();
        fillInNewContactForm(
                new Contact()
                        .setName(name)
                        .setLastName("LastName")
                        .setPhone("1234567888978")
                        .setEmail("positiveexample@example.com")
                        .setAddress("Germany, Berlin")
                        .setDescription("Some Description"));

        // click on Save button
        clickSaveContactButton();
    }

    public void addNewContactPositiveDataWODescription(String name) {
        //click on Add Link
        clickAddLink();
        fillInNewContactForm(
                new Contact()
                        .setName(name)
                        .setLastName("LastName")
                        .setPhone("1234567888978")
                        .setEmail("positiveexample@example.com")
                        .setAddress("Germany, Berlin"));


        // click on Save button
        clickSaveContactButton();
    }

    private void fillInNewContactForm(Contact contact) {
        //enter Name
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
//enter Last e
        type(By.xpath("//input[@placeholder='Last Name']"), "LastName");
        //enter Phone
        type(By.xpath("//input[@placeholder='Phone']"), "1234567888978");
        // enter Email
        type(By.xpath("//input[@placeholder='email']"), "positiveexample@example.com");
        // enter Address
        type(By.xpath("//input[@placeholder='Address']"), "Germany, Berlin");
        // enter Description
        type(By.xpath("//input[@placeholder='description']"), "Some Description");
    }

    private void clickSaveContactButton() {
        click(By.xpath("//b[.='Save']"));
    }

    private void clickAddLink() {
        click(By.xpath("//a[.='ADD']"));
    }

    public void deleteOneContact() {
        click(By.className("contact-item_card__2SOIM"));
        click(By.xpath("//button[.='Remove']"));
    }

    public int actualSizeOfContacts() {
        if (hasContacts()) {//если контакт на сранице есть
            //попадаем в общее поле див с контактами и возвращаем количество
            return driver.findElements(By.cssSelector("h2")).size();
        }
        return 0;
    }

    //метод для проверки наличия элемента на странице
    public boolean hasContacts() {
        return isElementPresent(By.className(CONTACT_LOCATOR));
    }

    public void deleteAllComtacts() {
        try {
            while (hasContacts()) {
                int sizeBefore = actualSizeOfContacts();
                deleteOneContact();
                wait.until((WebDriver d) -> actualSizeOfContacts() < sizeBefore);
            }
            //завернули наш while
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
