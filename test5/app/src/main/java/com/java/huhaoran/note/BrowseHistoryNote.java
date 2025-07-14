package com.java.huhaoran.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "browseHistory")
public class BrowseHistoryNote {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "url")
    public String publishTime;
    @ColumnInfo(name = "content")
    public String content;
    @ColumnInfo(name = "publisher")
    public String publisher;
    @ColumnInfo(name = "category")
    public String category;
    @ColumnInfo(name = "image")
    public String image;
    @ColumnInfo(name = "video")
    public String video;
    @ColumnInfo(name = "time")
    public long time;


    public BrowseHistoryNote(String title, String publishTime, String content, String publisher, String category, String image, String video) {
        this.title = new String(title);
        this.publishTime = new String(publishTime);
        this.content = new String(content);
        this.publisher = new String(publisher);
        this.category = new String(category);
        this.image = new String(image);
        this.video = new String(video);
    }
}
