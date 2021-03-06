package com.example.thymu;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class expenses extends AppCompatActivity {

    public Button btn;
    public Button btn1;
    public Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(expenses.this, viewExpenseMain.class);
                startActivity(intent);
            }
        });
        btn1 = findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(expenses.this, AddExpense.class);
                startActivity(intent);
            }
        });

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(view -> {
            DatabaseReference ref = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
            Query dataDel = ref.child("DataExpenses").child(FirebaseAuth.getInstance().getUid().toString()).orderByChild("position").equalTo(FirebaseAuth.getInstance().getUid().toString());
            dataDel.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {


                        data.getRef().removeValue();
                        Intent intent = new Intent(expenses.this, AddExpense.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        });
    }
}