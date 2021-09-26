package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class categories extends AppCompatActivity {

    public ImageButton img_btn;
    public ImageButton btn_med;
    public  ImageButton btn_water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        btn_water = findViewById(R.id.img_btn_water);
        btn_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categories.this, waterInsert.class);
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
    }


}








