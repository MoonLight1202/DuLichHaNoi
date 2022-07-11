package com.example.hanoitourist.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hanoitourist.MainActivity;
import com.example.hanoitourist.R;
import com.example.hanoitourist.model.User;
import com.example.hanoitourist.viewmodel.UserVM;
import com.example.hanoitourist.database.Database;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEdit extends AppCompatActivity {
    public CircleImageView circleImageView;
    public Button saveBtn;
    public EditText editUserName,editContact,editGender,editAge;
    public String username,contact,gender,age;
    public byte[] avt;
    private ImageView btnBack;
    int tuoi;
    Database database;
    UserVM userVM = new UserVM();
    ArrayList<User> userArrayList;
    public int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_edit );
        bindViewId();
        btnBack = findViewById( R.id.btnBack_profileEdit );

        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileEdit.this.finish();
            }
        } );
        userArrayList = userVM.getALl();
        for (int i = 0; i < userArrayList.size(); i++) {
            if(userArrayList.get( i ).getId() == MainActivity.id_user){
                id = i;
                editUserName.setText(userArrayList.get( i ).getName());
                editContact.setText(userArrayList.get( i ).getPhoneNumOrGmail());
                editContact.setEnabled( false );
                editGender.setText(userArrayList.get( i ).getGender());
                editAge.setText(String.valueOf(userArrayList.get( i ).getAge()));
                if(userArrayList.get( i ).getAvt() != null){
                    circleImageView.setImageBitmap( getImage( userArrayList.get( i ).getAvt() ) );
                }

            }
        }
        circleImageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions();
            }
        } );
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditTextData();
                postData();
            }
        });


    }
    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(i);
    }
    public Bitmap selectedImageBitmap;
    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode()
                == Activity.RESULT_OK) {
            Intent data = result.getData();
            if (data != null
                    && data.getData() != null) {
                Uri selectedImageUri = data.getData();

                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap( this.getContentResolver(), selectedImageUri);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                circleImageView.setImageBitmap(selectedImageBitmap);
            }
        }
    });

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    private void requestPermissions(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                imageChooser();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(ProfileEdit.this, "Quyền truy cập bị từ chối\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("Nếu bạn từ chối quyền, bạn không thể sử dụng dịch vụ này\n\nVui lòng bật quyền tại [Setting]> [Permission]")
                .setPermissions( Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void bindViewId()
    {
        editUserName=(EditText) findViewById(R.id.edit_username);
        editContact=(EditText) findViewById(R.id.edit_contact);
        editGender=(EditText) findViewById(R.id.edit_gender);
        editAge=(EditText) findViewById(R.id.edit_age);
        saveBtn=(Button) findViewById(R.id.save_btn);
        circleImageView = (CircleImageView) findViewById( R.id.img_photoUser );
        btnBack = findViewById( R.id.btnBack_profileEdit );
    }
    private void setEditTextData()
    {
        username= editUserName.getText().toString();
        contact= editContact.getText().toString();
        gender=editGender.getText().toString();
        age=editAge.getText().toString();
        avt = userArrayList.get( id ).getAvt();
    }
    private void postData()
    {
        if(selectedImageBitmap != null){
            avt = getBytes( selectedImageBitmap );
        } else {
            avt = userArrayList.get( id ).getAvt();
        }
        tuoi = Integer.valueOf( age );
        Log.d("AAA",MainActivity.id_user+username+contact+gender+tuoi+"");
        User user = new User( MainActivity.id_user,username,contact,gender,tuoi,avt );
        if(userVM.update( user )>-1){
            Toast.makeText(ProfileEdit.this,"Update thông tin thành công",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(ProfileEdit.this,"Update lỗi",Toast.LENGTH_SHORT).show();
        }
    }
}