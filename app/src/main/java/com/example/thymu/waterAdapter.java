package com.example.thymu;



import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import java.util.Locale;
import java.util.Map;

class waterAdapter extends FirebaseRecyclerAdapter<waterPlan, waterAdapter.waterViewholder> {

    int hour,minute;


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
                        .setContentHolder(new ViewHolder(R.layout.activity_water_pop))
                        .setExpanded(true, 1400)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText weight = view.findViewById(R.id.et_weightPop);
                EditText workout = view.findViewById(R.id.et_workPop);
                Button wakeup = view.findViewById(R.id.btn_popwakeup);
                Button sleep = view.findViewById(R.id.btn_popsleep);


                Button btnUpdate = view.findViewById(R.id.btn_waterEdit);
                Button btn_wakeup = view.findViewById(R.id.btn_popwakeup);
                Button btn_sleep = view.findViewById(R.id.btn_popsleep);

                weight.setText(model.getWeight());
                workout.setText(model.getWorkoutMins());
                wakeup.setText(model.getWakeupTime());
                sleep.setText(model.getBedTime());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String, Object> map = new HashMap<>();
                        map.put("weight", weight.getText().toString());
                        map.put("workoutMins", workout.getText().toString());
                        map.put("wakeupTime", wakeup.getText().toString());
                        map.put("bedTime", sleep.getText().toString());


                        //.child(getRef(position).getKey())
                        //DatabaseReference newref;
                        String uid = FirebaseAuth.getInstance().getUid();
                        Log.d("abcc", getRef(position).getKey());
                        Log.d("map", map.toString());

                        FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("waterPlan")
                                .child(uid).child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.weight.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();


                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.weight.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });


                    }
                });




            }

        });


        /////////////////////////////////////////delete////////////////////////////////////////////////

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.weight.getContext());
                builder.setTitle("Are You Sure ?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String uid = FirebaseAuth.getInstance().getUid();
                        FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("waterPlan")
                                .child(uid).child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.weight.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
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
