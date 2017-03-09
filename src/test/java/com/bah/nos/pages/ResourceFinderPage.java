package com.bah.nos.pages;

import com.bah.nos.model.ResourceFinderAnswer;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.nativeonestop.gov/resources/resource-finder")
public class ResourceFinderPage extends NosBasePage {

    /*
    Example Radio XPATH logic
    (1) is the div indentified by PARENT_QUESTION_XPATH
    (2) is the line used to identify the correct div on the page for (1)
    (3) is the input to click or type into, identified by the different values if radio or text

    (1)<div id="cat_1_1006" class="ui-question" validated="false">
        <legend class="ui-question-legend">
            <div class="ui-question-text" id="qid_1006">
    (2)         <span class="badge badge-info ui-question-text-number">2</span>
                <span class="ui-question-text-alpha">What is your current employment status?</span>
            </div>
        </legend>
        <div class="ui-question-answer">
            <label for="option_1006_1" style="max-width: 450px;">
    (3)         <input name="1006_name" id="option_1006_1" value="1"
                    data-answerlabel="Employed (full-time)" type="radio">
                Employed (full-time)
            </label>
        </div>
        <div class="ui-question-answer">
            <label for="option_1006_2" style="max-width: 450px;">
                <input name="1006_name" id="option_1006_2" value="2"
                    data-answerlabel="Employed (part-time or seasonal)" type="radio">
                Employed (part-time or seasonal)
            </label>
        </div>
        <div class="ui-question-answer">
            <label for="option_1006_3" style="max-width: 450px;">
                <input name="1006_name" id="option_1006_3" value="3"
                    data-answerlabel="Under-employed (working for very low wages)" type="radio">
                Under-employed (working for very low wages)
            </label>
        </div>
        <div class="ui-question-answer">
            <label for="option_1006_4" style="max-width: 450px;">
                <input name="1006_name" id="option_1006_4" value="4"
                    data-answerlabel="Unemployed or about to become unemployed" type="radio">
                Unemployed or about to become unemployed
            </label>
        </div>
        <div class="ui-question-answer">
            <label for="option_1006_5" style="max-width: 450px;">
                <input name="1006_name" id="option_1006_5" value="5"
                    data-answerlabel="Retired or not seeking employment" type="radio">
                Retired or not seeking employment
            </label>
        </div>
    </div>
     */

    // -------- Question and answer xpath strings --------

    private static final String ACTIVE_SECTION_XPATH = "//div[@class='tab-content']";

    private static final String PARENT_QUESTION_XPATH = ACTIVE_SECTION_XPATH +
            "/fieldset/div[./legend/div/span[@class='badge badge-info ui-question-text-number' and text()='%d']]";

    //The string input for answer label does not include single quotes, required to be placed by String.format
    private static final String ANSWER_BUTTON_XPATH = PARENT_QUESTION_XPATH +
            "/div/label/input[normalize-space(@data-answerlabel)=%s]";

    private static final String TEXT_INPUT_XPATH = PARENT_QUESTION_XPATH + "/div/input[@type='text']";


    // -------- Page navigation xpath strings --------

    private static final String SECTION_NAV_XPATH =
            "//ul[@class='nav nav-tabs']/li/a/div[text()=%s]";

    private static final String PAGE_NUM_XPATH = "//div[@class='tab-content']/p/ul/li/a[text()='%d']";


    @FindBy(xpath = "//div[@class='tab-content']/h6[@class='result-header']")
    private WebElementFacade sectionTitle;

    @FindBy(xpath = "//div[@class='tab-content']/p/ul/li[@class='active' or @class='active disabled']/a")
    private WebElementFacade currentPageNum;

    @FindBy(xpath = "//div[@id='bc-value']")
    private WebElementFacade benefitTotal;

    @FindBy(xpath = "//tbody[@id='resultTableBody']/tr/td/h2/a")
    private List<WebElementFacade> resourceTitles;

    @FindBy(xpath = "//div[@class='tab-content']//a[@title='Next']")
    private WebElementFacade nextButton;

    public ResourceFinderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ResourceFinderPage answer(ResourceFinderAnswer answer) {
        navigateToSection(answer);

        switch (answer.getAnswerType()) {
            case RADIO:
                clickAnswerButton(answer.getQuestionId(), answer.getAnswer().get(0));
                break;
            case TEXT:
                enterText(answer.getQuestionId(), answer.getAnswer().get(0));
                break;
            case CHECKBOX:
                answer.getAnswer().forEach(s -> clickAnswerButton(answer.getQuestionId(), s));
                break;
            default:
                throw new UnsupportedOperationException("Answer type not supported: " + answer.getAnswerType());
        }

        return this;
    }

    /**
     * Navigates to correct section and page within that section if not already there
     * @param answer
     */
    private void navigateToSection(ResourceFinderAnswer answer) {
        scrollToTop();

        sectionTitle.waitUntilVisible();

        String sectionName = answer.getSection().getSectionTitle();

        // If we're not in right section, navigate to that section
        if (!sectionName.equalsIgnoreCase(sectionTitle.getText())) {
            String sectionNavXpath = String.format(SECTION_NAV_XPATH, formatXpathStringInput(sectionName));
            WebElementFacade sectionNavButton = find(By.xpath(sectionNavXpath));
            sectionNavButton.click();
        }

        Integer curPageNum =  Integer.parseInt(currentPageNum.getText());
        // If we're not on the right page in the section, navigate to that page
        if (!curPageNum.equals(answer.getPageNum())) {
            String pageNumXpath = String.format(PAGE_NUM_XPATH, answer.getPageNum());
            WebElementFacade pageNumButton = find(By.xpath(pageNumXpath));
            pageNumButton.click();
        }

        scrollToTop();

    }

    public String getSectionTitle() {
        return sectionTitle.getText();
    }

    public ResourceFinderPage waitUntilBenefitTotalVisible() {
        benefitTotal.waitUntilVisible();
        return this;
    }

    public Integer getBenefitTotal() {
        return Integer.parseInt(benefitTotal.getText());
    }

    public ResourceFinderPage clickBenefitTotal() {
        benefitTotal.click();
        return this;
    }

    public ResourceFinderPage clickNextButton() {
        nextButton.click();
        return this;
    }

    public List<String> getResourceTitles() {
        return resourceTitles.stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }

    private void enterText(Integer questionId, String answer) {
        String answerXpath = String.format(TEXT_INPUT_XPATH, questionId);
        WebElementFacade textField = find(By.xpath(answerXpath));
        textField.type(answer);
    }

    private void clickAnswerButton(Integer questionId, String answer) {
        String answerXpath = String.format(ANSWER_BUTTON_XPATH, questionId, formatXpathStringInput(answer));
        WebElementFacade button = find(By.xpath(answerXpath));
        button.click();
    }

}
