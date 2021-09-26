/*
package com.example.thymu;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOExpenses {
    public static DatabaseReference databaseReference ;


    public DAOExpenses(){
       FirebaseDatabase db =FirebaseDatabase.getInstance();
       databaseReference = db.getReference(DataExpenses.class.getSimpleName());
    }
    public Task<Void>add(DataExpenses exp){
        return databaseReference.push().setValue(exp);
    }


}
*/
