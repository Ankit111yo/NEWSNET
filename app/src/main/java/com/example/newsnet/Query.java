package com.example.newsnet;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Query {

    private Query(){

    }

    public static ArrayList<News> extract(String response) throws JSONException {
        ArrayList<News> news = new ArrayList<>();
//

            JSONObject rresponse = new JSONObject(response);
            JSONArray newsar =rresponse.getJSONArray("articles");

            for(int i=0;i<newsar.length();i++)
            {
                JSONObject currentNews=newsar.getJSONObject(i);
                JSONObject source=currentNews.getJSONObject("source");
                String name=source.getString("name");
                String title=currentNews.getString("title");
                String uri=currentNews.getString("url");
                String imageUri=currentNews.getString("urlToImage");
                String desc=currentNews.getString("description");
                String date=currentNews.getString("publishedAt");
                String content=currentNews.getString("content");
                Log.i("extract: ",name);
                news.add(new News(name,title,uri,imageUri,desc,date,content));
            }



        return news;
    }





}









