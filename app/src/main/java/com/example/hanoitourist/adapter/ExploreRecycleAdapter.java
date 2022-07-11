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

public class ExploreRecycleAdapter extends RecyclerView.Adapter<ExploreRecycleAdapter.LocationHolder> {

private ArrayList<Places> list;
Context context;
LayoutInflater inflater;

    public ExploreRecycleAdapter(ArrayList<Places> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public ExploreRecycleAdapter(ArrayList<Places> list, Context context,int allCities) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(parent.getContext()).inflate(R.layout.places_elements,parent,false);
        LocationHolder myHolder=new LocationHolder(myView);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {
        holder.locationTitle.setText(list.get(position).getName());
        Picasso.get().load( list.get( position ).getAvt() ).into( holder.locationImage );
        //holder.viewPager2.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText( view.getContext(),"hello",Toast.LENGTH_SHORT ).show();
//            }
//        } );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder {
        //ViewPager2 viewPager2;
        TextView locationTitle;
        ImageView locationImage;
        public LocationHolder(@NonNull View itemView)
        {
            super(itemView);
            //viewPager2 = itemView.findViewById( R.id.view_pager2 );
            locationTitle=(TextView) itemView.findViewById( R.id.place_title);
            locationImage=(ImageView) itemView.findViewById(R.id.place_img);
        }
    }
}
