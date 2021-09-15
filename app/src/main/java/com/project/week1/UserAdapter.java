package com.project.week1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import Storage.User;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> listUser;

    public UserAdapter(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull UserAdapter.UserViewHolder holder, int position) {
        holder.card_textView_name.setText(listUser.get(position).getUserName());
        holder.card_textView_age.setText(listUser.get(position).getUserAge());
        holder.card_textView_address.setText(listUser.get(position).getUserAddress());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView card_textView_name, card_textView_age, card_textView_address;
        public UserViewHolder(View view) {
            super(view);
            card_textView_name = itemView.findViewById(R.id.card_textView_name);
            card_textView_age = itemView.findViewById(R.id.card_textView_age);
            card_textView_address = itemView.findViewById(R.id.card_textView_address);


        }
    }
}
