package com.example.hemamostafa.loginwithroom.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UserTable {
    @PrimaryKey(autoGenerate = true)
    int id ;
    @ColumnInfo
    String userName;
    @ColumnInfo
    String password;
    @ColumnInfo
    String email;

    public UserTable(){};
    public UserTable(String userName, String password, String email) {

        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
