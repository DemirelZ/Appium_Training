package Tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Random;

public class Scenarios {

    By lApiDemos = By.xpath("//*[@text= 'API Demos']");
    By lAccessibility = By.xpath("//*[@text='Accessibility']");
    By lViews = By.xpath("//*[@text='Views']");
    By lControls = By.xpath("//*[@text='Controls']");
    By lLightTheme = By.xpath("//*[@text='01. Light Theme']");
    By lInputBox = By.id("com.touchboarder.android.api.demos:id/edit");
    By lCheckBox1 = By.id("com.touchboarder.android.api.demos:id/check1");
    By lRadioButton1 = By.id("com.touchboarder.android.api.demos:id/radio1");
    By lStar = By.id("com.touchboarder.android.api.demos:id/star");
    //    By lAcik = By.xpath("//*[@text='Açık']");
    By lAcik = By.id("com.touchboarder.android.api.demos:id/toggle1");
    By lKapali = By.xpath("//*[@text='Kapalı']");
    By lSelectBox = By.id("com.touchboarder.android.api.demos:id/spinner1");

    By lNextButton = By.id("com.touchboarder.android.api.demos:id/next");

    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;
    String textXpath = "//*[@text=\"{0}\"]";

    @BeforeTest
    public void beforeTest(){
        Driver.runAppium();
        driver = Driver.getDriver(Device.SAMSUNG_J5, App.APIDEMO);
        wait = new WebDriverWait(driver, 10);

    }
    @AfterTest
    public void afterTest(){
        driver.quit();
        Driver.stopAppium();
    }


    @Test
    public void scenario_1() {

        click("API Demos");
        swipeUntil(lViews, .6, .4);
        click(lViews);
        //click("Controls");
        click(xpathOfText("Controls"));

        click(xpathOfText("01. Light Theme"));

        sendKeys(lInputBox, "Controls");

        MobileElement checkBox1 = driver.findElement(lCheckBox1);
        checkBox1.click();
        Assert.assertTrue(Boolean.parseBoolean(checkBox1.getAttribute("checked")));

        MobileElement radioButton1 = driver.findElement(lRadioButton1);
        radioButton1.click();
        Assert.assertEquals(radioButton1.getAttribute("checked"), "true");

        swipeUntil(lStar, .5, .2);

        MobileElement starButton = driver.findElement(lStar);
        starButton.click();
        Assert.assertTrue(Boolean.valueOf(starButton.getAttribute("checked")));

        MobileElement acikButton = driver.findElement(lAcik);
        acikButton.click();
        Assert.assertEquals(acikButton.getAttribute("checked"), "true");

        MobileElement kapaliButton = driver.findElement(lKapali);
        Assert.assertEquals(kapaliButton.getAttribute("checked"), "false");

        click(lSelectBox);

        click(xpathOfText("Mars"));

    }

    @Test
    public void scenario_2() {

        click("API Demos");
        swipeUntil(lViews, .6, .4);
        click(lViews);
        click(xpathOfText("Expandable Lists"));
        click(xpathOfText("1. Custom Adapter"));
        click(xpathOfText("People Names"));

        MobileElement arnold = driver.findElement(xpathOfText("Arnold"));
        Assert.assertTrue(arnold.isDisplayed());

        MobileElement barry = driver.findElement(xpathOfText("Barry"));
        Assert.assertTrue(barry.isDisplayed());

        MobileElement chuck = driver.findElement(xpathOfText("Chuck"));
        Assert.assertTrue(chuck.isDisplayed());

        MobileElement david = driver.findElement(xpathOfText("David"));
        Assert.assertTrue(david.isDisplayed());

        click(xpathOfText("People Names"));


        Boolean arnold1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Arnold")));
        Assert.assertTrue(arnold1);

        ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Barry"));
        ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Chuck"));
        ExpectedConditions.invisibilityOfElementLocated(xpathOfText("David"));

        click(xpathOfText("Fish Names"));

        MobileElement bubbles = driver.findElement(xpathOfText("Bubbles"));
        Assert.assertTrue(bubbles.isDisplayed());

    }

    @Test
    public void scenario_3() {

        click("API Demos");
        swipeUntil(lViews, .6, .4);
        click(lViews);

        swipeUntil(xpathOfText("TextSwitcher"), .8, .2);

        click(xpathOfText("TextSwitcher"));

            Random random=new Random();
            int i = random.nextInt(11)+10;
            System.out.println("i = " + i);

        for (int j = 1; j <= i; j++) {
            click(lNextButton);
        }

        String numberOnScreen = driver.findElement(xpathOfText(""+i+"")).getText();

        int randomNum = Integer.parseInt(numberOnScreen);

        Assert.assertEquals(i,randomNum);

        driver.navigate().back();

    }

    @Test
    public void scenario_5() {

        driver.get("https://demowebshop.tricentis.com/contactus");

        By inputs = By.cssSelector(".page-body input[type='text']");

        String[] arr= {"text1", "text2"};
        for (int i = 0; i < 2; i++) {

            driver.findElements(inputs).get(i);

        }





    }




    void click(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    void sendKeys(By locator, String s) {
        Driver.getDriver().findElement(locator).sendKeys(s);
    }

    void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    void click(String elementName) {
        Driver.getDriver().findElement(By.xpath("//*[@text= '" + elementName + "']")).click();
    }

    By xpathOfText(String... text) {
        return By.xpath(MessageFormat.format(textXpath, text));
    }

    public void swipeV(double startPoint, double stopPoint) {
        int w = driver.manage().window().getSize().width;
        int h = driver.manage().window().getSize().height;

        new TouchAction<>(driver)
                .press(PointOption.point(w / 2, (int) (h * startPoint)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(w / 2, (int) (h * stopPoint)))
                .release()
                .perform();

    }

    public void swipeUntil(By locator, double start, double end) {
        while (driver.findElements(locator).size() <= 0)
            swipeV(start, end);
    }


}
