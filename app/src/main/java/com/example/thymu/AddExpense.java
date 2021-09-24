package com.example.thymu;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddExpense extends AppCompatActivity {

    // creating variables for
    // EditText and buttons.
    private EditText billType, accNumber, nextBillDate;
    private Button btnAdd;

    // creating a variable for our
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

        // initializing our edittext and button
        billType = findViewById(R.id.idbillType);
        accNumber = findViewById(R.id.idaccNumber);
        nextBillDate = findViewById(R.id.idnextBillDate);

        // below line is used to get the
        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference().child("DataExpenses");

        // initializing our object
        // class variable.
        dataExpenses = new DataExpenses();

        btnAdd = findViewById(R.id.btnAdd);

        // adding on click listener for our button.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String bill = billType.getText().toString();
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


        DatabaseReference newref = databaseReference.push();
        newref.setValue(dataExpenses);


        // after adding this data we are showing toast message.
        Toast.makeText(AddExpense.this, "Data added success", Toast.LENGTH_SHORT).show();

        // we are use add value event listener method
        // which is called with database reference.
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // inside the method of on Data change we are setting
//                // our object class to our database reference.
//                // data base reference will sends data to firebase.
//                DatabaseReference  newref     = databaseReference.push();
//                newref.setValue(dataExpenses);
//
//                // after adding this data we are showing toast message.
//                Toast.makeText(AddExpense.this, "data added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // if the data is not added or it is cancelled then
//                // we are displaying a failure toast message.
//                Toast.makeText(AddExpense.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}

