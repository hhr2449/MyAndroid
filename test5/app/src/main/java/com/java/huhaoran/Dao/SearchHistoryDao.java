package com.java.huhaoran.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.java.huhaoran.note.SearchHistoryNote;

import java.util.List;

public interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SearchHistoryNote note);

    @Delete
    void delete(SearchHistoryNote note);

    //按照时间戳进行排序，同时实现分页，每页限制为limit条，offset为偏移量
    @Query("SELECT * FROM searchHistory ORDER BY time DESC LIMIT :limit OFFSET :offset")
    List<SearchHistoryNote> getSearchHistoryPage(int limit, int offset);

    @Query("DELETE FROM searchHistory")
    void clearAll();
}
