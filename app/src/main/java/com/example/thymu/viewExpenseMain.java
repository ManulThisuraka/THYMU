package com.example.thymu;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class viewExpenseMain extends AppCompatActivity {

    private RecyclerView recyclerView;
    expenseAdpter adapter; // Create Object of the Adapter class
    DatabaseReference databaseReference; // Create object of the

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        String uid = FirebaseAuth.getInstance().getUid(); // Create a instance of the database and get
        databaseReference = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("DataExpenses").child(uid); // its reference
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));   // To display the Recycler view linearly
        // It is a class provide by the FirebaseUI to make a
        FirebaseRecyclerOptions<DataExpenses> options = new FirebaseRecyclerOptions.Builder<DataExpenses>().setQuery(databaseReference, DataExpenses.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new expenseAdpter(options);
        recyclerView.setAdapter(adapter);   // Connecting Adapter class with the Recycler view*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
