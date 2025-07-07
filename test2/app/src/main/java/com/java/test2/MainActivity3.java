package com.java.test2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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
        Button nextButton = findViewById(R.id.nextbutton3);
        nextButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity4.class));
        });
        Button backButton = findViewById(R.id.backbutton3);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity2.class));
        });
        ImageButton Imagebutton = findViewById(R.id.imagebutton3);
        Imagebutton.setOnClickListener(v -> {
            TextView textView = findViewById(R.id.textview3);
            textView.setText("开根号");
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}