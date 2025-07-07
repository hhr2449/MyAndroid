package com.java.huhaoran;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        Button backbutton = findViewById(R.id.activity3_back_button);
        backbutton.setOnClickListener(v -> {
            // 设置缩放动画（带回弹）
            ScaleAnimation scale = new ScaleAnimation(
                    1.0f, 0.8f,
                    1.0f, 0.8f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            scale.setDuration(150);
            scale.setRepeatCount(1);
            scale.setRepeatMode(Animation.REVERSE);

            v.startAnimation(scale);
            //跳转
            Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(intent);
            //跳转动画
            overridePendingTransition(R.anim.enterfromleft, R.anim.existfromright);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}