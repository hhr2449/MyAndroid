package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    //TextView 用于文字显示
    private TextView textViewCounter;
    private TextView congratulation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取按钮和文本视图的引用
        //通过id将布局文件中的控件进行引用，从而可以通过这里的对象来管理控件
        // R是构建工具自动生成的资源索引类，存放了项目中的所有资源，R.id则是
        //一个内部类，用于存放控件的id
        Button buttonIncrement = findViewById(R.id.buttonIncrement);
        textViewCounter = findViewById(R.id.textViewCounter);
        congratulation = findViewById(R.id.congratulation);

        // 设置按钮的点击事件
        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 每次点击按钮时，计数器加 1 并更新文本视图
                counter++;
                textViewCounter.setText(String.valueOf(counter));
                if(counter % 10 == 0) {
                    congratulation.setText("恭喜你，你已点击\n" + counter + "次");
                }
            }
        });
    }
}
