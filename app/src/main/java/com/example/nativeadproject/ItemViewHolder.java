package com.example.nativeadproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView name;
    public TextView email;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.id_img);
        name = itemView.findViewById(R.id.id_name);
        email = itemView.findViewById(R.id.id_email);
    }
}
