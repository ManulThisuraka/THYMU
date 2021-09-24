package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class exercise extends AppCompatActivity {

    private Button btn_ok;
    private NumberPicker workout_picker;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    waterPlan water_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        btn_ok = findViewById(R.id.btn_workout);

        workout_picker = (NumberPicker) findViewById(R.id.workout_picker);

        workout_picker.setValue(R.id.workout_picker);
        workout_picker.setMaxValue(150);
        workout_picker.setMinValue(25);


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
                int workoutHrs = workout_picker.getValue();

                addDatatoFirebase(workoutHrs);

                Intent intent = new Intent(exercise.this, wakeup.class);
                startActivity(intent);
            }
        });

    }

    private void addDatatoFirebase(int workoutHrs){

        water_plan.setWorkoutHrs(workoutHrs);

        DatabaseReference newref = databaseReference.push();
        newref.setValue(water_plan);

        // after adding this data we are showing toast message.
        Toast.makeText(exercise.this, "Data added success", Toast.LENGTH_SHORT).show();
    }
}
