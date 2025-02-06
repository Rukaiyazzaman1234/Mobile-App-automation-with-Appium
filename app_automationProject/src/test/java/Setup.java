import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Setup {
    AndroidDriver driver;
    @BeforeTest
    public AndroidDriver setup() throws MalformedURLException {
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("platformVersion","11");
        caps.setCapability("appPackage","com.google.android.calculator");
        caps.setCapability("appActivity","com.android.calculator2.Calculator");
       // caps.setCapability("app", "D:\\app_automation\\calculator.apk");
        caps.setCapability("app", System.getProperty("user.dir")+"/src/test/resources/calculator.apk"); //dynamic location
        caps.setCapability("automationName","UIAutomator2");

        URL url=new URL("http://127.0.0.1:4723");

        driver=new AndroidDriver(url,caps);  //android driver,it's expect url and cap(obj or caps of android)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;

    }
    //@AfterTest
    public void quitApp(){
        driver.quit();
    }
}