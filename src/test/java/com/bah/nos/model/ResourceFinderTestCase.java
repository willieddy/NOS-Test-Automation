package com.bah.nos.model;

import java.util.ArrayList;
import java.util.List;

public class ResourceFinderTestCase {

    private String title;

    private String summary;

    private List<ResourceFinderAnswer> answers = new ArrayList();

    private ResourceFinderResult result = new ResourceFinderResult();

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

    public ResourceFinderResult getResult() {
        return result;
    }

    public void setResult(ResourceFinderResult result) {
        this.result = result;
    }
}
