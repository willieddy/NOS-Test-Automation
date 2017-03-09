package com.bah.nos.model;

import java.util.ArrayList;
import java.util.List;

public class ResourceFinderAssertions {

    private Integer totalResourceCount;

    private List<String> resourceTitles = new ArrayList<>();

    public Integer getTotalResourceCount() {
        return totalResourceCount;
    }

    public void setTotalResourceCount(Integer totalResourceCount) {
        this.totalResourceCount = totalResourceCount;
    }

    public List<String> getResourceTitles() {
        return resourceTitles;
    }

    public void setResourceTitles(List<String> resourceTitles) {
        this.resourceTitles = resourceTitles;
    }
}
