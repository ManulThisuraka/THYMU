package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertMedicine extends AppCompatActivity {
    // creating variables for
    // EditText and buttons.
    private EditText nameMed, reasonMed, formMed, timeMed;
    private Button button4;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    MedicineInfo medicineInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_medicine);

        // initializing our edittext and button
        nameMed = findViewById(R.id.idnameMedi);
        reasonMed = findViewById(R.id.idreasonMedi);
        formMed = findViewById(R.id.idformMedi);
        timeMed = findViewById(R.id.idtimeMedi);

        // below line is used to get the
        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference().child("MedicineInfo");

        // initializing our object
        // class variable.
        medicineInfo = new MedicineInfo();

        button4 = findViewById(R.id.button4);

        // adding on click listener for our button.
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String nameMedi = nameMed.getText().toString();
                String reasonMedi = reasonMed.getText().toString();
                String formMedi = formMed.getText().toString();
                String timeMedi = timeMed.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(nameMedi) && TextUtils.isEmpty(reasonMedi) && TextUtils.isEmpty(formMedi) && TextUtils.isEmpty(timeMedi)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(InsertMedicine.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(nameMedi, reasonMedi, formMedi, timeMedi);
                }
            }
        });
    }

    private void addDatatoFirebase(String nameMedi, String reasonMedi, String formMedi, String timeMedi) {
        // below 3 lines of code is used to set
        // data in our object class.
        medicineInfo.setNameMedi(nameMedi);
        medicineInfo.setReasonMedi(reasonMedi);
        medicineInfo.setFormMedi(formMedi);
        medicineInfo.setTimeMedi(timeMedi);


        DatabaseReference newref = databaseReference.push();
        newref.setValue(medicineInfo);

        // after adding this data we are showing toast message.
        Toast.makeText(InsertMedicine.this, "Succesfully", Toast.LENGTH_SHORT).show();

    }
}



