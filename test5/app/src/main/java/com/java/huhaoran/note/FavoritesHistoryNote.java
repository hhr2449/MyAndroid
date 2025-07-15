package com.java.huhaoran.note;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//用于记录收藏过的新闻
//其实和浏览历史的表单结构一样，只是为了方便区分
@Entity(tableName = "favoritesHistory")
public class FavoritesHistoryNote {
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
    //注意数据库不支持列表类型，只能拼接成字符串
    @ColumnInfo(name = "image")
    public String image;
    @ColumnInfo(name = "video")
    public String video;
    @ColumnInfo(name = "time")
    public long time;

    public FavoritesHistoryNote(String title, String publishTime, String content, String publisher, String image, String video, long time) {
        this.title = new String(title);
        this.publishTime = new String(publishTime);
        this.content = new String(content);
        this.publisher = new String(publisher);
        this.image = new String(image);
        this.video = new String(video);
        this.time = time;
    }
}
