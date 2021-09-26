package com.example.thymu;



import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.api.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;


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
    onBindViewHolder(@NonNull expenseViewholder holder,int position, @NonNull DataExpenses model) {


        //holder.deleteButton.setOnClickListener(view -> delete(holder.getAdapterPosition()));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("user",user.toString());
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                String providerId = profile.getProviderId();
                // Add firstname from model class (here
                // "person.class")to appropriate view in Card
                // view (here "person.xml")
                holder.bill_type.setText(model.getBill_type());

                // Add lastname from model class (here
                // "person.class")to appropriate view in Card
                // view (here "person.xml")
                holder.acc_num.setText(model.getAcc_num());

                // Add age from model class (here
                // "person.class")to appropriate view in Card
                // view (here "person.xml")
                holder.next_bill.setText((model.getNext_bill()));

            }

        }
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
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
        public expenseViewholder(@NonNull View itemView) {
            super(itemView);

            bill_type = itemView.findViewById(R.id.bill_type);
            acc_num = itemView.findViewById(R.id.acc_num);
            next_bill = itemView.findViewById(R.id.next_bill);
        }
    }



    /*protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        View view;
        TextView bill_type, acc_num, next_bill;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bill_type=itemView.findViewById(R.id.bill_type);
            acc_num=itemView.findViewById(R.id.acc_num);
            next_bill=itemView.findViewById(R.id.next_bill);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            //expenseAdpter.

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }*/



}





