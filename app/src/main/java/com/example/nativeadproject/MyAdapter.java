package com.example.nativeadproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int IS_AD = 0;
    private static final int NOT_Ad = 1;

    private final ArrayList<Object> objects = new ArrayList<>();

    public void setList(List<ItemClass> list){
        this.objects.addAll(list);
    }

    public void setAd(List<NativeAd> nativeAd){
        this.objects.addAll(nativeAd);
        notifyDataSetChanged();
    }

    public void setObject (ArrayList<Object> object){
        this.objects.clear();
        this.objects.addAll(object);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == IS_AD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_ad_card_view,parent,false);
            return new AdViewHolder(view);
        }else{
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item_card_view,parent,false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position)==IS_AD){
            AdViewHolder adv =  (AdViewHolder) holder;
            adv.setNativeAd((NativeAd) objects.get(position));
        }else{
            ItemClass itemClass = (ItemClass) objects.get(position);
            ItemViewHolder ivh = (ItemViewHolder) holder;

            ivh.imageView.setImageResource(itemClass.getImg());
            ivh.name.setText(itemClass.getName());
            ivh.email.setText(itemClass.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
       if(objects.get(position) instanceof NativeAd){
           return IS_AD;
       }else{
           return NOT_Ad;
       }
    }
}
