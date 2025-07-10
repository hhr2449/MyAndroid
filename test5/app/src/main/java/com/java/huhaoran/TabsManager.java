package com.java.huhaoran;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.java.huhaoran.R;

//用于标签栏的增删改查
public class TabsManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tabs_manager);
        GridView gridTop = findViewById(R.id.grid_top);
        GridView gridBottom = findViewById(R.id.grid_bottom);
        gridTop.setAdapter(new TitlesAdapter(this, MainActivity.titles, true));
        gridBottom.setAdapter(new TitlesNoUseAdapter(this, MainActivity.titlesNoUse, true));

    }
}