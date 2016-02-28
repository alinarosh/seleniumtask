package com.teradata.selenium.playground.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class is created to describe all web elements on Playground page and methods to work with them
 *
 * @author Alina Roshchupkina
 */
public class PlaygroundPage extends BasePage {

    private final String PLAYGROUND_PAGE_URL = "http://timvroom.com/selenium/playground/";
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @FindBy(id = "name")
    private WebElement inputName;
    @FindBy(id = "occupation")
    private WebElement occupation;
    @FindBy(xpath = "//input[@value='wrotebook']")
    private WebElement radioBtnYes;
    @FindBy(xpath = "//input[@value='didntwritebook']")
    private WebElement radioBtnNo;
    @FindBy(id = "answer1")
    private WebElement inputBox1;
    @FindBy(id = "answer4")
    private WebElement inputBox4;
    @FindBy(id = "answer6")
    private WebElement inputBox6;
    @FindBy(id = "answer8")
    private WebElement inputBox8;
    @FindBy(id = "answer10")
    private WebElement inputBox10;
    @FindBy(id = "answer11")
    private WebElement inputBox11;
    @FindBy(id = "answer13")
    private WebElement inputBox13;
    @FindBy(id = "answer14")
    private WebElement inputBox14;
    @FindBy(id = "redbox")
    private WebElement redBox;
    @FindBy(id = "greenbox")
    private WebElement greenBox;
    @FindBy(id = "orangebox")
    private WebElement orangeBox;
    @FindBy(id = "purplebox")
    private WebElement purpleBox;
    private String blueBox = ".//*[@class='bluebox']";
    @FindBy(xpath = ".//a[contains(text(), 'click me')]")
    private WebElement linkClickMe;
    @FindBy(xpath = ".//a[contains(text(), 'click then wait')]")
    private WebElement linkClickThenWait;
    private String linkClickAfterWait = ".//a[contains(text(), 'click after wait')]";
    @FindBy(xpath = ".//h3[contains(text(),'Boxes to check')]/following::span[1]")
    private WebElement topBox;
    @FindBy(id = "submitbutton")
    private WebElement submitBtn;
    @FindBy(id = "checkresults")
    private WebElement checkResBtn;
    @FindBy(id = "showresults")
    private WebElement resultsText;

    public PlaygroundPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public PlaygroundPage open() {
        driver.get(PLAYGROUND_PAGE_URL);
        return this;
    }

    public void grabPageTitle() {
        inputBox1.sendKeys(getPageTitle());
    }

    public void fillOutNameSection(String name) {
        inputName.sendKeys(name);
    }

    public void fillOccupation(String occupationValue) {
        Select dropdown = new Select(occupation);
        dropdown.selectByValue(occupationValue);
    }

    public void countBlueBoxes() {
        int boxCount = driver.findElements(By.xpath(blueBox)).size();
        inputBox4.sendKeys(Integer.toString(boxCount));
    }

    public void clickOnLink() {
        linkClickMe.click();
    }

    public void getClassNameOfRedBox() {
        WebElement webElement = driver.findElement(By.id("redbox"));
        String classText = webElement.getAttribute("class");
        inputBox6.sendKeys(classText);
    }

    public void executeJsAndReturnResult(String jsQuery) {
        String jsValue = executeJS(jsQuery).toString();
        inputBox8.sendKeys(jsValue);
    }

    public void clickRadioBtnYes() {
        radioBtnYes.click();
    }

    public void getTextFromRedBox() {
        String textRedBox = redBox.getText();
        inputBox10.sendKeys(textRedBox);
    }

    //Method can be also implemented using xpath "//h3[contains(text(),'Boxes to check')]/following::span[1]"
    public void getColorOfTopBox() {
        int greenBoxLocation = greenBox.getLocation().getY();
        int orangeBoxLocation = orangeBox.getLocation().getY();
        String color = greenBoxLocation < orangeBoxLocation ? "green" : "orange";
        inputBox11.sendKeys(color);
    }

    public void getStatusOfElement() {

        if (isElementPresent(By.id("ishere"))) {
            inputBox13.sendKeys("yes");
        } else inputBox13.sendKeys("no");
    }

    public void isPurpleBoxVisible() {
        if (purpleBox.isDisplayed()) {
            inputBox14.sendKeys("yes");
        } else inputBox14.sendKeys("no");
    }

    public void waitGame() {
        linkClickThenWait.click();
        fluentWait(By.xpath(linkClickAfterWait)).click();
    }

    public void submitForm() {
        submitBtn.click();
    }

    public void checkResults() {
        checkResBtn.click();
        String results = resultsText.getText();
        logger.info("Test results: " + results);
        int actual = Integer.parseInt(results.split("of")[0].replaceAll("[^\\d.]", ""));
        int expected = Integer.parseInt(results.split("of")[1].replaceAll("[^\\d.]", ""));
        Assert.assertEquals("Comparing of expected and actual steps 'passed' status",
                expected, actual);
    }
}

