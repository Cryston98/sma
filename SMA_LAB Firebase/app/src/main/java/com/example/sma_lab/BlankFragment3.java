package com.example.sma_lab;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.sma_lab.Data.UserDatabase;
import com.example.sma_lab.Data.UserModelDAO;
import com.example.sma_lab.Model.UserAdapter;
import com.example.sma_lab.Model.UserModel;

import java.util.List;


public class BlankFragment3 extends Fragment {


    public BlankFragment3() {
        // Required empty public constructor
    }


    RecyclerView recyclerView;
    List<UserModel> itemList;
    UserModelDAO db;
    UserDatabase database;
    private List<UserModel> userModelList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = Room.databaseBuilder(getContext(), UserDatabase.class, "UserModel")
                .allowMainThreadQueries()
                .build();
        db = database.getUserDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank3, container, false);

        recyclerView=view.findViewById(R.id.RecyclerUsers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //initData();
        recyclerView.setAdapter(new UserAdapter(initData(),getContext()));
        return view;
    }

    private List<UserModel> initData() {
        userModelList = db.getAll();
        return userModelList;
    }
}