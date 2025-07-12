package com.java.huhaoran;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SummarizeByGLM {
    private static final String API_KEY = "7e9dbe5b59904cc396ae3dc9c3a17202.bRiYAY52gnozmJ09";

    public static String summarize(String newsTitle, String newsContent) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build();


        //构建请求体
        JsonObject requestBody = new JsonObject();

        //加上模型信息
        requestBody.addProperty("model", "glm-4");

        //任务信息用一个列表储存
        JsonArray messages = new JsonArray();

        //添加任务目标
        JsonObject systemJson = new JsonObject();
        systemJson.addProperty("role", "user");
        systemJson.addProperty("system", "你是一个新闻摘要助手，请总结新闻重点");
        messages.add(systemJson);

        //添加新闻内容
        JsonObject newsMessage = new JsonObject();
        newsMessage.addProperty("role", "user");
        String content = "以下是**新闻内容**：\n  **【新闻标题】**：" + newsTitle +
                "\n  **【新闻内容】**：" + newsContent;
        newsMessage.addProperty("content", content);
        messages.add(newsMessage);

        //再次明确任务
        JsonObject promptMessage = new JsonObject();
        promptMessage.addProperty("role", "user");
        promptMessage.addProperty("content", "请用简洁的语言总结这条新闻，突出关键信息和意义，要求总结的内容以“【新闻简报】：”开头");
        messages.add(promptMessage);

        //将信息加入
        requestBody.add("messages", messages);
        //得到请求体的字符串
        String jsonBody = requestBody.toString();
        //构建请求
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("https://open.bigmodel.cn/api/paas/v4/chat/completions")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        //返回请求的结果
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return "Error: " + response.code() + " " + response.message();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }


    }
}
