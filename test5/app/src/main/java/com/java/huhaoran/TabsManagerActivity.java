package com.java.huhaoran;



import java.util.ArrayList;
import static com.java.huhaoran.MainActivity.titles;
import static com.java.huhaoran.MainActivity.titlesNoUse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


//用于标签栏的增删改查
public class TabsManagerActivity extends AppCompatActivity {

    private final String[] mode = new String[]{"编辑", "完成"};
    //0按钮显示为编辑，1按钮显示为完成
    private int modeIndex = 0;
    //是否为编辑模式
    private boolean editMode = false;
    private GridView gridTop;
    private GridView gridBottom;
    private TitlesAdapter titlesAdapter;
    private TitlesNoUseAdapter titlesNoUseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tabs_manager);
        gridTop = findViewById(R.id.grid_top);
        gridBottom = findViewById(R.id.grid_bottom);
        //默认进入的时候默认是非编辑模式
        titlesAdapter = new TitlesAdapter(this, titles, editMode);
        gridTop.setAdapter(titlesAdapter);
        titlesNoUseAdapter = new TitlesNoUseAdapter(this, titlesNoUse, editMode);
        gridBottom.setAdapter(titlesNoUseAdapter);
        ImageView btn_back = findViewById(R.id.btn_back);
        //设置点击事件，点击可以切换编辑模式（editMode取反），并切换按钮文字
        TextView button = findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMode = !editMode;
                modeIndex = editMode ? 1 : 0;
                button.setText(mode[modeIndex]);
                titlesAdapter.setEditMode(editMode);
                titlesNoUseAdapter.setEditMode(editMode);
                if(editMode) {
                    btn_back.setVisibility(View.GONE);
                }
                else {
                    btn_back.setVisibility(View.VISIBLE);
                }
            }
        });
        //在Activity中定义两个Adapter中的接口，实现交互
        titlesAdapter.setOnTitlesTransferListener(new OnTitlesTransferListener() {
            @Override
            public void onTitlesTransfer(String tag) {
                //改变两个list
                MainActivity.titles.remove(titles.indexOf(tag));
                MainActivity.titlesNoUse.add(tag);
                titlesAdapter.remove(titlesAdapter.titles.indexOf(tag));
                titlesNoUseAdapter.add(tag);
            }
        });

        titlesNoUseAdapter.setOnTitlesNoUseTransferListener(new OnTitlesNoUseTransferListener() {
            @Override
            public void onTitlesNoUseTransfer(String tag) {
                //改变两个list
                MainActivity.titles.add(tag);
                MainActivity.titlesNoUse.remove(titlesNoUse.indexOf(tag));
                titlesNoUseAdapter.remove(titlesNoUseAdapter.titlesNoUse.indexOf(tag));
                titlesAdapter.add(tag);
            }
        });
        //设置返回键

        btn_back.setOnClickListener(v -> {
            //设置在关闭页面时保存数据
            titles = new ArrayList<>(titlesAdapter.titles);
            titlesNoUse = new ArrayList<>(titlesNoUseAdapter.titlesNoUse);
            TabPreference tabPreference = new TabPreference(TabsManagerActivity.this);
            tabPreference.saveTabs(titles, titlesNoUse);
            //将请求的信息返回给MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("update_titles", new ArrayList<String>(titles));
            setResult(RESULT_OK, resultIntent);
            finish();
        });


    }

    //注意有时候用户并不会点击设置好的返回键，而是点击手机上的回退，所以要重载一下对手机回退键的监听
    @Override
    public void onBackPressed() {
        //设置在关闭页面时保存数据
        titles = new ArrayList<>(titlesAdapter.titles);
        titlesNoUse = new ArrayList<>(titlesNoUseAdapter.titlesNoUse);
        TabPreference tabPreference = new TabPreference(TabsManagerActivity.this);
        tabPreference.saveTabs(titles, titlesNoUse);
        //将请求的信息返回给MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("update_titles", new ArrayList<String>(titles));
        setResult(RESULT_OK, resultIntent);
        super.onBackPressed();
    }
}