package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MainScreen {

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    public MobileElement mainScreenText;

    @AndroidFindBy(id = "")
    public MobileElement userInput;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    public MobileElement changeTextButton;

    private AppiumDriver driver;

    public MainScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }
}
