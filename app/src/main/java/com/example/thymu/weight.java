package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class weight extends AppCompatActivity {

    //creating varialbles for
    //button and numberpicker
    public Button btn_ok;
    private NumberPicker weightnum_picker;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    waterPlan water_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        weightnum_picker = (NumberPicker)findViewById(R.id.weightnum_picker);
        btn_ok = findViewById(R.id.btn_weight);

        weightnum_picker.setValue(R.id.weightnum_picker);
        weightnum_picker.setMaxValue(150);
        weightnum_picker.setMinValue(25);

        // below line is used to get the
        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference().child("waterPlan");

        // initializing our object
        // class variable.
        water_plan = new waterPlan();

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getting text from our edittext fields.
                    int weight = weightnum_picker.getValue();

                    addDatatoFirebase(weight);

                    Intent intent = new Intent(weight.this, exercise.class);
                    startActivity(intent);
            }

        });
    }
    private void addDatatoFirebase(int weight) {

        water_plan.setWeight(weight);

        DatabaseReference newref = databaseReference.push();
        newref.setValue(water_plan);

        // after adding this data we are showing toast message.
        Toast.makeText(weight.this, "Data added success", Toast.LENGTH_SHORT).show();


    }

}
