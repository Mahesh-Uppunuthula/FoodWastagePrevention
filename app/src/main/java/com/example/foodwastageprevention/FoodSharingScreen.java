package com.example.foodwastageprevention;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FoodSharingScreen extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    DocumentReference documentReference;

    StatusHelper statusHelper;

    String am_pm_string, type_of_food = " ";
    String[] time_format_array;

    EditText mHrs_et, mMM_et;

    AutoCompleteTextView mAm_Pm_AT;
    TextInputLayout mAddress_layout, mLandmark_Et_layout;
    AppCompatEditText mQuantity_Et, mAddress_et, mLandmark_Et;
    CheckBox mVeg, mNVeg, mAgreement_CB, mTC_CB;

    ArrayAdapter<String> arrayAdapter;
    AppCompatButton mDonate_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_sharing_screen);

        // instances
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        // hooks
        mQuantity_Et = findViewById(R.id.Quantity_ET);
        mAddress_et = findViewById(R.id.Address_et);
        mLandmark_Et = findViewById(R.id.Landmark_ET);
        mAddress_layout = findViewById(R.id.Address_et_layout);
        mLandmark_Et_layout = findViewById(R.id.Landmark_ET_layout);
        mDonate_btn = findViewById(R.id.donate_btn);
        mAm_Pm_AT = findViewById(R.id.Am_Pm_AT);

        mVeg = findViewById(R.id.Veg_CB);
        mNVeg = findViewById(R.id.Non_veg_CB);

        mAgreement_CB = findViewById(R.id.Agreement_CB);
        mTC_CB = findViewById(R.id.Terms_CB);

        mHrs_et = findViewById(R.id.Hrs_Et);
        mMM_et = findViewById(R.id.Min_Et);


        time_format_array = new String[]{"am", "pm"};

        mAgreement_CB.setOnClickListener(view -> {
            enableDonateBtn();
        });
        mTC_CB.setOnClickListener(view -> {
            enableDonateBtn();
        });

        // owner ship details
        mAm_Pm_AT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                am_pm_string = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(FoodSharingScreen.this, am_pm_string, Toast.LENGTH_SHORT).show();
            }
        });

        // setting the adapter
        arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_item, time_format_array);
        mAm_Pm_AT.setAdapter(arrayAdapter);


        // call to feedback screen
        mDonate_btn.setOnClickListener(view -> {
//            Toast.makeText(FoodSharingScreen.this, getCheckBoxData(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(FoodSharingScreen.this, getTime(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(FoodSharingScreen.this, getQuatity(), Toast.LENGTH_SHORT).show();
            uploadStatusToDB();
//            callFeedBackScreen();
        });

    }

    private void uploadStatusToDB() {
        statusHelper = new StatusHelper(getQuatity(),getCheckBoxData(),getTime(),am_pm_string,getAddress().toString(),getLandmark().toString());
        DocumentReference documentReference = firebaseFirestore
                .collection("EntityUsers")
                .document(firebaseUser.getUid())
                .collection("AdminSpecific")
                .document();
        documentReference.set(statusHelper)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "uploaded successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FoodSharingScreen.this, MainActivity.class));
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Note not saved " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void enableDonateBtn() {
        if (mAgreement_CB.isChecked() && mTC_CB.isChecked()){
            mDonate_btn.setVisibility(View.VISIBLE);
        }
    }

    private void callFeedBackScreen() {
        Intent intent = new Intent(FoodSharingScreen.this, FeedbackScreen.class);
        startActivity(intent);
    }

    // check box data
    private String getCheckBoxData() {
        if (mVeg.isChecked() && !mNVeg.isChecked()) {
            type_of_food = " ";
            type_of_food += mVeg.getText().toString();
        } else if (mNVeg.isChecked() && !mVeg.isChecked()) {
            type_of_food = " ";
            type_of_food += mNVeg.getText().toString();
        } else if (mVeg.isChecked() && mNVeg.isChecked()) {
            type_of_food = " ";
            type_of_food += "both";
        } else
            type_of_food = "null";
        return type_of_food;
    }


    @Override
    protected void onStart() {
        super.onStart();
        // requesting focus when the start of the screen
        mQuantity_Et.requestFocus();
//        Editable string = mTerms.getEditableText();
    }

    public Editable getAddress() {
        Editable address = mAddress_et.getText();
        if (address.toString().isEmpty()) {
            mAddress_layout.requestFocus();
            mAddress_layout.setError("Cannot be empty");
        }
        return address;
    }

    public Editable getLandmark() {
        Editable landmark = mLandmark_Et.getText();
        if (landmark.toString().isEmpty()) {
            mLandmark_Et_layout.requestFocus();
            mLandmark_Et_layout.setError("cannot be empty");
        }
        return landmark;
    }

    public Editable landmark() {
        return mLandmark_Et.getText();
    }

    public String getQuatity() {
        Editable quantity = mQuantity_Et.getText();
        return quantity.toString();
    }

    public String getTime() {
        Editable Hrs = mHrs_et.getText();
        Editable Min = mHrs_et.getText();
        String Time = mHrs_et.getText().toString() + mMM_et.getText().toString();
        return Time;

//        int Hours = Integer.parseInt(Hrs.toString());
//        int minutes = Integer.parseInt(Min.toString());


//        if (!TextUtils.isEmpty(Hrs)) {
//            if (!TextUtils.isEmpty(Min)) {
//                Toast.makeText(this, "not empty", Toast.LENGTH_SHORT).show();
////                if (Hours <= 12) {
////                    if (minutes <= 59) {
////                        String dateAndTime = Hrs.toString() + Min.toString();
////                        return dateAndTime;
////                    }
////                }
//            }
//        }
//        return null;
    }


    public void callDashboard(View view) {
        startActivity(new Intent(FoodSharingScreen.this, MainActivity.class));
    }

}