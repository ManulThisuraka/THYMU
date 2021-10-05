package com.example.thymu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.MutableDateTime;
import org.joda.time.Weeks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class MasterActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView weekSpendingTv, todaySpendingTv, monthSpendingTv;

    private CardView todayCardView, weekCardView, monthCardView, historyCardView;


    private FirebaseAuth mAuth;
    private DatabaseReference spendingRef;
    private String onlineUserID = "";

    private int totalAmountMonth = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spending Tracker App");

        todaySpendingTv = findViewById(R.id.todaySpendingTv);
        weekSpendingTv = findViewById(R.id.weekSpendingTv);
        monthSpendingTv = findViewById(R.id.monthSpendingTv);

        mAuth = FirebaseAuth.getInstance();
        onlineUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        spendingRef = FirebaseDatabase.getInstance().getReference("spending").child(onlineUserID);


        todayCardView = findViewById(R.id.todayCardView);
        todayCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MasterActivity.this, TodaySpendingActivity.class);
                startActivity(intent);
            }
        });


        monthCardView = findViewById(R.id.monthCardView);
        monthCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MasterActivity.this, WeekSpendingActivity.class);
                intent.putExtra("type", "month");
                startActivity(intent);
            }
        });

        weekCardView = findViewById(R.id.weekCardView);
        weekCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MasterActivity.this, WeekSpendingActivity.class);
                intent.putExtra("type", "week");
                startActivity(intent);
            }
        });

        historyCardView = findViewById(R.id.historyCardView);
        historyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MasterActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });



        getTodaySpentAmount();
        getWeekSpentAmount();
        getMonthSpentAmount();

    }


    private void getTodaySpentAmount() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("spending").child(onlineUserID);
        Query query = reference.orderByChild("date").equalTo(date);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int totalAmount = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object total = map.get("amount");
                    int pTotal = Integer.parseInt(String.valueOf(total));
                    totalAmount += pTotal;
                    todaySpendingTv.setText("$ " + totalAmount);
                }
                spendingRef.child("today").setValue(totalAmount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MasterActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getMonthSpentAmount() {
        MutableDateTime epoch = new MutableDateTime();
        epoch.setDate(0); //Set to Epoch time
        DateTime now = new DateTime();
        Months months = Months.monthsBetween(epoch, now);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("spending").child(onlineUserID);
        Query query = reference.orderByChild("month").equalTo(months.getMonths());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int totalAmount = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object total = map.get("amount");
                    int pTotal = Integer.parseInt(String.valueOf(total));
                    totalAmount += pTotal;
                    monthSpendingTv.setText("$ " + totalAmount);

                }
                spendingRef.child("month").setValue(totalAmount);
                totalAmountMonth = totalAmount;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getWeekSpentAmount() {
        MutableDateTime epoch = new MutableDateTime();
        epoch.setDate(0); //Set to Epoch time
        DateTime now = new DateTime();
        Weeks weeks = Weeks.weeksBetween(epoch, now);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("spending").child(onlineUserID);
        Query query = reference.orderByChild("week").equalTo(weeks.getWeeks());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int totalAmount = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object total = map.get("amount");
                    int pTotal = Integer.parseInt(String.valueOf(total));
                    totalAmount += pTotal;
                    weekSpendingTv.setText("$ " + totalAmount);
                }
                spendingRef.child("week").setValue(totalAmount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

