package com.teradata.selenium.playground.pages;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * BasePage is parent class of all pages, contains main methods to work with Web page
 *
 * @author Alina Roshchupkina
 */
public class BasePage {

    protected WebDriver driver;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Base page constructor
     *
     * @param driver - web driver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * execute javascript query via selenium webdriver (Javascript executor)
     *
     * @param jsQuery - javascript query for execution
     * @return - result of executed script
     */
    public Object executeJS(String jsQuery) {
        return ((JavascriptExecutor) driver).executeScript(jsQuery);
    }

    /**
     * resize browser window
     *
     * @param width - width of browser in pixels
     * @param height - heigh of browser in pixels
     */
    public void resizeBrowser(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);
    }

    /**
     * verify if element presents on the page
     *
     * @param by - locating mechanism (xpath, css, id) and locator
     * @return - boolean true or false depending on element presents
     */
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * fluent wait with timeout 15 seconds and pulling every 500 milliseconds
     *
     * @param locator - locating mechanism (xpath, css, id) and locator
     * @return - WebElement located by locator
     */
    public WebElement fluentWait(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        return foo;
    }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}
