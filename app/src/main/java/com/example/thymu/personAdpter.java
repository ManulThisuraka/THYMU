package com.example.thymu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
class personAdapter extends FirebaseRecyclerAdapter<person, personAdapter.personsViewholder> {

    public personAdapter(
            @NonNull FirebaseRecyclerOptions<person> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull person model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.firstname.setText(model.getFirstname());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.lastname.setText(model.getLastname());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.age.setText(String.valueOf(model.getAge()));

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_person, parent, false);
        return new personAdapter.personsViewholder(view);
    }

    // Sub Class to create references of the views in Card
    // view (here "person.xml")
    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView firstname, lastname, age;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            firstname= itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            age = itemView.findViewById(R.id.age);
        }
    }
}
