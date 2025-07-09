package com.java.huhaoran;
import android.util.Log;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
            // 如果没有指定结束日期，则使用当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = sdf.format(new Date());
            url += "&endDate=" + currentDateTime;
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
                .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 9.0; Mobile; rv:68.0) Gecko/68.0 Firefox/68.0")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Referer", "https://www.inewsweek.cn/")
                .addHeader("Origin", "https://www.inewsweek.cn")
                .addHeader("Connection", "keep-alive")
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

    //获取图片链接,因为直接爬取得到的图片链接可能有中括号和,分割，所以需要处理
    //换了一个更加健壮的处理方式，因为后端接口的数据格式混乱
    public static String[] getLinks(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new String[0];
        }

        input = input.trim();

        // 如果是 [xxx, yyy] 形式，先去掉中括号
        if (input.startsWith("[") && input.endsWith("]")) {
            input = input.substring(1, input.length() - 1).trim();
        }

        // 用逗号分隔
        String[] rawParts = input.split(",\\s*");
        List<String> validLinks = new ArrayList<>();

        for (String part : rawParts) {
            part = part.trim();
            // 过滤空项、空字符串、null 字符
            if (!part.isEmpty() && !part.equalsIgnoreCase("null")) {
                validLinks.add(part);
            }
        }

        return validLinks.toArray(new String[0]);
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