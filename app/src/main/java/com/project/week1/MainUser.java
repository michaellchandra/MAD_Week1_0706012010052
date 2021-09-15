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

import Storage.StorageUser;

import static com.project.week1.MainActivity.adapter;
import static com.project.week1.MainActivity.dataStorageUser;

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

        Intent intent = getIntent();
        int cardIndex1 = intent.getIntExtra("cardIndex",0);
        int option = intent.getIntExtra("userEditAdd",0);
        if(option == 1) {
            user_button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userName = user_textInputLayout_name.getEditText().getText().toString().trim();
                    int userAge = Integer.parseInt(user_textInputLayout_age.getEditText().getText().toString().trim());
                    String userAddress = user_textInputLayout_address.getEditText().getText().toString().trim();
//                UserDetails user = new UserDetails(userName, userAge, userAddress);
                    StorageUser storageUser = new StorageUser(userName, userAge, userAddress);
//                Intent intent = new Intent();
//                intent.putExtra("listUser", user);

//                setResult(200,intent);
                    dataStorageUser.set(cardIndex1,storageUser);
                    adapter.notifyDataSetChanged();
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

        } else {
            user_button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userName = user_textInputLayout_name.getEditText().getText().toString().trim();
                    int userAge = Integer.parseInt(user_textInputLayout_age.getEditText().getText().toString().trim());
                    String userAddress = user_textInputLayout_address.getEditText().getText().toString().trim();
//                UserDetails user = new UserDetails(userName, userAge, userAddress);
                    StorageUser storageUser = new StorageUser(userName, userAge, userAddress);
//                Intent intent = new Intent();
//                intent.putExtra("listUser", user);

//                setResult(200,intent);
                    dataStorageUser.add(storageUser);
                    adapter.notifyDataSetChanged();
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


    }
    private void initView(){
        user_imageView_back = findViewById(R.id.user_imageView_back);
        user_button_save = findViewById(R.id.user_button_save);
        user_textInputLayout_name = findViewById(R.id.user_textInputLayout_name);
        user_textInputLayout_age = findViewById(R.id.user_textInputLayout_age);
        user_textInputLayout_address = findViewById(R.id.user_textInputLayout_address);


        Intent intent = getIntent();
        int cardIndex1 = intent.getIntExtra("cardIndex",0);
        int option = intent.getIntExtra("userEditAdd",0);


        if (option == 1){
            user_button_save.setText("editUser");
            String name = dataStorageUser.get(cardIndex1).getUserName();
            int age = dataStorageUser.get(cardIndex1).getUserAge();
            String address = dataStorageUser.get(cardIndex1).getUserAddress();
            user_textInputLayout_name.getEditText().setText(name);
            user_textInputLayout_age.getEditText().setText(String.valueOf(age));
            user_textInputLayout_address.getEditText().setText(address);

            ActionBar actionBar1 = getSupportActionBar();
            actionBar1.setTitle("editUser");

        }




    }
}