package com.java.huhaoran;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.java.huhaoran.R;
import com.github.chrisbanes.photoview.PhotoView;

public class PictureDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_picture_display);

        PhotoView picture = findViewById(R.id.picture);
        String url = getIntent().getStringExtra("image");
        if(url != null) {
            Glide.with(this).load(url).into(picture);
        }
        //设置返回，只要点击屏幕中任何一个地方就返回
        ConstraintLayout main = findViewById(R.id.main);
        main.setOnClickListener(v -> finish());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}