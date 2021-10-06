package com.example.thymu;




import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Calendar;



public class dailyDrink extends AppCompatActivity {
    Button btn_drink;
    EditText et_drinkDate, et_drinkAmount;
    TextView totalDrink;



    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    waterDrink water_drink;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_drink);

        et_drinkDate = findViewById(R.id.et_today);
        et_drinkAmount = findViewById(R.id.et_todayDrink);
        btn_drink = findViewById(R.id.btn_drinkadd);
        totalDrink = findViewById(R.id.tv_todayDrink);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        // below line is used to get the
        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        // below line is used to get reference for our database.
        String uid = FirebaseAuth.getInstance().getUid();

        databaseReference = firebaseDatabase.getReference().child("waterDrink").child(uid);


        // initializing our object
        // class variable.
        water_drink = new waterDrink();



        btn_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.

                String drinkDate = et_drinkDate.getText().toString();
                String drinkAmount = et_drinkAmount.getText().toString();


                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(drinkDate) || TextUtils.isEmpty(drinkAmount)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(dailyDrink.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                }
                else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(drinkDate,drinkAmount);

                    Intent intent = new Intent(dailyDrink.this, waterView.class);
                    startActivity(intent);
                }
            }
        });



        et_drinkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(dailyDrink.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        et_drinkDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }

        });




}


        public void addDatatoFirebase(String drinkDate, String drinkAmount) {

        water_drink.setDrinkDate(drinkDate);
        water_drink.setDrinkAmount(drinkAmount);

        DatabaseReference newref = databaseReference.push();
        newref.setValue(water_drink);

        // after adding this data we are showing toast message.
        Toast.makeText(dailyDrink.this, "Data added success", Toast.LENGTH_SHORT).show();
        }


}