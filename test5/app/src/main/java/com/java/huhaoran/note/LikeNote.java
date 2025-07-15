package com.java.huhaoran.note;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//对于点赞，只要能够知道某条新闻是否点过赞即可，不需要记录点赞新闻的具体内容
@Entity(tableName = "likeNote")
public class LikeNote {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    public LikeNote(String title)
    {
        this.title = title;
    }
}
