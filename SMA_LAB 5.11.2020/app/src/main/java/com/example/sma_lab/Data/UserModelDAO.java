package com.example.sma_lab.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.sma_lab.Model.UserModel;

import java.util.List;

@Dao
public interface UserModelDAO {
    @Query("SELECT * FROM UserModel WHERE username=:username AND password=:password")
    UserModel getUser(String username, String password);

    @Query("UPDATE UserModel SET username=:username WHERE email=:email")
    void updateUsername(String username,String email);

    @Query("SELECT * FROM UserModel")
    List<UserModel> getAll();

    @Query("SELECT * FROM UserModel WHERE id IN (:userIds)")
    List<UserModel> loadAllByIds(int[] userIds);



    @Insert
    void insert(UserModel userModel);
}
