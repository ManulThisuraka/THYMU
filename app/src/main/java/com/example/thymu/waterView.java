package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class waterView extends AppCompatActivity {

    Button btn_view, btn_insert, btn_drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_view);

        btn_view = findViewById(R.id.btn_waterview);
        btn_insert = findViewById(R.id.btn_getstart);
        btn_drink = findViewById(R.id.btn_dailydrink);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(waterView.this, myPlan.class);
                startActivity(intent);
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(waterView.this, waterInsert.class);
                startActivity(intent);
            }
        });

        btn_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(waterView.this, dailyDrink.class);
                startActivity(intent);
            }
        });

    }
}