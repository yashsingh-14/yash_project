package com.yash.store.model.enums;

public enum Category {
    POPULAR_COLLECTION("popular-collection"),
    BEST_SELLERS("best-sellers"),
    NEW_ARRIVALS("new-arrivals"),
    SUMMER_ESSENTIALS("summer-essentials"),
    SALE("sale");

    private final String urlName;

    Category(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlName() {
        return urlName;
    }
}
