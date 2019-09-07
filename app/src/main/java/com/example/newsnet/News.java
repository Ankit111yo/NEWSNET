package com.example.newsnet;

public class News  {

    private String mSource;
    private String mTitle;
    private String mUrl;
    private String mImageUrl;
    private String mDescription;
    private String mDate;
    private String mContent;

    public News(String src,String ttl,String url,String imurl,String mdesc,String date,String content)
    {
        mSource=src;
        mTitle=ttl;
        mUrl=url;
        mImageUrl=imurl;
        mDescription=mdesc;
        mDate=date;
        mContent=content;
    }



    public String getmUrl() {
        return mUrl;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmContent() {
        return mContent;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmSource() {
        return mSource;
    }

    public String getmTitle() {
        return mTitle;
    }
}
