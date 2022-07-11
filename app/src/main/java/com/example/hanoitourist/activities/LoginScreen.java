package com.example.hanoitourist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.hanoitourist.R;

public class LoginScreen extends AppCompatActivity {
    AppCompatButton btn_sign_in_otp;

    public static SharedPreferences luu_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_screen );
        btn_sign_in_otp = findViewById( R.id.sign_in_otp );
        luu_user = PreferenceManager.getDefaultSharedPreferences( this );
        int data = luu_user.getInt( "soluong",1 );
        btn_sign_in_otp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this,LoginWithOTP.class);
                startActivity( intent );
            }
        } );
    }
}