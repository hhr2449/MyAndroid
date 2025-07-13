package com.java.huhaoran;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.java.huhaoran.R;

public class SearchActivity extends AppCompatActivity {

    //用于储存查询信息
    private SearchData searchData;

    //控件
    private EditText search_edittext;
    private TextView search_button;
    private ImageView icon_clear;
    private ImageView back;
    //类别搜索栏
    private GridView category_gridview;

    private void getViews() {
        search_edittext = findViewById(R.id.search_edittext);
        search_button = findViewById(R.id.btn_search_text);
        icon_clear = findViewById(R.id.icon_clear);
        back = findViewById(R.id.back_button);
        category_gridview = findViewById(R.id.categories_choose);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        //获取控件
        getViews();

        //构造查询信息
        searchData = new SearchData();

        //设置返回键
        back.setOnClickListener(v -> finish());

        //注册删除按键的点击事件
        icon_clear.setOnClickListener(v -> {
           search_edittext.setText("");
        });

        //监听输入框的输入，从而决定是否显示删除图标
        search_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            //当文字发生改变的时候
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //设置删除图标是否可见
                //如果搜索框文字不为空（s不为空），则设置为可见，否则不可见
                ImageView icon_clear = findViewById(R.id.icon_clear);
                if(!TextUtils.isEmpty(s)) {
                    icon_clear.setVisibility(View.VISIBLE);
                }
                else {
                    icon_clear.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        //设置类别搜索的界面
        SearchCategoryAdapter searchCategoryAdapter = new SearchCategoryAdapter(this);
        category_gridview.setAdapter(searchCategoryAdapter);
        //传入接口对象
        searchCategoryAdapter.setOnCategoryTransferListener(new OnCategoryTransferListener() {
            @Override
            public void onCategoryTransfer(String tag, boolean isChoosed) {
                //如果被选中，则加入set，否则删除
                if(isChoosed) {
                    searchData.addCategory(tag);
                }
                else {
                    searchData.deleteCategory(tag);
                }
            }
        });


        //当点下搜索键的时候获取搜索输入，并且跳转到搜索结果页面
        search_button.setOnClickListener(v -> {
            //设置输入的关键词
            searchData.setKeyword(((EditText) findViewById(R.id.search_edittext)).getText().toString().trim());
            //类别已经添加


            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
            intent.putExtra("searchData", searchData);
            startActivity(intent);
        });


    }
}