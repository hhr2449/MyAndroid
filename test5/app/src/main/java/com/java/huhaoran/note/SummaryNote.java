package com.java.huhaoran.note;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//用于保存ai生成的summary的表单
@Entity(tableName = "summary")
public class SummaryNote {
    @PrimaryKey
    @NonNull
    private String title;
    @ColumnInfo(name = "summary_content")
    private String summary_content;

    //构造函数
    public SummaryNote(String title, String summary_content) {
        this.title = title;
        this.summary_content = summary_content;
    }
    //getter和setter
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSummary_content() {
        return summary_content;
    }
    public void setSummary_content(String summary_content) {
        this.summary_content = summary_content;
    }
}
