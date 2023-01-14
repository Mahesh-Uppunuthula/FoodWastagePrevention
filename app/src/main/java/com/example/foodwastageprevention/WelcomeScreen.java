package com.example.foodwastageprevention;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void callLoginScreen(View view)
    {
        Intent intent = new Intent(WelcomeScreen.this, login_registration.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair <View,String>(findViewById(R.id.get_started_btn),"get_started_transition_name");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent,options.toBundle());

    }
}