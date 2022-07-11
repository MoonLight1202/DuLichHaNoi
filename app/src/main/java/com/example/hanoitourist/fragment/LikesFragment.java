package com.example.hanoitourist.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanoitourist.MainActivity;
import com.example.hanoitourist.model.Places;
import com.example.hanoitourist.viewmodel.PlacesVM;
import com.example.hanoitourist.R;
import com.example.hanoitourist.model.User_Fav;
import com.example.hanoitourist.viewmodel.User_fav_VM;
import com.example.hanoitourist.adapter.LikeRecycleAdapter;

import java.util.ArrayList;


public class LikesFragment extends Fragment {
    RecyclerView LikeRecyclerView;
    ArrayList<Places> likeArrayList;
    ArrayList<Places> PlaceArrayList;
    public static ArrayList<User_Fav> user_favArrayList;
    public static PlacesVM placesVM ;
    public static User_fav_VM user_fav_vm;
    public static View view;
    public static LikeRecycleAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_likes, container, false );
        user_favArrayList = new ArrayList<>();
        likeArrayList = new ArrayList();
        PlaceArrayList = new ArrayList();
        user_favArrayList.clear();
        PlaceArrayList.clear();
        likeArrayList.clear();

        placesVM = new PlacesVM();
        user_fav_vm = new User_fav_VM();

        LikeRecyclerView = view.findViewById( R.id.recyclerView_likeFragment );
        PlaceArrayList = placesVM.getALl();
        user_favArrayList = user_fav_vm.getALl();
        Log.d("Hello",MainActivity.id_user+"");
        Log.d("UserfvList_size",user_favArrayList.size()+"");
        Log.d("PlaceList_size",PlaceArrayList.size()+"");
        for (int i = 0; i < user_favArrayList.size(); i++) {
            if (user_favArrayList.get( i ).getId_user() == MainActivity.id_user && user_favArrayList.get( i ).getIs_like() == 1) {
                likeArrayList.add( PlaceArrayList.get( i ) );
            }
            Log.d("user_favArrayList",user_favArrayList.get( i ).getIs_like()+"");
        }
            adapter = new LikeRecycleAdapter( likeArrayList, view.getContext(), 1 );
            GridLayoutManager gridLayoutManager = new GridLayoutManager( getContext(), 1, GridLayoutManager.VERTICAL, false );
            LikeRecyclerView.setLayoutManager( gridLayoutManager );
            LikeRecyclerView.setAdapter( adapter );
        return view;
    }

}