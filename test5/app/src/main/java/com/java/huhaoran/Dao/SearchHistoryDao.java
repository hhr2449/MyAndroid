package com.java.huhaoran.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.java.huhaoran.note.SearchHistoryNote;

import java.util.List;

@Dao
public interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SearchHistoryNote note);

    @Delete
    void delete(SearchHistoryNote note);

    //按照时间戳进行排序，同时限制每次只获得limit条，offset表示跳过offset条数据
    @Query("SELECT * FROM searchHistory ORDER BY time DESC LIMIT :limit OFFSET :offset")
    List<SearchHistoryNote> getSearchHistoryPage(int limit, int offset);

    @Query("DELETE FROM searchHistory")
    void clearAll();
}
