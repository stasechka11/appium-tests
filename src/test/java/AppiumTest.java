
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import screens.MainScreen;

public class AppiumTest {

    private AppiumDriver driver;
    //private MainScreen mainScreen;

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

        driver = new AndroidDriver(this.getUrl(), caps);
        //driver = new AppiumDriver<>(new URL("http://127.0.0.1:4723"), caps);

    }

    @Test
    public void changeToEmptyTextTest() {
       /* MainScreen mainScreen = new MainScreen(driver);
        String textToBeChanged = mainScreen.mainScreenText.getText();

        mainScreen.userInput.sendKeys("");
        mainScreen.changeTextButton.click();

        String resultText = mainScreen.mainScreenText.getText();*/

         String textToBeChanged = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged").getText();
      driver.findElementById("ru.netology.testing.uiautomator:id/userInput").sendKeys("");
        MobileElement changeTextButton = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        changeTextButton.click();
        String resultText = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged").getText();

        Assertions.assertEquals(textToBeChanged, resultText);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}