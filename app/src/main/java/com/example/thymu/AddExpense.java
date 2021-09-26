package com.example.thymu;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddExpense extends AppCompatActivity {

    // creating variables for
    // EditText and buttons.
    private EditText billType, accNumber, nextBillDate;
    private Button btnAdd;
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;
    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    // creating a variable for
    // our object class
    DataExpenses dataExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        billType = findViewById(R.id.idbillType);
        accNumber = findViewById(R.id.idaccNumber);
        nextBillDate = findViewById(R.id.idnextBillDate);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        String uid = FirebaseAuth.getInstance().getUid();
        Log.d("abc", uid);
        databaseReference = firebaseDatabase.getReference().child("DataExpenses").child(uid);
        dataExpenses = new DataExpenses();
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {      // adding on click listener for our add button.
            @Override
            public void onClick(View v) {
                String bill = billType.getText().toString();    // getting text from our edittext fields.
                String account = accNumber.getText().toString();
                String nextdate = nextBillDate.getText().toString();
                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(bill) && TextUtils.isEmpty(account) && TextUtils.isEmpty(nextdate)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(AddExpense.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(bill, account, nextdate);
                }
            }
        });
    }

    private void addDatatoFirebase(String bill, String account, String nextdate) {
        // below 3 lines of code is used to set
        // data in our object class.
        dataExpenses.setBill_type(bill);
        dataExpenses.setAcc_num(account);
        dataExpenses.setNext_bill(nextdate);
        dataExpenses.setPosition(FirebaseAuth.getInstance().getUid());

        DatabaseReference newref = databaseReference.push();
        Log.d("abc", newref.toString());
        newref.setValue(dataExpenses);

        // after adding this data we are showing toast message.
        Toast.makeText(AddExpense.this, "Data added success", Toast.LENGTH_SHORT).show();
    }
}

