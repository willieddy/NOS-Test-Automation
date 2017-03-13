package com.bah.nos.model;

import java.util.ArrayList;
import java.util.List;

public class ResourceFinderResult {

    private Integer totalResourceCount;

    private List<String> resultAssertionList = new ArrayList<>();

    private List<String> favorites = new ArrayList<>();

    public Integer getTotalResourceCount() {
        return totalResourceCount;
    }

    public void setTotalResourceCount(Integer totalResourceCount) {
        this.totalResourceCount = totalResourceCount;
    }

    public List<String> getResultAssertionList() {
        return resultAssertionList;
    }

    public void setResultAssertionList(List<String> resultAssertionList) {
        this.resultAssertionList = resultAssertionList;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }
}
