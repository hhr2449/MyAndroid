package com.java.testglm;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.ChatCompletionRequest;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import com.zhipu.oapi.service.v4.model.ChatMessageRole;
import com.zhipu.oapi.service.v4.model.ModelApiResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.java.testglm.R;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "7e9dbe5b59904cc396ae3dc9c3a17202.bRiYAY52gnozmJ09";
    private static final String requestIdTemplate = "thu-%d";

    private static final ClientV4 client = new ClientV4.Builder(API_KEY).build();

    /**
     * Synchronous Call
     */
    private static String testInvoke() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build();

        JsonObject json = new JsonObject();
        json.addProperty("model", "glm-4-plus");  // 这里用更强的模型，可以根据需要换

        JsonArray messages = new JsonArray();

        // 用户告诉模型任务
        JsonObject systemMessage = new JsonObject();
        systemMessage.addProperty("role", "user");
        systemMessage.addProperty("content", "请帮我总结以下新闻内容，提炼重点和关键信息");
        messages.add(systemMessage);

        // 新闻内容，换成你真实新闻文本
        JsonObject newsMessage = new JsonObject();
        newsMessage.addProperty("role", "user");
        newsMessage.addProperty("content", "【新闻标题】：全球气候变化峰会召开\n" +
                "【新闻内容】：全球各国领导人近日齐聚一堂，讨论应对气候变化的紧迫措施。专家指出，气候变暖带来的极端天气频发，严重影响农业生产和人类生活。会议强调加速清洁能源发展和碳排放减少的必要性。各国承诺将在未来十年内大幅提升环保投入，共同守护地球家园。");
        messages.add(newsMessage);

        // 明确让模型总结的提示
        JsonObject promptMessage = new JsonObject();
        promptMessage.addProperty("role", "user");
        promptMessage.addProperty("content", "请用简洁的语言总结这条新闻，突出关键信息和意义。");
        messages.add(promptMessage);

        json.add("messages", messages);

        String jsonBody = json.toString();

        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("https://open.bigmodel.cn/api/paas/v4/chat/completions")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

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


    class ChatResponse {
        Choice [] choices;
        class Choice {
            Message message;
        }

        class Message {
            String content;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        TextView textView = findViewById(R.id.text);
        new Thread(() -> {
            String s = testInvoke();
            Gson gson = new Gson();
            ChatResponse response_content = gson.fromJson(s, ChatResponse.class);

            runOnUiThread(() -> {
                textView.setText(response_content.choices[0].message.content);
            });
        }).start();
    }
}



