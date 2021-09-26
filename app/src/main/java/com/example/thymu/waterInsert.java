package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class waterInsert extends AppCompatActivity {
    Button btn_wakeUP, btn_sleeping , btn_con;
    private EditText et_weight, et_workout;
    int hour, minute;



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
        setContentView(R.layout.activity_water_insert);

        et_weight = findViewById(R.id.et_weight);
        et_workout = findViewById(R.id.et_workout);
        btn_wakeUP = findViewById(R.id.btn_wakeUP);
        btn_sleeping = findViewById(R.id.btn_sleeping);
        btn_con = findViewById(R.id.btn_continue);


        // below line is used to get the
        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        // below line is used to get reference for our database.
        String uid = FirebaseAuth.getInstance().getUid();
        //databaseReference = firebaseDatabase.getReference().child("waterPlan").child(uid);
        databaseReference = firebaseDatabase.getReference().child("waterPlan").child(uid);

        // initializing our object
        // class variable.
        water_plan = new waterPlan();

        btn_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.

                String weight = et_weight.getText().toString();
                String workout = et_workout.getText().toString();
                CharSequence wakeupTime = btn_wakeUP.getText();
                CharSequence bedTime = btn_sleeping.getText();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(weight) && TextUtils.isEmpty(workout)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(waterInsert.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(weight,workout,wakeupTime,bedTime);

                    Intent intent = new Intent(waterInsert.this, myPlan.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void addDatatoFirebase(String weight, String workout, CharSequence wakeupTime,CharSequence bedTime) {

        water_plan.setWeight(weight);
        water_plan.setWorkoutHrs(workout);
        water_plan.setWakeupTime(wakeupTime);
        water_plan.setBedTime(bedTime);

        DatabaseReference newref = databaseReference.push();
        newref.setValue(water_plan);

        // after adding this data we are showing toast message.
        Toast.makeText(waterInsert.this, "Data added success", Toast.LENGTH_SHORT).show();

    }

    public void onClick(View v){
        if(v==btn_wakeUP){
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker1, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;

                        btn_wakeUP.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    }
                };

                int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
                timePickerDialog.show();
        }
        if(v==btn_sleeping){
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker1, int selectedHour, int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;
                    btn_sleeping.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                }
            };

            int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
            timePickerDialog.show();
        }
    }



}