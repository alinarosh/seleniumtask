package com.teradata.selenium.playground.tests;

import com.teradata.selenium.playground.DriverFactory;
import com.teradata.selenium.playground.pages.PlaygroundPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * TestInit class is parent class for all test classes
 * contains main setUp of driver, @Before and @After methods
 *
 * @author Alina Roshchupkin
 */
public class TestInit {
    protected WebDriver driver;

    PlaygroundPage playgroundPage;

    //Before each test class actions (setup driver, etc.)
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        if (System.getProperty("browser") == null)
            setDefaultBrowser();
        driver = DriverFactory.getDriver(System.getProperty("browser"));
        driver.manage()
                .timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
        initPages();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    private void setDefaultBrowser() {
        System.setProperty("browser", "firefox");
    }

    private void initPages(){
        playgroundPage = new PlaygroundPage(driver);
    }
}
