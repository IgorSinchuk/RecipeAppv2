package com.nonexistentware.recipeappv2.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nonexistentware.recipeappv2.Interface.RecentDao;

import static com.nonexistentware.recipeappv2.Database.LocalDataBase.DATABASE_VERSION;

@Database(entities = Recent.class, version = DATABASE_VERSION)
public abstract class LocalDataBase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "recipes-f3ae6";

    public abstract RecentDao recentDao();

    private static LocalDataBase instance;

    public static LocalDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, LocalDataBase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
