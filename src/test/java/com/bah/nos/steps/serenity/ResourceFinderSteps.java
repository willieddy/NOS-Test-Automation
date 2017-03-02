package com.bah.nos.steps.serenity;

import com.bah.nos.model.ResourceFinderTestCase;
import com.bah.nos.pages.ResourceFinderPage;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@DefaultUrl("https://www.nativeonestop.gov/resources/resource-finder")
public class ResourceFinderSteps {

    private ResourceFinderPage currentPage;

    @Step
    public void openPage() {
        currentPage.open();
    }

    @Step
    public void completeResourceFinder(String testCaseFileName) throws IOException {
        ResourceFinderTestCase testCase = getTestCase(testCaseFileName);
        currentPage = currentPage.answerQuestions();
    }

    @Step
    public void verifyBenefitsDisplayed(String testCaseFileName) {

    }

    private ResourceFinderTestCase getTestCase(String testCaseFileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("findResources/" + testCaseFileName);
        String json = IOUtils.toString(inputStream);

        return mapper.readValue(json, ResourceFinderTestCase.class);
    }
}
