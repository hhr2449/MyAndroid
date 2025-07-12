package com.java.huhaoran.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.java.huhaoran.note.SummaryNote;


//summary对应的Dao接口
@Dao
public abstract interface SummaryDao {
    //插入操作（返回title）
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SummaryNote note);

    default String insertAndReturnTitle(SummaryNote note) {
        insert(note);
        return note.getTitle();
    }


    //批量插入
    @Insert
    void insertAll(SummaryNote... paramVarArgs);

    //更新
    @Update
    void update(SummaryNote paramSummaryNote);

    //删除
    @Delete
    void delete(SummaryNote paramSummaryNote);

    //按照title查询
    @Query("SELECT * FROM summary WHERE title = :title")
    SummaryNote queryByTitle(String title);
}
