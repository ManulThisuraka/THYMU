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
public class MedicineAdapter extends FirebaseRecyclerAdapter<MedicineInfo, MedicineAdapter.medicineViewholder> implements View.OnClickListener{

    public MedicineAdapter firebaseConvAdapter;


    public MedicineAdapter(
            @NonNull FirebaseRecyclerOptions<MedicineInfo> options) {
        super(options);
    }



    @Override
    protected void
    onBindViewHolder(@NonNull medicineViewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull MedicineInfo model) {


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("user", user.toString());
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                String providerId = profile.getProviderId();

                holder.nameMedi.setText(model.getNameMedi());
                holder.reasonMedi.setText(model.getReasonMedi());
                holder.formMedi.setText((model.getFormMedi()));
                holder.timeMedi.setText((model.getTimeMedi()));
                Log.d("holder", model.toString());

            }

        }


        ////////////////////////////////////UPDATE//////////////////////////////////

        holder.btnMediUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(v.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_update_medicine))
                        .setExpanded(true, 1400)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText nameMedi = view.findViewById(R.id.medicineName);
                EditText reasonMedi = view.findViewById(R.id.medicineReason);
                EditText formMedi = view.findViewById(R.id.meidicneForm);
                EditText timeMedi = view.findViewById(R.id.medicineTime);


                Button btnUpdateMedicine = view.findViewById(R.id.btnMedicinePop);

                nameMedi.setText(model.getNameMedi());
                reasonMedi.setText(model.getReasonMedi());
                formMedi.setText(model.getFormMedi());
                timeMedi.setText(model.getTimeMedi());

                dialogPlus.show();

                btnUpdateMedicine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String, Object> map = new HashMap<>();
                        map.put("nameMedi", nameMedi.getText().toString());
                        map.put("reasonMedi", reasonMedi.getText().toString());
                        map.put("formMedi", formMedi.getText().toString());
                        map.put("timeMedi", timeMedi.getText().toString());


                        String uid = FirebaseAuth.getInstance().getUid();
                        Log.d("abcc", getRef(position).getKey());
                        Log.d("map", map.toString());

                        FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("MedicineInfo")
                                .child(uid).child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nameMedi.getContext(), "Medicine Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();


                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.nameMedi.getContext(), "Error!.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });


                    }
                });


            }
        });


////////////////////////////////DELETE//////////////////////////////////////////
        holder.btnMediDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nameMedi.getContext());
                builder.setTitle("Are You Sure ?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String uid = FirebaseAuth.getInstance().getUid();
                        FirebaseDatabase.getInstance("https://thymu-9c71c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("MedicineInfo")
                                .child(uid).child(getRef(position).getKey()).removeValue();


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.nameMedi.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();


                    }
                });
                builder.show();


            }
        });
    }
////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public medicineViewholder
    onCreateViewHolder(@NonNull ViewGroup MedicineInfo,
                       int viewType) {
        View view = LayoutInflater.from(MedicineInfo.getContext()).inflate(R.layout.activity_medicine, MedicineInfo, false);
        return new MedicineAdapter.medicineViewholder(view);
    }
    @Override
    public void onClick(View view) {

    }

    // Sub Class to create references of the views in Card
    // view (here "medicine.xml")
    class medicineViewholder extends RecyclerView.ViewHolder {
        TextView nameMedi,reasonMedi,formMedi,timeMedi;
        Button btnMediUpdate, btnMediDelete;

        public medicineViewholder(@NonNull View itemView) {
            super(itemView);

            nameMedi = itemView.findViewById(R.id.nameMedi);
            reasonMedi = itemView.findViewById(R.id.reasonMedi);
            formMedi = itemView.findViewById(R.id.formMedi);
            timeMedi = itemView.findViewById(R.id.timeMedi);


            btnMediUpdate = (Button) itemView.findViewById(R.id.btnMediUpdate);
            btnMediDelete = (Button) itemView.findViewById(R.id.btnMediDelete);
        }
    }
}







