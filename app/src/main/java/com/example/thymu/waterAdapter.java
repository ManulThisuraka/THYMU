package com.example.thymu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.text.BreakIterator;

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
                     int position, @NonNull waterPlan model) {


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                String providerId = profile.getProviderId();

                holder.weight.setText(model.getWeight());
                holder.workout.setText(model.getWorkoutHrs());
                holder.wakeupTime.setText((model.getWakeupTime()));
                holder.bedTime.setText((model.getBedTime()));


            }
        }
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

        public waterViewholder(@NonNull View itemView) {
            super(itemView);

            weight = itemView.findViewById(R.id.tv_wtvalue);
            workout = itemView.findViewById(R.id.tv_wkvalue);
            wakeupTime = itemView.findViewById(R.id.tv_wkuptime);
            bedTime = itemView.findViewById(R.id.tv_slptime);
        }
    }
}
