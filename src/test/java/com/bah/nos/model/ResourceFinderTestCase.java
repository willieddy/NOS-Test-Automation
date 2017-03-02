package com.bah.nos.model;

import java.util.List;

public class ResourceFinderTestCase {

    private String title;

    private String summary;

    private List<ResourceFinderAnswer> answers;

    private List<ResourceFinderAssertion> assertions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<ResourceFinderAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ResourceFinderAnswer> answers) {
        this.answers = answers;
    }

    public List<ResourceFinderAssertion> getAssertions() {
        return assertions;
    }

    public void setAssertions(List<ResourceFinderAssertion> assertions) {
        this.assertions = assertions;
    }
}
