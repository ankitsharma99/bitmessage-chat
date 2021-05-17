package com.example.bitmessages.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitmessages.Models.Message;
import com.example.bitmessages.R;
import com.example.bitmessages.databinding.ItemRecievedBinding;
import com.example.bitmessages.databinding.ItemSendBinding;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class MessagesAdapter extends RecyclerView.Adapter {
    Context ctx;
    ArrayList<Message> messages;
    final  int ITEM_SENT = 1;
    final int ITEM_RECEIVED = 2;

    public MessagesAdapter(Context ctx, ArrayList<Message> messages) {
        this.ctx = ctx;
        this.messages = messages;
    }


    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_SENT) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.item_send, parent, false);
            return new SentViewHolder(view);
        }else {
            View view = LayoutInflater.from(ctx).inflate(R.layout.item_recieved, parent, false);
            return new ReceivedViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);

        if(Objects.equals(FirebaseAuth.getInstance().getUid(), message.getSenderId())) {
            return ITEM_SENT;
        }else {
            return ITEM_RECEIVED;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if(holder.getClass() == SentViewHolder.class) {
            SentViewHolder viewHolder = (SentViewHolder) holder;
            viewHolder.binding.tvSentMessage.setText(message.getMessage());
        }else {
            ReceivedViewHolder viewHolder = (ReceivedViewHolder) holder;
            viewHolder.binding.tvRecievedMsg.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder {
        ItemSendBinding binding;
        public SentViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            binding = ItemSendBinding.bind(itemView);
        }
    }


    public class ReceivedViewHolder extends RecyclerView.ViewHolder {
        ItemRecievedBinding binding;

        public ReceivedViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding = ItemRecievedBinding.bind(itemView);
        }
    }
}
