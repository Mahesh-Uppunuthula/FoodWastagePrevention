package com.example.foodwastageprevention;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.textfield.TextInputLayout;

public class login_registration extends AppCompatActivity {

    TextInputLayout mMobileNoET_layout;
    AppCompatEditText mMobileNoET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        // changing the status bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));


        // hooks
        mMobileNoET_layout = findViewById(R.id.Mobile_number_TIL);
        mMobileNoET = findViewById(R.id.Mobile_number_et);

        Editable _phoneNo = mMobileNoET.getText();

        // onClickVerifyNumber
        findViewById(R.id.verify_number_btn).setOnClickListener(view -> {

            if (validate(_phoneNo.toString())) {
                Intent intent = new Intent(login_registration.this, OTP_verification.class);
                intent.putExtra("phoneNo", _phoneNo.toString());

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(findViewById(R.id.verify_number_btn), "verify_transition_name");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
                startActivity(intent, options.toBundle());

                Toast.makeText(this, _phoneNo, Toast.LENGTH_SHORT).show();
            }
        });


    }

    // validating phone number
    private boolean validate(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            mMobileNoET_layout.setError("Cannot be empty");
            mMobileNoET_layout.requestFocus();
        } else {
            if (phoneNumber.length() != 10 ) {
                mMobileNoET_layout.setError("Enter a valid number");
                mMobileNoET_layout.requestFocus();
            } else
                return true;
        }
        return false;
    }

    // onClickBackBtn
    public void callWelcomeScreen(View view) {
        startActivity(new Intent(this, WelcomeScreen.class));
    }
}


//    private void sendCodeToUser(String phoneNumber) {
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber("+91" + phoneNumber)  // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(this)                 // Activity (for callback binding)
//                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }
//
//    // call backs
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
//            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                @Override
//                public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
//                    final String code = credential.getSmsCode();
//                    if (code != null) {
//                        verifycode(code);
//                    }
//                }
//
//                @Override
//                public void onVerificationFailed(@NonNull FirebaseException e) {
//                    Toast.makeText(getApplicationContext(), "Verification Failed", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onCodeSent(@NonNull String verificationId,
//                                       @NonNull PhoneAuthProvider.ForceResendingToken token) {
//                    super.onCodeSent(verificationId, token);
//                    verificationID = verificationId;
//                    Toast.makeText(getApplicationContext(), "Code sent", Toast.LENGTH_SHORT).show();
////                    btnverify.setEnabled(true);
////                    bar.setVisibility(View.INVISIBLE);
//                }
//            };
//
//
//    private void verifycode(String Code) {
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, Code);
//        signinbyCredentials(credential);
//    }
//

//    }