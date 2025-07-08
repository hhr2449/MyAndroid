package com.java.huhaoran;
import android.util.Log;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.List;


// 目标是解析新闻数据，并且返回一个NewsResponse对象，
// 注意NewsResponse是一个内部类，创建的时候应该使用FetchNews.NewsResponse来创建
public class FetchNews {
    // 接口地址模板
    private static final String BASE_URL = "https://api2.newsminer.net/svc/news/queryNewsList";
    public static NewsResponse fetchNews(String size, String startDate, String endDate, String[] words, String categories, String page) {
        String url = BASE_URL + "?size=" + size;

        if (startDate != null && !startDate.trim().isEmpty()) {
            url += "&startDate=" + startDate;
        }

        if (endDate != null && !endDate.trim().isEmpty()) {
            url += "&endDate=" + endDate;
        }
        else {
            url += "&endDate=当前时间";
        }

        url += "&words=";
        if (words != null && words.length > 0) {
            for(int i = 0; i < words.length; i++) {
                url += words[i];
                if (i < words.length - 1) {
                    url += ",";
                }
            }
        }

        if (categories != null && !categories.trim().isEmpty()) {
            url += "&categories=" + categories;
        }

        url += "&page=" + page;

        // 创建 OkHttpClient
        // OkHttpClient是用于发送请求的客户端对象
        OkHttpClient client = new OkHttpClient();

        // 构建请求
        // request是请求对象，用于描述发送的Http请求
        //使用了OkHttp提供的构建器模式，url是请求的地址，.build()方法构建出请求的对象
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            // 同步发送请求
            //response是响应对象，用于描述请求得到的响应
            //client.newCall(request) 创建一个Call类型对象，代表一次http网络请求
            //参数是请求对象，表示该网络请求的内容
            //execute() 方法发送请求并获取响应
            Response response = client.newCall(request).execute();

            //如果请求成功，那么response的body里面就包含了我们要的新闻数据，格式为json
            if (response.isSuccessful()) {
                // 获取返回的 JSON 字符串
                String json = response.body().string();

                // 打印原始 JSON 字符串
                System.out.println("原始JSON：\n" + json.substring(0, Math.min(500, json.length())) + "...\n");

                // 使用 Gson 解析
                Gson gson = new Gson();
                //接受一个json字符串，返回指定类型的对象
                //这里转换采用了字符串匹配，将json字符串的字段按照字段名与类的成员变量进行匹配
                //如果字段名和成员变量的名字一样，那么字段值就会自动赋给成员变量
                NewsResponse newsResponse = gson.fromJson(json, NewsResponse.class);
                return newsResponse;


            } else {
                System.out.println("请求失败，状态码：" + response.code());
                return null;
            }

        } catch (IOException e) {
            Log.e( "FetchNews","网络请求异常：" + e.getMessage());
        }

        return null;
    }


    // JSON 数据结构类
    //定义了用来接受json解析结果的类
    static class NewsResponse {
        //每一条新闻字段名为data，这里设置了名为data的列表，那么一条新闻数据就会成为列表中的一个元素
        List<NewsItem> data;
    }

    static class Organizations {
        String mention;
        String count;
    }


    static class KeyWord {
        String word;
        String score;
    }

    static class Location {
        String mention;
    }


    static class NewsItem {
        String title;
        String publishTime;
        List<KeyWord> keywords;
        List<Organizations> organizations;
        List<Location> locations;
        String content;
        String publisher;
        String category;
        String image;
        String video;
    }
}