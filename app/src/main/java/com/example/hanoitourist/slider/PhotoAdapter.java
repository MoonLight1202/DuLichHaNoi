package com.example.hanoitourist.slider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class PhotoAdapter extends FragmentStateAdapter {
    private List<Photo> photoList;

    public PhotoAdapter(@NonNull FragmentActivity fragmentActivity, List<Photo> list) {
        super(fragmentActivity);
        this.photoList = list;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Photo photo = photoList.get( position );

        Bundle bundle = new Bundle();
        bundle.putSerializable( "object photo",photo );

        PhotoFragment photoFragment = new PhotoFragment();
        photoFragment.setArguments( bundle );

        return photoFragment;
    }

    @Override
    public int getItemCount() {
        if(photoList != null){
            return photoList.size();
        }
        return 0;
    }
}
