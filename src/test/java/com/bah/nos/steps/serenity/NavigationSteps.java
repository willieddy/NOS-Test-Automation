package com.bah.nos.steps.serenity;

import com.bah.nos.pages.NosBasePage;
import com.bah.nos.pages.NosPageEnum;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class NavigationSteps {

    private NosBasePage currentPage;

    @Step
    public void openPage(NosPageEnum page) throws ReflectiveOperationException {
        currentPage = page.getPageClass().getDeclaredConstructor(WebDriver.class).newInstance(currentPage.getDriver());
        currentPage.open();
    }

    @Step
    public void navigate(String navBarPath) throws ReflectiveOperationException {
        currentPage = currentPage.navigate(navBarPath);
    }

    @Step
    public void verifyTitle(String navBarPath) {
        NosPageEnum page = NosPageEnum.getPageByNavPath(navBarPath);
        Assert.assertEquals(page.getPageTitle(), currentPage.getPageTitle());
    }

}
