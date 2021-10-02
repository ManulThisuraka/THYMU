package com.example.thymu;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class expenseAdpter extends FirebaseRecyclerAdapter<DataExpenses, expenseAdpter.expenseViewholder> implements View.OnClickListener {

    public expenseAdpter firebaseConvAdapter;


    public expenseAdpter(
            @NonNull FirebaseRecyclerOptions<DataExpenses> options) {
        super(options);
    }


    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")


    @Override
    protected void
    onBindViewHolder(@NonNull expenseViewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull DataExpenses model) {


        //holder.deleteButton.setOnClickListener(view -> delete(holder.getAdapterPosition()));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("user", user.toString());
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                String providerId = profile.getProviderId();

                holder.bill_type.setText(model.getBill_type());
                holder.acc_num.setText(model.getAcc_num());
                holder.next_bill.setText((model.getNext_bill()));
            }

        }
        ////////////////////////////////////////////////////////////

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(v.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_update_pop))
                        .setExpanded(true, 1400)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText type = view.findViewById(R.id.popType);
                EditText acc = view.findViewById(R.id.popAcc);
                EditText next = view.findViewById(R.id.popNext);


                Button btnUpdate = view.findViewById(R.id.popbtnUpdate);

                type.setText(model.getBill_type());
                acc.setText(model.getAcc_num());
                next.setText(model.getNext_bill());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String, Object> map = new HashMap<>();
                        map.put("acc_num", acc.getText().toString());
                        map.put("bill_type", type.getText().toString());
                        map.put("next_bill", next.getText().toString());

//.child(getRef(position).getKey())
                        //DatabaseReference newref;
                        String uid = FirebaseAuth.getInstance().getUid();
                        Log.d("abcc", getRef(position).getKey());
                        Log.d("map", map.toString());

                        FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("DataExpenses")
                                .child(uid).child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.acc_num.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();


                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.acc_num.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });


                    }
                });


            }
        });




        ///////////////////////////////////// Delete /////////////////////////

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.acc_num.getContext());
                builder.setTitle("Are You Sure ?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String uid = FirebaseAuth.getInstance().getUid();
                        FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("DataExpenses")
                                .child(uid).child(getRef(position).getKey()).removeValue();


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.acc_num.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();


                    }
                });
                builder.show();



            }
        });


////////////////////////////////////////////////////////////////////////////////////////

    }

    @NonNull
    @Override
    public expenseViewholder
    onCreateViewHolder(@NonNull ViewGroup DataExpenses,
                       int viewType) {
        View view = LayoutInflater.from(DataExpenses.getContext()).inflate(R.layout.activity_person, DataExpenses, false);
        return new expenseAdpter.expenseViewholder(view);
    }


    @Override
    public void onClick(View view) {

    }


    // Sub Class to create references of the views in Card
    // view (here "person.xml")
    class expenseViewholder extends RecyclerView.ViewHolder {
        TextView bill_type, acc_num, next_bill;
        Button btnEdit, btnDelete;

        public expenseViewholder(@NonNull View itemView) {
            super(itemView);

            bill_type = itemView.findViewById(R.id.bill_type);
            acc_num = itemView.findViewById(R.id.acc_num);
            next_bill = itemView.findViewById(R.id.next_bill);

            btnEdit = (Button) itemView.findViewById(R.id.idbtnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.idbtnDelete);
        }
    }
}





