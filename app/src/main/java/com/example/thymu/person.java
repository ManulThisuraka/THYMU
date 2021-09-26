package com.example.thymu;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class person extends AppCompatActivity {

    public Button btnDelete;

    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    // final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("DataExpenses").child(user.getUid());

    public static Button btnDel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        //btnDel=findViewById(R.id.idbtnDelete);

        //btnDelete = findViewById(R.id.idbtnDelete);
        btnDelete.setOnClickListener(view -> {
            deleteUser();



            /*@Override
            public void onClick(View view) {
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("DataExpenses");
                reference.keepSynced(true);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(FirebaseAuth.getInstance().getUid())){
                            reference.removeValue();
                            Log.d("done",reference.removeValue().toString());

                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });


            }*/
        });


    }

    private void deleteUser() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query dataDel = ref.child("DataExpenses").equalTo(FirebaseAuth.getInstance().getUid());
            dataDel.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot data: dataSnapshot.getChildren()) {
                        data.getRef().removeValue();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });
        }


}

