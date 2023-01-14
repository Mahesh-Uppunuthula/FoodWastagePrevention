package com.example.foodwastageprevention;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Test extends AppCompatActivity {

    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        t1 = findViewById(R.id.User);
        t2 = findViewById(R.id.entity);

        String fname, entity;

        fname = getIntent().getStringExtra("FName");
        entity = getIntent().getStringExtra("LName");
        Toast.makeText(this, fname, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, entity, Toast.LENGTH_SHORT).show();

    }
}