package com.java.huhaoran.note;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "browseHistory")
public class BrowseHistoryNote {
    @PrimaryKey(autoGenerate = true)
    public int id;
}
