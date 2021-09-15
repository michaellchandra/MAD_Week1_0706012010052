package com.project.week1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import Storage.StorageUser;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<StorageUser> listStorageUser;

    public UserAdapter(ArrayList<StorageUser> listStorageUser) {
        this.listStorageUser = listStorageUser;
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
        holder.card_textView_name.setText(listStorageUser.get(position).getUserName());
        holder.card_textView_age.setText(String.valueOf(listStorageUser.get(position).getUserAge()));
        holder.card_textView_address.setText(listStorageUser.get(position).getUserAddress());
    }

    @Override
    public int getItemCount() {
        return listStorageUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView card_textView_name, card_textView_age, card_textView_address;
        private CardView card_User;
        public UserViewHolder(View view) {
            super(view);
            card_User = itemView.findViewById(R.id.card_User);
            card_textView_name = itemView.findViewById(R.id.card_textView_name);
            card_textView_age = itemView.findViewById(R.id.card_textView_age);
            card_textView_address = itemView.findViewById(R.id.card_textView_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cardIndex = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(),MainDetails.class);
                    intent.putExtra("cardIndex", cardIndex);
                    v.getContext().startActivity(intent);
                }
            });



        }
    }
}
