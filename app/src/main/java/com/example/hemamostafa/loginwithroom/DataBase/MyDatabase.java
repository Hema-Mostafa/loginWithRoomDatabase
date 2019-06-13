package com.example.hemamostafa.loginwithroom.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hemamostafa.loginwithroom.Daos.UserTableDao;
import com.example.hemamostafa.loginwithroom.Model.UserTable;

@Database(entities = {UserTable.class},version = 1,exportSchema= false)

public abstract class  MyDatabase extends RoomDatabase {

   private  static MyDatabase myDatabase;

   public  abstract UserTableDao userTableDao();
    public static MyDatabase getInstance (Context context) {

        if (myDatabase== null)
            myDatabase= Room.databaseBuilder(context,
                    MyDatabase.class, "user-database")
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceB    asicSample for an example.
                    .allowMainThreadQueries()
                    .build();
        return myDatabase;



    }

}
