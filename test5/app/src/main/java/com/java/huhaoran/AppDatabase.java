package com.java.huhaoran;
//app中的数据库

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.java.huhaoran.Dao.BrowseHistoryDao;
import com.java.huhaoran.Dao.SearchHistoryDao;
import com.java.huhaoran.Dao.SummaryDao;
import com.java.huhaoran.note.BrowseHistoryNote;
import com.java.huhaoran.note.SearchHistoryNote;
import com.java.huhaoran.note.SummaryNote;

import java.util.concurrent.Executors;

@Database(entities = {SummaryNote.class, SearchHistoryNote.class, BrowseHistoryNote.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    //用于获取实体类对应的Dao对象的方法
    public abstract SummaryDao summaryDao();
    public abstract SearchHistoryDao searchHistoryDao();
    public abstract BrowseHistoryDao browseHistoryDao();

    //单例对象
    private static volatile AppDatabase INSTANCE;



    //使用getInstance方法获取数据库类的对象
    //保证单例，需要判断是否有实例，如果有，直接返回，没有才创建
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "app_database.db"
            )
            .fallbackToDestructiveMigration()  // 强制重建数据库（数据丢失！）
            .build();
        }
        return INSTANCE;
    }



}
