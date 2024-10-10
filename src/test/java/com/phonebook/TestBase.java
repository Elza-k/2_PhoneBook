package com.phonebook;


import com.phonebook.core.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));
    Logger logger = LoggerFactory.getLogger(TestBase.class);




//@BeforeMethod
    @BeforeSuite//браузер инициали
    public void setUp() {
        app.init();//тут мы инициализируем браузер, метод находится в  @BeforeSuite это позволяет не открывать при каждом тесте новое окно браузера
        logger.info("*************************TESTING IN  PROGRESS**********************");
    }
    @BeforeMethod//каждый раз в новом окне. Перед каждым тестом
    public void startTest(Method method) {
        logger.info(("Test is started:[" + method.getName() + "]"));
       // app.init();
    }

    @AfterMethod
    public void stopTest(Method method, ITestResult result,ITestResult context) {
        StringBuilder parameters = new StringBuilder();
        for (String key : context.getAttributeNames()) {
            Object value = context.getAttribute(key);
            parameters.append(key).append("=").append(value).append(", ");//Будет выводится в консоли после атрибута равно, потом значение.
        }
        if (parameters.length() > 0) {
            parameters.setLength(parameters.length() - 2);
        }

        logger.info("Test is started: [" + method.getName() + "], with data: [" + parameters + "]");
        if (result.isSuccess()) {
            logger.info("Test is PASSED: [" + method.getName() + "]");
        } else {
            // Проверяем наличие алерта и закрываем его с помощью isAlertPresent
            if (app.getUserHelper().isAlertPresent()) {
                logger.warn("Alert was present and has been accepted.");
            } else {
                logger.info("No alert present.");
            }
            // Теперь делаем скриншот
            String screenshotPath = app.getUserHelper().takeScreenshot();
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: [" + screenshotPath + "]");
        }
        logger.info("Test is ended: [" + method.getName() + "]");
    }

//@BeforeMethod
    @AfterSuite(enabled = true)
    public void tearDown() {
       logger.info("*************************ALL TEST END*****************************");
        app.stop();
    }





}
