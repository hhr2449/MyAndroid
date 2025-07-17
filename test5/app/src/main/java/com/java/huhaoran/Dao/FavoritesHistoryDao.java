package com.java.huhaoran.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.java.huhaoran.note.BrowseHistoryNote;
import com.java.huhaoran.note.FavoritesHistoryNote;

import java.util.List;

@Dao
public interface FavoritesHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoritesHistoryNote note);
    default String insertAndReturnTitle(FavoritesHistoryNote note) {
        insert(note);
        return note.getTitle();
    }

    //更新
    @Update
    void update(FavoritesHistoryNote paramFavoritesHistoryNote);

    //删除
    @Delete
    void delete(FavoritesHistoryNote paramFavoritesHistoryNote);

    //删除特定title的记录
    @Query("DELETE FROM favoritesHistory WHERE title = :title AND userName = :userName")
    void deleteByTitle(String title, String userName);

    //批量删除
    @Query("DELETE FROM favoritesHistory WHERE title IN (:titles) AND userName = :userName")
    void deleteByTitles(List<String> titles, String userName);

    @Query("DELETE FROM favoritesHistory WHERE userName = :userName")
    void deleteAll(String userName);


    //按照title查询
    @Query("SELECT * FROM favoritesHistory WHERE title = :title AND userName = :userName")
    FavoritesHistoryNote queryByTitle(String title, String userName);

    //判断某个title是否存在
    @Query("SELECT EXISTS(SELECT 1 FROM favoritesHistory WHERE title = :title AND userName = :userName)")
    boolean existsByTitle(String title, String userName);

    @Query("SELECT * FROM favoritesHistory WHERE userName = :userName ORDER BY time DESC LIMIT :limit OFFSET :offset")
    List<FavoritesHistoryNote> getFavoritesHistoryPage(int limit, int offset, String userName);

    @Query("SELECT title FROM favoritesHistory WHERE userName = :userName")
    List<String> getAllFavoredTitles(String userName);
}
