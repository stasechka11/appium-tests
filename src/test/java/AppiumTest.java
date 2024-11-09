
import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.net.URL;

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
        mainScreen = new MainScreen(driver);
    }

    @Test
    public void changeToEmptyTextTest() {
        String textToBeChanged = mainScreen.mainScreenText.getText();

        mainScreen.userInput.sendKeys("");
        mainScreen.changeTextButton.click();

        String resultText = mainScreen.mainScreenText.getText();

        Assertions.assertEquals(textToBeChanged, resultText);

    }

    @Test
    public void setNewActivityTest() {
        mainScreen.userInput.sendKeys(textToSet);
        mainScreen.openTextInAnotherActivityButton.click();

        mainScreen.textNewActivity.isDisplayed();
        String resultText = mainScreen.textNewActivity.getText();

        Assertions.assertEquals(textToSet, resultText);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
