package com.java.huhaoran.note;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "searchHistory")
public class SearchHistoryNote {
    //主键
    @PrimaryKey(autoGenerate = true)
    public int id;

    //搜索记录数据
    @ColumnInfo(name = "keyword")
    public String keyword;
    @ColumnInfo(name = "categories")
    public String categories;
    @ColumnInfo(name = "startDate")
    public String startDate;
    @ColumnInfo(name = "ednDate")
    public String endDate;

    //时间戳，是某个固定时间点到获取时间戳的时间间隔，可以用于查询，获取时间更近的搜索记录
    @ColumnInfo(name = "time")
    public long time;



}
