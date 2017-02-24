package com.bah.nos.pages;

import org.openqa.selenium.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NosPageEnum {
    HOME("WELCOME TO NATIVE ONE STOP", new ArrayList<>(Arrays.asList("Home")), HomePage.class),
    FINDER("Resource Finder",
            new ArrayList<>(Arrays.asList("Resources", "Resources,Resource Finder")),
            ResourceFinderPage.class),
    CATEGORY("Browse by Category", new ArrayList<>(Arrays.asList("Resources,Browse by Category"))),
    AGENCY("Browse by Agency", new ArrayList<>(Arrays.asList("Resources,Browse by Agency"))),
    TOOLKIT("Native One Stop Toolkit", new ArrayList<>(Arrays.asList("Resources,Native One Stop Toolkit"))),
    ABOUT("Overview", new ArrayList<>(Arrays.asList("About", "About,Overview"))),
    PRIVACY("Privacy Policy", new ArrayList<>(Arrays.asList("About,Privacy Policy"))),
    INDIVIDUALS("FAQs for Individuals", new ArrayList<>(Arrays.asList("Help", "Help,FAQs for Individuals"))),
    TRIBES("FAQs for Tribes", new ArrayList<>(Arrays.asList("Help,FAQs for Tribes")));

    private String pageTitle;
    private List<String> navPathList;
    private Class<? extends NosBasePage> pageClass;

    NosPageEnum(String pageTitle, List<String> navPathList, Class<? extends NosBasePage> pageClass) {
        this.pageTitle = pageTitle;
        this.navPathList = navPathList;
        this.pageClass = pageClass;
    }

    NosPageEnum(String pageTitle, List<String> navTitleList) {
        this(pageTitle, navTitleList, NosBasePage.class);
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public List<String> getNavPathList() {
        return navPathList;
    }

    public Class<? extends NosBasePage> getPageClass() {
        return pageClass;
    }

    public static NosPageEnum getPageByNavPath(String navPath) {
        for (NosPageEnum page : NosPageEnum.values()) {
            for (String curNavPath : page.getNavPathList()) {
                if (curNavPath.equalsIgnoreCase(navPath)) {
                    return page;
                }
            }
        }

        throw new EnumConstantNotPresentException(NosPageEnum.class, navPath);
    }

}