package com.java.huhaoran;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//使用一个类来封装主题栏相关操作
public class TabPreference {

    //用到的文件名和key名，方便操作
    private final String PREF_NAME = "tab_pref";
    private final String KEY_TITLES = "tab_key";
    private final String KEY_TITLES_NO_USE = "tab_key_no_use";

    //创建gson对象和sharedPreferences对象
    private final Gson gson;
    private final SharedPreferences sp;

    //构造函数
    public TabPreference(Context context) {
        gson = new Gson();
        sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    //储存标题栏
    public void saveTabs(List<String> titles, List<String> titlesNoUse) {
        SharedPreferences.Editor editor = sp.edit();
        //将标题栏的列表转化为json字符串保存
        editor.putString(KEY_TITLES, gson.toJson(titles));
        //将未使用的标题栏的列表转化为json字符串保存
        editor.putString(KEY_TITLES_NO_USE, gson.toJson(titlesNoUse));
        editor.apply();
    }

    //默认标题栏
    public List<String> getDefaultTitles() {
        List<String> titles = new ArrayList<String>();
        Collections.addAll(titles, "全部", "文化", "娱乐", "军事", "教育", "健康", "财经", "体育", "汽车", "科技", "社会");
        return titles;
    }

    //获取标题栏
    public List<String> loadTitles() {
        String titles = sp.getString(KEY_TITLES, null);
        if(titles != null) {
            return gson.fromJson(titles, ArrayList.class);
        }
        else {
            //如果为空，说明是第一次使用，则返回默认标题栏
            return getDefaultTitles();
        }

    }

    //获取未使用的标题
    public List<String> loadTitlesNoUse() {
        String titles = sp.getString(KEY_TITLES_NO_USE, null);
        if(titles != null) {
            return gson.fromJson(titles, ArrayList.class);
        }
        else {
            //如果为空，说明是第一次使用，则返回空（没有没使用的）
            return new ArrayList<String>();
        }
    }

    //清空
    public void clear() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
