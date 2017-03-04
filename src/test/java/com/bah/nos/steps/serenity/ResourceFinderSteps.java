package com.bah.nos.steps.serenity;

import com.bah.nos.model.ResourceFinderAnswer;
import com.bah.nos.model.ResourceFinderAssertions;
import com.bah.nos.model.ResourceFinderTestCase;
import com.bah.nos.pages.ResourceFinderPage;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ResourceFinderSteps {
    private static final Logger log = LoggerFactory.getLogger(ResourceFinderSteps.class);

    private ResourceFinderPage currentPage;

    @Step
    public void openPage() {
        currentPage.open();
    }

    @Step
    public void completeResourceFinder(String testCaseFileName) throws IOException {
        ResourceFinderTestCase testCase = getTestCase(testCaseFileName);

        boolean hasLeftCore = false;
        for (ResourceFinderAnswer answer : testCase.getAnswers()) {
            currentPage = currentPage.answer(answer);
        }
    }

    @Step
    public void verifyBenefitsDisplayed(String testCaseFileName) throws IOException {
        ResourceFinderAssertions assertions = getTestCase(testCaseFileName).getAssertions();

        //TODO Verify next button is clickable
        if (ResourceFinderAnswer.QuestionSectionEnum.CORE.name().equals(currentPage.getSectionTitle())) {
            currentPage = currentPage.clickNextButton().waitUntilBenefitTotalVisible();

            try {
                // I don't like sleeping in tests, waiting on elements to load is better,
                // but this element loads with an incorrect value then corrects itself
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error("Sleep Interrupted!!!");
            }
        }

        Assert.assertEquals(assertions.getTotalResourceCount(), currentPage.getBenefitTotal());

        currentPage = currentPage.clickBenefitTotal();

        List<String> resourceTitles = currentPage.getResourceTitles();

        assertions.getResourceTitles().forEach(s -> Assert.assertTrue(resourceTitles.contains(s)));
    }

    private ResourceFinderTestCase getTestCase(String testCaseFileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("stories/findResources/" + testCaseFileName);
        String json = IOUtils.toString(inputStream);

        return mapper.readValue(json, ResourceFinderTestCase.class);
    }
}
