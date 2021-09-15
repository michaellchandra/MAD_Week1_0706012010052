package com.project.week1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import Storage.StorageUser;

public class MainDetails extends AppCompatActivity {

    private ImageView detail_imageView_back, detail_imageView_profile, detail_imageView_delete, detail_imageView_edit;
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
        detail_imageView_edit = findViewById(R.id.detail_imageView_edit);

        Intent intent = new Intent();
        intent = getIntent();

        StorageUser storageUser = MainActivity.dataStorageUser.get(intent.getIntExtra("cardIndex",0));
        detail_textView_name.setText(storageUser.getUserName());
        detail_textView_age.setText(String.valueOf(storageUser.getUserAge()));
        detail_textView_address.setText(storageUser.getUserAddress());

        int cardIndex = intent.getIntExtra("cardIndex",0);


        detail_imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        Intent finalIntent = intent;
        detail_imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dataStorageUser.remove(cardIndex);
                MainActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });

        detail_imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getBaseContext(), MainUser.class);
                intent2.putExtra("cardIndex", cardIndex);
                intent2.putExtra("userEditAdd",1);
                startActivity(intent2);
                finish();
            }
        });

    }
}