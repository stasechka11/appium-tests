
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import screens.MainScreen;

public class AppiumTest {

    private AppiumDriver driver;
    private MainScreen mainScreen;
    private final String textToSet = "Hello!";

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("appium:deviceName", "name");
        caps.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        caps.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        caps.setCapability("appium:automationName", "uiautomator2");
        caps.setCapability("appium:ensureWebviewsHavePages", true);
        caps.setCapability("appium:nativeWebScreenshot", true);
        caps.setCapability("appium:newCommandTimeout", 3600);
        caps.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AppiumDriver(this.getUrl(), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void changeToEmptyTextTest() {
        mainScreen = new MainScreen(driver);
        String textToBeChanged = mainScreen.mainScreenText.getText();

        mainScreen.userInput.sendKeys("");
        mainScreen.changeTextButton.click();

        String resultText = mainScreen.mainScreenText.getText();

        Assertions.assertEquals(textToBeChanged, resultText);

/*
        String textToBeChanged = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged").getText();
        driver.findElementById("ru.netology.testing.uiautomator:id/userInput").sendKeys("");
        MobileElement changeTextButton = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        changeTextButton.click();
        String resultText = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged").getText();
*/


    }

    @Test
    public void setNewActivityTest() {
        driver.findElementById("ru.netology.testing.uiautomator:id/userInput").sendKeys(textToSet);

        MobileElement openTextInAnotherActivityButton = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        openTextInAnotherActivityButton.click();
        driver.findElementById("ru.netology.testing.uiautomator:id/text").isDisplayed();
        String resultText = driver.findElementById("ru.netology.testing.uiautomator:id/text").getText();

        Assertions.assertEquals(textToSet, resultText);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
