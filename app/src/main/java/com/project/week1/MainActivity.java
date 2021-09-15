package com.project.week1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Storage.User;

public class MainActivity extends AppCompatActivity {

    private TextView main_textView_noData;
    private FloatingActionButton main_floatingButton_plus;
    private RecyclerView main_recyclerView;
    private ArrayList<User> dataUser;
    private UserAdapter adapter;

    boolean doubleTapExit = false;

    @Override
    public void onBackPressed(){
        if(doubleTapExit) {
            super.onBackPressed();
            return;
        }
        this.doubleTapExit = true;
        Toast.makeText(this, "Sentuh Lagi untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleTapExit = false;
            }
        }, 2000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_recyclerView = findViewById(R.id.main_recyclerView);
        main_floatingButton_plus = findViewById(R.id.main_floatingButton_plus);
        main_textView_noData = findViewById(R.id.main_textView_noData);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);
        initView();
        setupRecyclerView();

        setListener();



        Intent intent = new Intent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        User userBaru = data.getParcelableExtra("listUser");
        dataUser.add(userBaru);
        adapter.notifyDataSetChanged();
        main_textView_noData.setVisibility(View.GONE);
    }

    private void setListener (){
        main_floatingButton_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainUser.class);
                startActivity(intent);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        main_recyclerView.setLayoutManager(manager);
        main_recyclerView.setAdapter(adapter);
    }

    private void initView(){
        main_recyclerView = findViewById(R.id.main_recyclerView);
        main_textView_noData = findViewById(R.id.main_textView_noData);
        main_floatingButton_plus = findViewById(R.id.main_floatingButton_plus);
        dataUser = new ArrayList<User>();

        adapter = new UserAdapter(dataUser);


    }
}