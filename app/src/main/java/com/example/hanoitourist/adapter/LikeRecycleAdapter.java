package com.example.hanoitourist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanoitourist.model.Places;
import com.example.hanoitourist.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LikeRecycleAdapter extends RecyclerView.Adapter<LikeRecycleAdapter.LocationHolder>{
    private ArrayList<Places> list;
    Context context;
    LayoutInflater inflater;
    public LikeRecycleAdapter(ArrayList<Places> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public LikeRecycleAdapter(ArrayList<Places> list, Context context, int allCities) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(parent.getContext()).inflate(R.layout.line_fav,parent,false);
        LikeRecycleAdapter.LocationHolder myHolder=new LikeRecycleAdapter.LocationHolder(myView);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {
        holder.likedTitle.setText(list.get(position).getName());
        Picasso.get().load( list.get( position ).getAvt() ).into( holder.likedImage );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder {
        TextView likedTitle;
        ImageView likedImage;
        public LocationHolder(@NonNull View itemView)
        {
            super(itemView);
            likedTitle=(TextView) itemView.findViewById( R.id.name_favPlace);
            likedImage= (ImageView) itemView.findViewById( R.id.img_placeLike );
        }
    }
}
