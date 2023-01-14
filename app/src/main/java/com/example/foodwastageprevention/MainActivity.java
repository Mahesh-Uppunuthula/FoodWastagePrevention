package com.example.foodwastageprevention;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Uri imageUri;
    String image;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;
    TextView mUsername, mEntity;
    ImageView mUser_profile_image, mlogout_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hooks
        mlogout_btn = findViewById(R.id.logout_btn);
        mUsername = findViewById(R.id.user_name_TV);
        mEntity = findViewById(R.id.ownerShip_Tv);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        String FName, ownerShip_type;

        image = getIntent().getStringExtra("Uri");
        mUser_profile_image = findViewById(R.id.User_profile_image);

        mlogout_btn.setOnClickListener(view -> {
            firebaseAuth.signOut();
            Toast.makeText(MainActivity.this, "Signed out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, WelcomeScreen.class));
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(MainActivity.this, WelcomeScreen.class));
            finish();
        }
    }

    //        if (currentUser == null) {
//
//
//        } else {
//            Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(MainActivity.this, FoodSharingScreen.class));
//        }

    private void setUserProfileImage() {
//        Toast.makeText(MainActivity.this, image, Toast.LENGTH_SHORT).show();
//        mUser_profile_image.setImageURI(Uri.parse(image));
    }

    // call profile page

    public void callProfilePage(View view) {
        Toast.makeText(MainActivity.this, "Profile Page", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, CreateYourProfile.class));
        finish();
    }

    public void callFoodSharingScreen(View view) {
        startActivity(new Intent(MainActivity.this, FoodSharingScreen.class));
        finish();
    }
}