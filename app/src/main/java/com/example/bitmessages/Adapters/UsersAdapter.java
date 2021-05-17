package com.example.bitmessages.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bitmessages.Activities.ChatActivity;
import com.example.bitmessages.Models.User;
import com.example.bitmessages.R;
import com.example.bitmessages.databinding.RowConversationBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    Context ctx;
    ArrayList<User> users;
        public UsersAdapter(Context ctx, ArrayList<User> users) {
        this.ctx = ctx;
        this.users = users;
    }
    @NonNull
    @NotNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.row_conversation, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UsersAdapter.UsersViewHolder holder, int position) {
        User user = users.get(position);
        holder.binding.tvUsernameSample.setText(user.getName());

        Glide.with(ctx).load(user.getProfilePic()).placeholder(R.drawable.avatar).into(holder.binding.ivProfilePicInChat);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, ChatActivity.class);
                intent.putExtra("name", user.getName());
                intent.putExtra("uid", user.getUid());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public  class  UsersViewHolder extends RecyclerView.ViewHolder {
        RowConversationBinding binding;
        public UsersViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding = RowConversationBinding.bind(itemView);
        }
    }
}
