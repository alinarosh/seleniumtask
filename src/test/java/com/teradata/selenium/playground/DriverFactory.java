package com.teradata.selenium.playground;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/**
 * DriverFactory class allows create different types of driver based on browsers (Chrome, Firefox, IE)
 * To use appropriate driver send in VM options browser parameter (eg. -Dbrowser=chrome)
 *
 * @author Alina Roshchupkina
 */
public class DriverFactory {

    public static WebDriver getDriver(String type) {
        String browser = System.getenv("browser.name");
        if (type.equalsIgnoreCase("chrome")) {
            return createChrome();
        } else if (type.equalsIgnoreCase("firefox")) {
            return createFirefox();
        } else if (type.equalsIgnoreCase("ie")) {
            return createInternetExplorer();
        } else {
            return null;
        }
    }

    private static WebDriver createChrome() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        return new ChromeDriver();
    }

    private static WebDriver createFirefox() {
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    private static WebDriver createInternetExplorer() {
        if (System.getProperty("os.arch").contains("64")) {
            System.setProperty("webdriver.ie.driver", "IEDriverServer64.exe");
        } else {
            System.setProperty("webdriver.ie.driver", "IEDriverServer32.exe");
        }
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    }
}

