package com.java.huhaoran;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;


//这是一个专门用于管理和储存搜索数据的类，方便进行传递和储存,同时封装了一些用于管理数据的方法
//注意要实现Serializable接口，这样才能在页面之间传递
public class SearchData implements Serializable {
    private String keyword;
    private HashSet<String> categories;
    private String startDate;
    private String endDate;

    // 构造方法
    public SearchData() {
        // 默认构造器
        categories = new HashSet<String>();
    }

    public SearchData(@NonNull String keyword, @Nullable LinkedList<String> categories, @Nullable String startDate, @Nullable String endDate) {
        this.keyword = keyword;
        this.categories = new HashSet<String>(categories);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter 和 Setter
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = new String(keyword);
    }

    public HashSet<String> getCategories() {
        return categories;
    }

    public void setCategories(HashSet<String> categories) {
        this.categories = new HashSet<String>(categories);
    }

    public void addCategory(String category)
    {
        this.categories.add(category);
    }

    public void deleteCategory(String category)
    {
        if(categories.contains(category)) {
            this.categories.remove(category);
        }
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }



    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // 调试用输出
    @NonNull
    @Override
    public String toString() {
        return "SearchData{" +
                "keyword='" + keyword + '\'' +
                ", categories=" + categories.toString() +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
