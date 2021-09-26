package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class myPlan extends AppCompatActivity {


    private RecyclerView recyclerView;
    waterAdapter adapter; // Create Object of the Adapter class
    DatabaseReference databaseReference; // Create object of the
    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);



        // Create a instance of the database and get
        // its reference
        String uid = FirebaseAuth.getInstance().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("waterPlan").child(uid);

        recyclerView = findViewById(R.id.tb_item3);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<waterPlan> options = new FirebaseRecyclerOptions.Builder<waterPlan>().setQuery(databaseReference, waterPlan.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new waterAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}