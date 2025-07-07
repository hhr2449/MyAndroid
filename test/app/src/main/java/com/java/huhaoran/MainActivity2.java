package com.java.huhaoran;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        onResume();
        Button nextbutton = findViewById(R.id.activity2_next_button);
        nextbutton.setOnClickListener(v -> {
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
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enterfromright, R.anim.existfromleft);
        });
        Button backbutton = findViewById(R.id.activity2_back_button);
        backbutton.setOnClickListener(v -> {
            //缩放动画
            // 设置缩放动画
            ScaleAnimation scale = new ScaleAnimation(
                    1.0f, 0.95f,
                    1.0f, 0.95f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            scale.setDuration(150);
            v.startAnimation(scale);
            //跳转
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enterfromleft, R.anim.existfromright);
        });


    }

}
