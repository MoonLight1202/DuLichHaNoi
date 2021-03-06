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
                        .setPhoneNumber(phoneNumber)       // S??? ??i???n tho???i ????? x??c minh
                        .setTimeout(60L, TimeUnit.SECONDS) // Th???i gian ch??? v?? ????n v???
                        .setActivity(this)                 // Ho???t ?????ng (?????i v???i r??ng bu???c g???i l???i)
                        // callback x??c th???c s??t
                        .setCallbacks( new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                // hd trong hai t??nh hu???ng:
                                // 1 - X??c minh t???c th??. Trong m???t s??? tr?????ng h???p, s??? ??i???n tho???i c?? th??? ???????c x??c minh ngay l???p t???c
                                // m?? kh??ng c???n g???i ho???c nh???p m?? x??c minh.
                                // 2 - T??? ?????ng nghe. Tr??n m???t s??? thi???t b??? D???ch v??? Google Play c?? th??? t??? ?????ng
                                // Ph??t hi???n SMS x??c minh ?????n v?? th???c hi???n x??c minh m?? kh??ng c???n // H??nh ?????ng c???a ng?????i d??ng.
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                // L???nh g???i l???i n??y ???????c g???i trong m???t y??u c???u x??c minh kh??ng h???p l??? ???????c th???c hi???n,
                                // ch???ng h???n n???u ?????nh d???ng s??? ??i???n tho???i kh??ng h???p l???.
                                //show text v?? update ui
                                Toast.makeText( LoginWithOTP.this, "Verify Failed",Toast.LENGTH_SHORT ).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                // M?? x??c minh SMS ???? ???????c g???i ?????n s??? ??i???n tho???i ???????c cung c???p
                                // b??y gi??? c???n y??u c???u ng?????i d??ng nh???p m?? v?? sau ???? t???o th??ng tin x??c th???c
                                // b???ng c??ch k???t h???p m?? v???i ID x??c minh.


                                // L??u ID x??c minh v?? g???i l???i m?? th??ng b??o ????? c?? th??? s??? d???ng ch??ng sau n??y
                                super.onCodeSent( verificationId, forceResendingToken );
                                gotoEnterOTPCodeActivity( phoneNumber,verificationId );

                            }
                        } )          // Khi x??c minh tr???ng th??i ???? thay ?????i callback
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // ????ng nh???p th??nh c??ng, c???p nh???t giao di???n ng?????i d??ng v???i th??ng tin c???a ng?????i d??ng ???? ????ng nh???p
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // C???p nh???t giao di???n
                            //gotoMainActivity( edt_phone_num.getText().toString().trim());
                        } else {
                            // ????ng nh???p kh??ng th??nh c??ng, hi???n th??? th??ng b??o v?? c???p nh???t giao di???n ng?????i d??ng
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // M?? x??c minh ???? nh???p kh??ng h???p l???
                                Toast.makeText( LoginWithOTP.this, "L???i code",Toast.LENGTH_SHORT).show();
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