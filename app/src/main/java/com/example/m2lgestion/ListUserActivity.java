package com.example.m2lgestion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m2lgestion.dao.UserDao;
import com.example.m2lgestion.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ListUserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
// Initialiser la liste des utilisateurs
        userList = new ArrayList<>();
        userList = UserDao.findAllUsers();
        userAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(userAdapter);

    }



}