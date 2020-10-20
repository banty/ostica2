package com.example.ostica2.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities =Person.class, exportSchema=false, version= 1)
public abstract class PersonDatabase extends RoomDatabase {
    private static final String DB_NAME = "person_db";
    private static PersonDatabase instance;

    public  abstract PersonDao personDao();

    public static synchronized PersonDatabase getInstance(Context context) {


        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), PersonDatabase.class, DB_NAME)
                    .build();

        }

        return instance;
    }
}