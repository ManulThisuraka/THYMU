package com.example.thymu;



import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;


import java.util.HashMap;
import java.util.Map;

class waterAdapter extends FirebaseRecyclerAdapter<waterPlan, waterAdapter.waterViewholder> {
    public waterAdapter(
            @NonNull FirebaseRecyclerOptions<waterPlan> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")


    @Override
    protected void
    onBindViewHolder(@NonNull waterAdapter.waterViewholder holder,
                     @SuppressLint("RecyclerView") final int position, @NonNull waterPlan model) {


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                String providerId = profile.getProviderId();

                holder.weight.setText(model.getWeight());
                holder.workout.setText(model.getWorkoutMins());
                holder.wakeupTime.setText((model.getWakeupTime()));
                holder.bedTime.setText((model.getBedTime()));


            }
        }

        /////////////////////////////update///////////////////////////////////////////////////////////////////////

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
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




    }





    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public waterAdapter.waterViewholder
    onCreateViewHolder(@NonNull ViewGroup waterPlan,
                       int viewType) {
        View view = LayoutInflater.from(waterPlan.getContext()).inflate(R.layout.activity_water_card, waterPlan, false);
        return new waterAdapter.waterViewholder(view);
    }

    // Sub Class to create references of the views in Card
    // view (here "person.xml")
    class waterViewholder
            extends RecyclerView.ViewHolder {

        TextView weight, workout, wakeupTime, bedTime;
        Button btn_edit, btn_delete;

        public waterViewholder(@NonNull View itemView) {
            super(itemView);

            weight = itemView.findViewById(R.id.tv_wtvalue);
            workout = itemView.findViewById(R.id.tv_wkvalue);
            wakeupTime = itemView.findViewById(R.id.tv_wkuptime);
            bedTime = itemView.findViewById(R.id.tv_slptime);
        }
    }
}
