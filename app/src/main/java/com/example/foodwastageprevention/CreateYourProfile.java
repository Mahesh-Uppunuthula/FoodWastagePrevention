package com.example.foodwastageprevention;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CreateYourProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String selectedOwnerShip;
    String[] type_of_ownership;
    Editable Fname, Lname;

    StorageReference storageReference;

    AutoCompleteTextView mCity_ET, mOwnerShip_ET;

    AppCompatEditText mFnameET, mLNameET;

    TextInputLayout mFNameET_layout, mLNameET_layout, mOwnerShip_layout;

    ArrayAdapter<String> ownerShip_adapter;

    Uri imageUri;

    ImageView mUploadProfilePic;
    CardView mUploadProfilePic_CV;

    UserHelper userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_profile);


        // hooks

        mFnameET = findViewById(R.id.FName_ET);
        mLNameET = findViewById(R.id.LName_ET);
        mFNameET_layout = findViewById(R.id.FName_ET_layout);
        mLNameET_layout = findViewById(R.id.LName_ET_layout);
        mOwnerShip_layout = findViewById(R.id.Donater_type_layout);

        mOwnerShip_ET = findViewById(R.id.Donater_type_ET);
        mUploadProfilePic = findViewById(R.id.UploadProfilePic);
        mUploadProfilePic_CV = findViewById(R.id.UploadProfilePic_CV);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        // getting instance
        Toast.makeText(CreateYourProfile.this, FirebaseAuth.getInstance().getUid(), Toast.LENGTH_SHORT).show();

        type_of_ownership = new String[]{"RESTAURANT OWNER", "CATERER", "INDIVIDUAL", "OTHER"};

//        Cities = new String[]{""};


        // owner ship details
        mOwnerShip_ET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOwnerShip = adapterView.getItemAtPosition(i).toString();
            }
        });


        findViewById(R.id.register_btn).setOnClickListener(view -> {
            if (validateUserData()) {
                // data is valid and register the user in the data
                Toast.makeText(CreateYourProfile.this, "User data is correct", Toast.LENGTH_SHORT).show();

                // upload the user profile image
                uploadProfileImage(imageUri);
                // upload the user data to the database
                saveUserDataInDataBase();

                Intent intent = new Intent(CreateYourProfile.this, MainActivity.class);
                intent.putExtra("FName",Fname);
                intent.putExtra("Name",Lname);
                Toast.makeText(CreateYourProfile.this, selectedOwnerShip, Toast.LENGTH_SHORT).show();
                startActivity(intent);

            } else {
                Toast.makeText(CreateYourProfile.this, "couldn't  register ", Toast.LENGTH_SHORT).show();
            }

        });

        // setting the adapter
        ownerShip_adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_item, type_of_ownership);
        mOwnerShip_ET.setAdapter(ownerShip_adapter);

        // uploading the profile image
        findViewById(R.id.UploadProfilePic).setOnClickListener(view -> {
            selectImage();
        });

    }

    private boolean validateUserData() {

        Fname = mFnameET.getText();
        Lname = mLNameET.getText();

        if (TextUtils.isEmpty(selectedOwnerShip)) {
            mOwnerShip_ET.setError("Cannot be empty");
            mOwnerShip_ET.requestFocus();
        } else {
            Toast.makeText(CreateYourProfile.this, selectedOwnerShip, Toast.LENGTH_SHORT).show();
            if ( !selectedOwnerShip.equals(type_of_ownership[0]) && !selectedOwnerShip.equals(type_of_ownership[1]) && !selectedOwnerShip.equals(type_of_ownership[2]) && !selectedOwnerShip.equals(type_of_ownership[3])) {
//                Toast.makeText(CreateYourProfile.this, selectedOwnerShip, Toast.LENGTH_SHORT).show();
                mOwnerShip_ET.setError("invalid select");
                mOwnerShip_ET.requestFocus();
            } else {
                if (TextUtils.isEmpty(Fname)) {
                    mFNameET_layout.setError("Cannot be empty");
                    mFNameET_layout.requestFocus();
                } else {
                    if (TextUtils.isEmpty(Lname)) {
                        mLNameET_layout.setError("Cannot be empty");
                        mLNameET_layout.requestFocus();
                    } else {
                        // code to register the user in the database
                        return  true;
                    }
                }
            }
        }
        return false;
    }

    private void saveUserDataInDataBase() {

        // get all the users data inside one object

        userData = new UserHelper(mAuth.getUid(),selectedOwnerShip,Fname.toString(),Lname.toString());

        // store the user data with Uid
        databaseReference.setValue(userData);
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && data != null && data.getData() != null) {
            imageUri = data.getData();
            mUploadProfilePic.setImageURI(imageUri);
        }
    }


    private void uploadProfileImage(Uri selectedImageUri) {
        String userId = mAuth.getUid();
        storageReference = FirebaseStorage.getInstance().getReference("UserProfileImages").child("EntitySide").child(userId);
        storageReference.putFile(selectedImageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(CreateYourProfile.this, "uploaded Successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(CreateYourProfile.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    public void callLoginScreen(View view) {
        startActivity(new Intent(CreateYourProfile.this, login_registration.class));
    }

}