package ru.netology.qa;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.FirstScreen;
import ru.netology.qa.screens.SecondScreen;

import java.net.MalformedURLException;
import java.net.URL;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppiumTest {

    private AndroidDriver driver;
    private final static String text = "heLLo";
    private final static String spaces = "   ";


    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 5 API 30");
        desiredCapabilities.setCapability("appium:app", "C:\\Netology\\testApk\\app-debug.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testFirst() {
        FirstScreen firstScreen = new FirstScreen(driver);
        firstScreen.userInput.click();
        firstScreen.userInput.setValue(text);
        firstScreen.buttonChange.click();
        Assertions.assertEquals("heLLo", firstScreen.textToBeChanged.getText());
        firstScreen.userInput.clear();
        firstScreen.userInput.click();
        Assertions.assertEquals("heLLo", firstScreen.textToBeChanged.getText());
        Assertions.assertNotEquals("", firstScreen.textToBeChanged.getText());
    }

    @Test
    public void testSecond() {
        FirstScreen firstScreen = new FirstScreen(driver);
        SecondScreen secondScreen = new SecondScreen(driver);
        firstScreen.userInput.click();
        firstScreen.userInput.setValue(text);
        firstScreen.buttonActivity.click();
        Assertions.assertEquals("heLLo", secondScreen.resultField.getText());
        }

    @Test
    public void testInputNull() {


        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.click();
        el1.setValue(text);
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("textToBeChanged");
        Assertions.assertEquals(text, el3.getText());
        el1.clear();
        el2.click();
        Assertions.assertNotEquals("", el3.getText());


    }

    @Test
    public void testInput() {
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.click();
        el1.setValue(text);
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("textToBeChanged");
        Assertions.assertEquals(text, el3.getText());
        el1.click();
        el1.setValue(spaces);
        el2.click();
        Assertions.assertNotEquals(spaces, el3.getText());

    }

    @Test
    public void testNewActivity() {
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.sendKeys(text);
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.TextView\n");
        Assertions.assertEquals(text, el3.getText());
    }


    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
