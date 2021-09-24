package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class wakeup extends AppCompatActivity {

    Button btn_ok;
    Button btn_wakeupTime;
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
        setContentView(R.layout.activity_wakeup);

        btn_wakeupTime = findViewById(R.id.btn_wakeupTime);
        btn_ok = findViewById(R.id.btn_wakeup);

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

                CharSequence wakeupTime = btn_wakeupTime.getText();

                addDatatoFirebase(wakeupTime);

                Intent intent = new Intent(wakeup.this, sleeping.class);
                startActivity(intent);

            }
        });

    }

    private void addDatatoFirebase(CharSequence wakeupTime) {

        water_plan.setWakeupTime(wakeupTime);

        DatabaseReference newref = databaseReference.push();
        newref.setValue(water_plan);

        // after adding this data we are showing toast message.
        Toast.makeText(wakeup.this, "Data added success", Toast.LENGTH_SHORT).show();
    }


    public void popTimepicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                btn_wakeupTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

            }
        };

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.show();

    }

}



    /*public void popTimepicker(View view1){
        TimePickerDialog.OnTimeSetListener onTimeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute= selectedMinute;
                btn_wakeupTime.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
                btn_bedTime.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,style, onTimeSetListener1, hour, minute,true);
        timePickerDialog.show();
    }*/

