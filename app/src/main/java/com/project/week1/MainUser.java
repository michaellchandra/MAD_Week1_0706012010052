package com.project.week1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import Storage.User;
import Storage.UserDetails;

public class MainUser extends AppCompatActivity {

    private TextInputLayout user_textInputLayout_name, user_textInputLayout_age, user_textInputLayout_address;
    private Button user_button_save;
    private ImageView user_imageView_back;
//    private Boolean validateUserName, validateUserAge, validateUserAddress;
    Loading loading = new Loading (MainUser.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main_user);
        initView();
        setListener();

        user_textInputLayout_name.getEditText().addTextChangedListener(textWatch);
        user_textInputLayout_age.getEditText().addTextChangedListener(textWatch);
        user_textInputLayout_address.getEditText().addTextChangedListener(textWatch);

        user_imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private TextWatcher textWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String userName = user_textInputLayout_name.getEditText().getText().toString().trim();
            String userAge = user_textInputLayout_age.getEditText().getText().toString().trim();
            String userAddress = user_textInputLayout_address.getEditText().getText().toString().trim();

            if(!userName.isEmpty() && userName.length()< 26) {
                if(!userAge.isEmpty() && !userAddress.isEmpty()) {
                    user_button_save.setEnabled(true);
                } else {
                    user_button_save.setEnabled(false);
                }
            } else {
                user_textInputLayout_name.setError("Maksimal 25 Karakter");
                user_button_save.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };




    private void setListener() {
        user_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = user_textInputLayout_name.getEditText().getText().toString().trim();
                int userAge = Integer.parseInt(user_textInputLayout_age.getEditText().getText().toString().trim());
                String userAddress = user_textInputLayout_address.getEditText().getText().toString().trim();
                UserDetails temp = new UserDetails(userName, userAge, userAddress);
                User user = new User(userName, userAge, userAddress);
                Intent intent = new Intent();
                intent.putExtra("listUser", user);


                setResult(200,intent);
                loading.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismissDialog();
                        finish();
                    }
                }, 1000);
                Toast.makeText(MainUser.this, "User telah terdaftar!", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private void initView(){
        user_imageView_back = findViewById(R.id.user_imageView_back);
        user_button_save = findViewById(R.id.user_button_save);
        user_textInputLayout_name = findViewById(R.id.user_textInputLayout_name);
        user_textInputLayout_age = findViewById(R.id.user_textInputLayout_age);
        user_textInputLayout_address = findViewById(R.id.user_textInputLayout_address);
    }
}