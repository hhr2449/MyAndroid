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

//AppCompatActivity提供向后兼容的界面，并且提供了一些方法来帮助开发者实现Material Design。
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //调用父类的初始化方法
        super.onCreate(savedInstanceState);
        //启用边缘到边缘显示支持（全屏效果）
        EdgeToEdge.enable(this);
        //显示主界面视图
        setContentView(R.layout.activity_main);
        //上面三行代码进行了ui界面的初始化
        onResume();
        //设置一个按钮控件，并且设置点击事件监听器，点击后跳转到下一个界面
        Button nextButton = findViewById(R.id.activity_next_button);
        nextButton.setOnClickListener(v -> {
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
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enterfromright, R.anim.existfromleft);
        });
        //下面是设置状态栏和导航栏的padding，防止被系统ui遮挡，这个不影响主进程的运行
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
}

    @Override
    protected void onResume() {
        //调用onResume表示当前界面被激活，可以与用户进行交互
        super.onResume();
    }



}