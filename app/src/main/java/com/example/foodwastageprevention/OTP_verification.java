package com.example.foodwastageprevention;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP_verification extends AppCompatActivity {


    TextView mPhoneNo_TV;

    String _receivedPhoneNo;
    String verificationID;

    AppCompatButton mConfirm_btn;
    EditText mEt1, mEt2, mEt3, mEt4, mEt5, mEt6;

    FirebaseAuth mAuth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);


        // hooks
        mPhoneNo_TV = findViewById(R.id.EnteredPhoneNo);


        mConfirm_btn = findViewById(R.id.confirm_btn);

        mEt1 = findViewById(R.id.et_1);
        mEt2 = findViewById(R.id.et_2);
        mEt3 = findViewById(R.id.et_3);
        mEt4 = findViewById(R.id.et_4);
        mEt5 = findViewById(R.id.et_5);
        mEt6 = findViewById(R.id.et_6);

        progressBar = findViewById(R.id.confirm_progressBar);


        _receivedPhoneNo = getIntent().getStringExtra("phoneNo");

        mAuth = FirebaseAuth.getInstance();
        sendVerificationCode(_receivedPhoneNo);

        requestNextEtFocus();

        mPhoneNo_TV.setText(_receivedPhoneNo);
        Toast.makeText(this, _receivedPhoneNo, Toast.LENGTH_SHORT).show();

        mConfirm_btn.setOnClickListener(view -> {

            mConfirm_btn.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            verifyCode(mEt1.getText().toString() + mEt2.getText().toString() + mEt3.getText().toString() + mEt4.getText().toString() + mEt5.getText().toString() + mEt6.getText().toString());
//            if (TextUtils.isEmpty(mEt1.getText()) || TextUtils.isEmpty(mEt2.getText()) || TextUtils.isEmpty(mEt3.getText()) || TextUtils.isEmpty(mEt4.getText()) || TextUtils.isEmpty(mEt5.getText()) || TextUtils.isEmpty(mEt6.getText())) {
//                mConfirm_btn.setVisibility(View.INVISIBLE);
//                progressBar.setVisibility(View.VISIBLE);
//                verifyCode(mEt1.getText().toString() + mEt2.getText().toString() + mEt3.getText().toString() + mEt4.getText().toString() + mEt5.getText().toString() + mEt6.getText().toString());
//            } else {
//                Toast.makeText(getApplicationContext(), "Wrong OTP : Not verified", Toast.LENGTH_SHORT).show();
//            }
        });


    }

    // request focus for next et
    private void requestNextEtFocus() {
        mEt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    mEt1.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_pin_bg1));
                    mEt2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mEt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    mEt2.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_pin_bg1));
                    mEt3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mEt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    mEt3.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_pin_bg1));
                    mEt4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mEt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    mEt4.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_pin_bg1));
                    mEt5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mEt5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    mEt5.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_pin_bg1));
                    mEt6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mEt6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    mEt6.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_pin_bg1));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void callLoginScreen(View view) {
        startActivity(new Intent(OTP_verification.this, login_registration.class));
    }

    private void sendVerificationCode(String phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phoneNo)  // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                    final String code = credential.getSmsCode();
                    if (code != null) {
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(OTP_verification.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(OTP_verification.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCodeSent(@NonNull String verificationId,
                                       @NonNull PhoneAuthProvider.ForceResendingToken token) {
                    super.onCodeSent(verificationId, token);
                    verificationID = verificationId;
                    Toast.makeText(getApplicationContext(), "Code sent", Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String Code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, Code);
        signInByCredentials(credential);
    }

    private void signInByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OTP_verification.this, CreateYourProfile.class);
                        intent.putExtra("verificationId",verificationID);
                        startActivity(intent);
                    }
                });
    }

}