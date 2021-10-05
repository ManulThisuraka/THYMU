package com.example.thymu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    Animation animation;
    private TextView txv_welcome;
    private ImageView img_welcome;
    private TextView txv_outflows;
    private Button btn_start;

    public SplashScreenActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        animation = AnimationUtils.loadAnimation(this, R.anim.animation);

        txv_welcome = findViewById(R.id.txv_welcome);
        img_welcome = findViewById(R.id.img_welcome);
        txv_outflows = findViewById(R.id.txv_outflows);
        btn_start = findViewById(R.id.btn_start);

        txv_welcome.setAnimation(animation);
        img_welcome.setAnimation(animation);
        txv_outflows.setAnimation(animation);
        btn_start.setAnimation(animation);

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreenActivity.this, MasterActivity.class);
                startActivity(intent);
            }
        });
    }
}
