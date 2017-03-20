package com.bah.nos.model.pages;

import com.bah.nos.model.ResourceFinderAnswer;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

@DefaultUrl("http://local.nativeonestop.gov/resources/resource-finder")
public class ResourceFinderQuestionPage extends ResourceFinderPage {

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

    // -------- Static xpath derived elements --------

    @FindBy(xpath = "//div[@class='tab-content']/h6[@class='result-header']")
    private WebElementFacade sectionTitle;

    @FindBy(xpath = "//div[@class='tab-content']/p/ul/li[@class='active' or @class='active disabled']/a")
    private WebElementFacade currentPageNum;

    @FindBy(xpath = "//div[@class='tab-content']//a[@title='Next']")
    private WebElementFacade nextButton;

    public ResourceFinderQuestionPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void answer(ResourceFinderAnswer answer) {
        navigateToSection(answer);

        // Might have scrolled past answer when checking page num
        scrollToTop();

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
    }

    /**
     * Navigates to correct section and page within that section if not already there
     * @param answer
     */
    private void navigateToSection(ResourceFinderAnswer answer) {
        boolean screenshot = false;
        scrollToTop();

        sectionTitle.waitUntilVisible();

        String sectionName = answer.getSection().getSectionTitle();

        // If we're not in right section, navigate to that section
        if (!sectionName.equalsIgnoreCase(sectionTitle.getText())) {
            Serenity.takeScreenshot();
            screenshot = true;

            String sectionNavXpath = String.format(SECTION_NAV_XPATH, formatXpathStringInput(sectionName));
            WebElementFacade sectionNavButton = find(By.xpath(sectionNavXpath));
            sectionNavButton.click();
        }

        Integer curPageNum =  Integer.parseInt(currentPageNum.getText());
        // If we're not on the right page in the section, navigate to that page
        if (!curPageNum.equals(answer.getPageNum())) {
            // If we didn't change section but need to changed page, screenshot before change
            if (!screenshot) Serenity.takeScreenshot();

            String pageNumXpath = String.format(PAGE_NUM_XPATH, answer.getPageNum());
            WebElementFacade pageNumButton = find(By.xpath(pageNumXpath));
            pageNumButton.click();
        }

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

    public String getSectionTitle() {
        return sectionTitle.getText();
    }

    public void clickNextButton() {
        nextButton.click();
    }

}
