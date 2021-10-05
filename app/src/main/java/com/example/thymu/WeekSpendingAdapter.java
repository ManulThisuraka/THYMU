package com.example.thymu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeekSpendingAdapter extends RecyclerView.Adapter<WeekSpendingAdapter.ViewHolder> {

    private Context mContext;
    private List<Fact> myFactList;

    public WeekSpendingAdapter(Context mContext, List<Fact> myFactList) {
        this.mContext = mContext;
        this.myFactList = myFactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.retrieve_layout_spending, parent,false);
        return new WeekSpendingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Fact fact = myFactList.get(position);

        holder.item.setText("Item: "+ fact.getItem());
        holder.amount.setText("Amount: "+ fact.getAmount());
        holder.date.setText("On "+ fact.getDate());
        holder.notes.setText("Note: "+ fact.getNotes());


        switch (fact.getItem()){
            case "Transport":
                holder.imageView.setImageResource(R.drawable.ic_transport);
                break;
            case "Food":
                holder.imageView.setImageResource(R.drawable.ic_food);
                break;
            case "House":
                holder.imageView.setImageResource(R.drawable.ic_house);
                break;
            case "Entertainment":
                holder.imageView.setImageResource(R.drawable.ic_entertainment);
                break;
            case "Education":
                holder.imageView.setImageResource(R.drawable.ic_education);
                break;
            case "Charity":
                holder.imageView.setImageResource(R.drawable.ic_consultancy);
                break;
            case "Apparel":
                holder.imageView.setImageResource(R.drawable.ic_shirt);
                break;
            case "Health":
                holder.imageView.setImageResource(R.drawable.ic_health);
                break;
            case "Personal":
                holder.imageView.setImageResource(R.drawable.ic_personalcare);
                break;
            case "Other":
                holder.imageView.setImageResource(R.drawable.ic_other);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return myFactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView item, amount, date, notes;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);
            notes = itemView.findViewById(R.id.note);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
