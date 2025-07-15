package com.java.huhaoran.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.java.huhaoran.note.LikeNote;

import java.util.List;

@Dao
public interface LikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LikeNote note);

    @Delete
    void delete(LikeNote note);

    //删除特定title的记录
    @Query("DELETE FROM likeNote WHERE title = :title")
    void deleteByTitle(String title);

    //判断某个title是否存在
    @Query("SELECT EXISTS(SELECT 1 FROM likeNote WHERE title = :title)")
    boolean existsByTitle(String title);

}
