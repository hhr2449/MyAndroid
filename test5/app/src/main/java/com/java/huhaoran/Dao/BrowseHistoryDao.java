package com.java.huhaoran.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.java.huhaoran.note.BrowseHistoryNote;
import com.java.huhaoran.note.SearchHistoryNote;

import java.util.List;

@Dao
public interface BrowseHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BrowseHistoryNote note);

    //更新
    @Update
    void update(BrowseHistoryNote paramBrowseHistoryNote);

    //删除
    @Delete
    void delete(BrowseHistoryNote paramBrowseHistoryNote);

    //按照title查询
    @Query("SELECT * FROM browseHistory WHERE title = :title")
    BrowseHistoryNote queryByTitle(String title);

    //判断某个title是否存在
    @Query("SELECT EXISTS(SELECT 1 FROM browseHistory WHERE title = :title)")
    boolean existsByTitle(String title);

    @Query("SELECT * FROM browseHistory ORDER BY time DESC LIMIT :limit OFFSET :offset")
    List<BrowseHistoryNote> getBrowseHistoryPage(int limit, int offset);

}
