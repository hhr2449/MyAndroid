package com.java.huhaoran.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.java.huhaoran.note.BrowseHistoryNote;
import com.java.huhaoran.note.SearchHistoryNote;
import com.java.huhaoran.note.SummaryNote;

import java.util.List;

@Dao
public interface BrowseHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BrowseHistoryNote note);
    default String insertAndReturnTitle(BrowseHistoryNote note) {
        insert(note);
        return note.getTitle();
    }

    //更新
    @Update
    void update(BrowseHistoryNote paramBrowseHistoryNote);

    //删除
    @Delete
    void delete(BrowseHistoryNote paramBrowseHistoryNote);

    //批量删除
    @Query("DELETE FROM browseHistory WHERE title IN (:titles) AND userName = :userName")
    void deleteByTitles(List<String> titles, String userName);

    @Query("DELETE FROM browseHistory WHERE userName = :userName")
    void deleteAll(String userName);


    //按照title查询
    @Query("SELECT * FROM browseHistory WHERE title = :title AND userName = :userName")
    BrowseHistoryNote queryByTitle(String title, String userName);

    //判断某个title是否存在
    @Query("SELECT EXISTS(SELECT 1 FROM browseHistory WHERE title = :title AND userName = :userName)")
    boolean existsByTitle(String title, String userName);

    @Query("SELECT * FROM browseHistory WHERE userName = :userName ORDER BY time DESC LIMIT :limit OFFSET :offset")
    List<BrowseHistoryNote> getBrowseHistoryPage(int limit, int offset, String userName);

    @Query("SELECT title FROM browseHistory WHERE userName = :userName")
    List<String> getAllReadTitles(String userName);

}
