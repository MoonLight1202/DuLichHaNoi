package com.example.hanoitourist.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.hanoitourist.activities.MainActivity;
import com.example.hanoitourist.R;
import com.example.hanoitourist.model.User;
import com.example.hanoitourist.viewmodel.UserVM;
import com.example.hanoitourist.activities.ProfileEdit;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment{
    ImageView editProfile;
    TextView txtUserName,txtUserPhone,txtUserGender,txtUserAge;
    CircleImageView circleImageView;
    View view;
    UserVM userVM = new UserVM();
    ArrayList<User> userArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate( R.layout.activity_profile, container, false);
        editProfile = view.findViewById(R.id.edit_profile);
        txtUserName = view.findViewById( R.id.user_name );
        txtUserPhone = view.findViewById( R.id.user_phoneNum );
        txtUserGender = view.findViewById( R.id.user_gender );
        txtUserAge = view.findViewById( R.id.user_age );
        circleImageView = view.findViewById( R.id.imgUser_Profile );
        userArrayList = userVM.getALl();
        for (int i = 0; i < userArrayList.size(); i++) {
            if(userArrayList.get( i ).getId() == MainActivity.id_user){
                Log.d("AAA",userArrayList.get( i ).getName());
                txtUserName.setText(userArrayList.get( i ).getName());
                txtUserPhone.setText(userArrayList.get( i ).getPhoneNumOrGmail());
                txtUserGender.setText("Giới tính: "+userArrayList.get( i ).getGender());
                txtUserAge.setText("Tuổi: "+userArrayList.get( i ).getAge());
                if(userArrayList.get( i ).getAvt() != null){
                    circleImageView.setImageBitmap( getImage( userArrayList.get( i ).getAvt() ) );
                }
            }
        }
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), ProfileEdit.class));
            }
        });
        return view;
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    @Override
    public void onResume() {
        super.onResume();
        userArrayList = userVM.getALl();
        for (int i = 0; i < userArrayList.size(); i++) {
            if(userArrayList.get( i ).getId() == MainActivity.id_user){
                Log.d("AAA",userArrayList.get( i ).getName());
                txtUserName.setText(userArrayList.get( i ).getName());
                txtUserPhone.setText(userArrayList.get( i ).getPhoneNumOrGmail());
                txtUserGender.setText("Giới tính: "+userArrayList.get( i ).getGender());
                txtUserAge.setText("Tuổi: "+userArrayList.get( i ).getAge());
                if(userArrayList.get( i ).getAvt() != null){
                    circleImageView.setImageBitmap( getImage( userArrayList.get( i ).getAvt() ) );
                }
            }
        }
    }
}