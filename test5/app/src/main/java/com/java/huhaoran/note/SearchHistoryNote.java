package com.java.huhaoran.note;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "searchHistory")
public class SearchHistoryNote {
    //主键
    @PrimaryKey(autoGenerate = true)
    public int id;

    //搜索记录数据
    public String keyword;
    public String categories;
    public String startDate;
    public String endDate;

    //时间戳，是某个固定时间点到获取时间戳的时间间隔，可以用于查询，获取时间更近的搜索记录
    public long time;



}
