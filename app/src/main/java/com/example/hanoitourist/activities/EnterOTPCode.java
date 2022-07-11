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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanoitourist.MainActivity;
import com.example.hanoitourist.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class EnterOTPCode extends AppCompatActivity {
    AppCompatEditText edtOTPNum;
    AppCompatButton btnOtp_signIn,btn_resend;
    String mPhoneNumber;
    String mVerificationId;
    FirebaseAuth mAuth;
    PhoneAuthProvider.ForceResendingToken  mForceResendingToken;
    TextView txtUserName;
    ImageView btnBack;
    public static final String TAG = EnterOTPCode.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_enter_otpcode );
        Intent intent = getIntent();
        String txtName = intent.getStringExtra( "phone_number" );
        txtUserName = findViewById( R.id.hi_user );
        btnBack = findViewById( R.id.btn_backEnterCode );
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
        txtUserName.setText("Hi, "+txtName);
        mAuth = FirebaseAuth.getInstance();
        getDataIntent();
        edtOTPNum = findViewById( R.id.edt_OTP_num );

        btnOtp_signIn = findViewById( R.id.otp_signIn );
        btnOtp_signIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOtp = edtOTPNum.getText().toString().trim();
                onCLickSendOtp(strOtp);
            }
        } );

        btn_resend = findViewById( R.id.resend );
        btn_resend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reSendOtpCode();
            }
        } );

    }

    private void onCLickSendOtp(String strOtp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, strOtp);
        signInWithPhoneAuthCredential(credential);
    }
    private void reSendOtpCode(){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mPhoneNumber)       // Số điện thoại để xác minh
                        .setTimeout(60L, TimeUnit.SECONDS) // Thời gian chờ và đơn vị
                        .setActivity(this)
                        .setForceResendingToken( mForceResendingToken )// Hoạt động (đối với ràng buộc gọi lại)
                        .setCallbacks( new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText( EnterOTPCode.this, "Verify Failed",Toast.LENGTH_SHORT ).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent( verificationId, forceResendingToken );
                                mVerificationId = verificationId;
                                mForceResendingToken = forceResendingToken;
                            }
                        } )          // Khi trạng thái xác minh đã thay đổi
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void getDataIntent(){
        mPhoneNumber = getIntent().getStringExtra( "phone_number" );
        mVerificationId = getIntent().getStringExtra( "verification_id" );
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Đăng nhập thành công, cập nhật giao diện người dùng với thông tin của người dùng đã đăng nhập
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Cập nhật UI
                            gotoMainActivity(user.getPhoneNumber());
                        } else {
                            // Đăng nhập thất bại, hiển thị thông báo và cập nhật giao diện người dùng
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // Mã xác minh đã nhập không hợp lệ
                                Toast.makeText( EnterOTPCode.this, "Lỗi code otp",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void gotoMainActivity(String phoneNumber) {
        Intent intent = new Intent(EnterOTPCode.this, MainActivity.class );
        intent.putExtra( "phone_number",phoneNumber );
        startActivity( intent );
        finish();
    }
}