package com.teradata.selenium.playground.tests;

import com.teradata.selenium.playground.pages.PlaygroundPage;
import org.testng.annotations.Test;

/**
 * Class contains playground test
 *
 * @author Alina Roshchupkina
 */
public class SeleniumPlaygroundTest extends TestInit {

    private static final String RAN_JS_FUNCTION = "ran_this_js_function()";
    private static final String GOT_RETURN_FROM_JS = "return got_return_from_js_function();";

    @Test
    public void playgroundTest() {

        playgroundPage.open();

        //Step 1: Grab page title and place title text in answer slot #1
        playgroundPage.grabPageTitle();

        //Step 2: Fill out name section of form to be Kilgore Trout
        String name = "Kilgore Trout";
        playgroundPage.fillOutNameSection(name);

        //Step 3: Set occupation on form to Sci-Fi Author
        String occupationValue = "scifiauthor";
        playgroundPage.fillOccupation(occupationValue);

        //Step 4: Count number of blue_boxes on page after form and enter into answer box #4
        playgroundPage.countBlueBoxes();

        //Step 5: Click link that says 'click me'
        playgroundPage.clickOnLink();

        //Step 6:Find red box on its page find class applied to it, and enter into answer box #6
        playgroundPage.getClassNameOfRedBox();

        //Step 7: Run JavaScript function as: ran_this_js_function() from your Selenium script
        playgroundPage.executeJS(RAN_JS_FUNCTION);

        //Step 8: Run JavaScript function and take returned value and place it in answer slot #8
        playgroundPage.executeJsAndReturnResult(GOT_RETURN_FROM_JS);

        //Step 9: Mark radio button on form for written book? to Yes
        playgroundPage.clickRadioBtnYes();

        //Step 10: Get the text from the Red Box and place it in answer slot #10
        playgroundPage.getTextFromRedBox();

        //Step 11: Which box is on top? orange or green -- place correct color in answer slot #11
        playgroundPage.getColorOfTopBox();

        //Step 12: Set browser width to 850 and height to 650
        playgroundPage.resizeBrowser(850, 650);

        //Step 13: Type into answer slot 13 yes or no depending on whether item by id of ishere is on the page
        playgroundPage.getStatusOfElement();

        //Step 14: Type into answer slot 14 yes or no depending on whether item with id of purplebox is visible
        playgroundPage.isPurpleBoxVisible();

        //Step 15: Waiting game
        playgroundPage.waitGame();

        //Step 16: Click OK on the confirm after completing task 15
        playgroundPage.acceptAlert();

        //Step 17: Click the submit button on the form
        playgroundPage.submitForm();

        playgroundPage.checkResults();
    }

}
