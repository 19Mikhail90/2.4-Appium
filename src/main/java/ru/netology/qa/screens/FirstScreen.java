package ru.netology.qa.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class FirstScreen {

    //** данные для ДЗ */ buttonChange

    @AndroidFindBy(accessibility = "buttonChange")
    public MobileElement buttonChange;

    @AndroidFindBy(accessibility = "buttonActivity")
    public MobileElement buttonActivity;

    @AndroidFindBy(id = "textToBeChanged")
    public MobileElement textToBeChanged;

    @AndroidFindBy(id = "userInput")
    public MobileElement userInput;

    private AppiumDriver driver;

    public FirstScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

}


