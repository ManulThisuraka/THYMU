package com.example.thymu;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;


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
                Log.d("holder",model.toString());

            }

        }

    }

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





