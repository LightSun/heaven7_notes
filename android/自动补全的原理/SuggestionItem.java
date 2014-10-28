package com.example.xiyougame;
public class SuggestionItem {
    private String mUrl;
    private String mTitle;
    
    public SuggestionItem(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }
}