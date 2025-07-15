package com.java.huhaoran.note;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "browseHistory")
public class BrowseHistoryNote {


    //注意这里将主键设置为title，并且将插入规则设置为替代，这样数据库中智慧留存一条最近的记录，不会出现多次浏览出现多条记录
    @PrimaryKey
    @NonNull
    public String title;
    @ColumnInfo(name = "url")
    public String publishTime;
    @ColumnInfo(name = "content")
    public String content;
    @ColumnInfo(name = "publisher")
    public String publisher;
    @ColumnInfo(name = "category")
    public String category;
    //注意数据库不支持列表类型，只能拼接成字符串
    @ColumnInfo(name = "image")
    public String image;
    @ColumnInfo(name = "video")
    public String video;
    @ColumnInfo(name = "time")
    public long time;


    public BrowseHistoryNote(String title, String publishTime, String content, String publisher, String category, String image, String video, long time) {
        this.title = new String(title);
        this.publishTime = new String(publishTime);
        this.content = new String(content);
        this.publisher = new String(publisher);
        this.category = new String(category);
        this.image = new String(image);
        this.video = new String(video);
        this.time = time;
    }

    public String getTitle() {
        return title;
    }
}
