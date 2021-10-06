package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class categories extends AppCompatActivity {

    public ImageButton img_btn;
    public ImageButton btn_med;
    public  ImageButton btn_water;
    public ImageButton btn_spend;

    public Button btnLogout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        btn_water = findViewById(R.id.img_btn_water);
        btn_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categories.this, waterView.class);
                startActivity(intent);
            }
        });

        img_btn = findViewById(R.id.imageButton);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categories.this, expenses.class);
                startActivity(intent);
            }
        });


        btn_med = findViewById(R.id.btnidmed);
        btn_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categories.this, viewMedicine.class);
                startActivity(intent);
            }
        });

        btn_spend = findViewById(R.id.imageButton4);
        btn_spend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categories.this,SplashScreenActivity.class);
                startActivity(intent);
            }
        });


        btnLogout = findViewById(R.id.btnLogout2);
        mAuth = FirebaseAuth.getInstance();
        btnLogout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(categories.this, LoginActivity.class));
        });








    }


}








