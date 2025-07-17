package com.java.huhaoran.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.java.huhaoran.note.UserNote;

@Dao
public interface UserDao {
    @Insert
    void insert(UserNote user);
    @Query("SELECT * FROM user WHERE userName = :userName")
    UserNote queryByUserName(String userName);
    @Query("SELECT EXISTS(SELECT 1 FROM user WHERE userName = :userName)")
    boolean existsByUserName(String userName);
    @Query("DELETE FROM user WHERE userName = :userName")
    void deleteByUserName(String userName);

}
