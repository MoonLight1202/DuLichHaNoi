package com.example.hanoitourist.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hanoitourist.MainActivity;
import com.example.hanoitourist.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginWithOTP extends AppCompatActivity {
    AppCompatEditText edt_phone_num;
    AppCompatButton btn_request_sign_in_otp;
    FirebaseAuth mAuth;
    ImageView btnBack;
    public static final String TAG = LoginWithOTP.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_with_otp );
        FirebaseApp.initializeApp(/*context=*/ this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());
        mAuth = FirebaseAuth.getInstance();
        edt_phone_num =findViewById( R.id.edt_phone_num );
        btnBack = findViewById( R.id.btnBack_LoginOtp );
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
        btn_request_sign_in_otp = findViewById( R.id.request_btn_otp );
        btn_request_sign_in_otp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = edt_phone_num.getText().toString().trim();
                onClickSendOtp(phoneNumber);
            }
        } );
    }

    private void onClickSendOtp(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Số điện thoại để xác minh
                        .setTimeout(60L, TimeUnit.SECONDS) // Thời gian chờ và đơn vị
                        .setActivity(this)                 // Hoạt động (đối với ràng buộc gọi lại)
                        // callback xác thực sđt
                        .setCallbacks( new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                // hd trong hai tình huống:
                                // 1 - Xác minh tức thì. Trong một số trường hợp, số điện thoại có thể được xác minh ngay lập tức
                                // mà không cần gửi hoặc nhập mã xác minh.
                                // 2 - Tự động nghe. Trên một số thiết bị Dịch vụ Google Play có thể tự động
                                // Phát hiện SMS xác minh đến và thực hiện xác minh mà không cần // Hành động của người dùng.
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                // Lệnh gọi lại này được gọi trong một yêu cầu xác minh không hợp lệ được thực hiện,
                                // chẳng hạn nếu định dạng số điện thoại không hợp lệ.
                                //show text và update ui
                                Toast.makeText( LoginWithOTP.this, "Verify Failed",Toast.LENGTH_SHORT ).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                // Mã xác minh SMS đã được gửi đến số điện thoại được cung cấp
                                // bây giờ cần yêu cầu người dùng nhập mã và sau đó tạo thông tin xác thực
                                // bằng cách kết hợp mã với ID xác minh.


                                // Lưu ID xác minh và gửi lại mã thông báo để có thể sử dụng chúng sau này
                                super.onCodeSent( verificationId, forceResendingToken );
                                gotoEnterOTPCodeActivity( phoneNumber,verificationId );

                            }
                        } )          // Khi xác minh trạng thái đã thay đổi callback
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Đăng nhập thành công, cập nhật giao diện người dùng với thông tin của người dùng đã đăng nhập
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Cập nhật giao diện
                            //gotoMainActivity( edt_phone_num.getText().toString().trim());
                        } else {
                            // Đăng nhập không thành công, hiển thị thông báo và cập nhật giao diện người dùng
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // Mã xác minh đã nhập không hợp lệ
                                Toast.makeText( LoginWithOTP.this, "Lỗi code",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


    private void gotoEnterOTPCodeActivity(String phoneNumber, String verificationId) {
         Intent intent = new Intent(LoginWithOTP.this,EnterOTPCode.class);
         intent.putExtra( "phone_number", phoneNumber );
         intent.putExtra( "verification_id",verificationId );
         startActivity( intent );

    }
//    private void gotoMainActivity(String phoneNumber) {
//        Intent intent = new Intent(LoginWithOTP.this, MainActivity.class );
//        intent.putExtra( "phone_number",phoneNumber );
//        startActivity( intent );
//
//    }
}