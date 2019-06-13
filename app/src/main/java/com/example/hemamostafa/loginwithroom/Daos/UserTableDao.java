package com.example.hemamostafa.loginwithroom.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.hemamostafa.loginwithroom.Model.UserTable;

import java.util.List;

@Dao
public interface UserTableDao {

@Query("select * from UserTable")
    public List<UserTable> getAllUsers();

    @Insert
    public void inserUser( UserTable userTable);
}

