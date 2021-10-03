package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadMedicine extends AppCompatActivity {




    private RecyclerView recyclerViewMedi;
    MedicineAdapter adapter;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_medicine);

        //create a instance from database
        String uid= FirebaseAuth.getInstance().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("MedicineInfo").child(uid);
        recyclerViewMedi=findViewById(R.id.recyclerMedi);
        recyclerViewMedi.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MedicineInfo> options = new FirebaseRecyclerOptions.Builder<MedicineInfo>().setQuery(databaseReference, MedicineInfo.class).build();

        adapter = new MedicineAdapter(options);
        recyclerViewMedi.setAdapter(adapter);




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