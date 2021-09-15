package com.project.week1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import Storage.User;

public class MainDetails extends AppCompatActivity {

    private ImageView detail_imageView_back, detail_imageView_profile, detail_imageView_delete;
    private TextView detail_textView_name, detail_textView_age, detail_textView_address;
    private String userName, userAge, userAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        detail_imageView_delete = findViewById(R.id.detail_imageView_delete);
        detail_textView_name = findViewById(R.id.detail_textView_name);
        detail_textView_age = findViewById(R.id.details_textView_age);
        detail_textView_address = findViewById(R.id.details_textView_address);
        detail_imageView_back = findViewById(R.id.detail_imageView_back);
        detail_imageView_profile = findViewById(R.id.detail_imageView_profile);

        Intent intent = new Intent();
        intent = getIntent();

        User user = intent.getParcelableExtra("listUser");
        detail_textView_name.setText(user.getUserName());
        detail_textView_age.setText(user.getUserAge());
        detail_textView_address.setText(user.getUserAddress());


        detail_imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        detail_imageView_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = getIntent();
//                User
//            }
//        });

    }
}