package com.java.huhaoran.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserNote {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "userName")
    public String userName;
    @ColumnInfo(name = "password")
    public String password;

    public UserNote(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
