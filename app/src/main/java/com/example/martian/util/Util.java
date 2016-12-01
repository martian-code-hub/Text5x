package com.example.martian.util;

import com.example.martian.bean.News;
import com.example.martian.bean.NewsList;

import java.util.List;

/**
 * Created by yangpei on 2016/11/28.
 */

public class Util {

    public static String data(NewsList data) {
        if (data != null) {
            if (data.getStories() != null) {
                List<News> stories = data.getStories();
                StringBuffer sb = new StringBuffer();
                for (News news : stories) {
                    sb.append(news.getId()).append("  ").append(news.getType()).append("   ").append(news.getTitle()).append("\n");
                }
                return sb.toString();
            }
        }
        return "";
    }
}
