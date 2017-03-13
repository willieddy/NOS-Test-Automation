package com.bah.nos.steps.serenity;

import com.bah.nos.model.ResourceFinderAnswer;
import com.bah.nos.model.ResourceFinderResult;
import com.bah.nos.model.ResourceFinderTestCase;
import com.bah.nos.model.pages.ResourceFinderFavoritePage;
import com.bah.nos.model.pages.ResourceFinderQuestionPage;
import com.bah.nos.model.pages.ResourceFinderResultPage;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ResourceFinderSteps {

    private static final Logger log = LoggerFactory.getLogger(ResourceFinderSteps.class);

    private ResourceFinderQuestionPage questionPage;

    private ResourceFinderResultPage resultPage;

    private ResourceFinderFavoritePage favoritePage;

    @Step
    public void openPage() {
        questionPage.open();
    }

    @Step
    public void answerQuestion(ResourceFinderAnswer resourceFinderAnswer) {
        questionPage.answer(resourceFinderAnswer);
    }

    @Step
    public void navigateToResults() {
        questionPage.clickQuestionnaireNavResults();
    }

    @Step
    public void addResultsToFavorites(String resourceTitle) throws IOException {
        resultPage.addToFavorites(resourceTitle);
    }

    @Step
    public void verifyBenefitsDisplayed(ResourceFinderResult result) throws IOException {
        if (result == null) {
            log.info("No results in test case to assert");
            return;
        }

        questionPage.clickQuestionnaireNavResults();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info("sleep interrupted");
        }

        // Assert total count
        if (result.getTotalResourceCount() != null) {
            Assert.assertEquals(result.getTotalResourceCount(), resultPage.getBenefitTotal());
        }

        // Assert all resource titles on results page
        List<String> resourceTitles = resultPage.getResourceTitles();

        result.getResultAssertionList().forEach(s -> Assert.assertTrue(resourceTitles.contains(s)));

        // Assert all resource titles on favorites page
        resultPage.clickQuestionnaireNavFavorites();

        List<String> favoriteTitles = favoritePage.getFavoriteTitles();
        result.getFavorites().forEach(s -> Assert.assertTrue(favoriteTitles.contains(s)));



    }

}
