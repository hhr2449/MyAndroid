package com.java.huhaoran;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.java.huhaoran.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    //是否从搜索结果界面跳转
    private boolean fromResult = false;

    //用于选择搜索日期
    //思路是设置两个日期框，点击就会弹出calendar，选择日期
    TextView textStartDate, textEndDate;
    Calendar calendar;

    //清除日期
    ImageView clear_date;

    private void getViews() {
        search_edittext = findViewById(R.id.search_edittext);
        search_button = findViewById(R.id.btn_search_text);
        icon_clear = findViewById(R.id.icon_clear);
        back = findViewById(R.id.back_button);
        category_gridview = findViewById(R.id.categories_choose);
        textStartDate = findViewById(R.id.text_start_date);
        textEndDate = findViewById(R.id.text_end_date);
        clear_date = findViewById(R.id.clear_date);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        //获取控件
        getViews();

        //如果是从搜索结果界面跳转的话
        if(getIntent().getSerializableExtra("searchData") != null) {
            //直接重用原本的搜索信息
            searchData = (SearchData) getIntent().getSerializableExtra("searchData");
            search_edittext.setText(searchData.getKeyword());
            String startdate = searchData.getStartDate() == null ? "开始日期" : searchData.getStartDate();
            textStartDate.setText(startdate);
            String enddate = searchData.getEndDate() == null ? "结束日期" : searchData.getEndDate();
            textEndDate.setText(enddate);
            fromResult = true;
        }
        else {
            //否则新建一个搜索信息
            searchData = new SearchData();
        }


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
        SearchCategoryAdapter searchCategoryAdapter = new SearchCategoryAdapter(this, fromResult, searchData.getCategories());
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

        //选择日期
        //获取Calender类的实例，用来获取当前日期
        calendar = Calendar.getInstance();
        //两个文本框的点击事件
        textStartDate.setOnClickListener(v -> showDatePickerDialog(true));
        textEndDate.setOnClickListener(v -> showDatePickerDialog(false));

        //清空日期
        clear_date.setOnClickListener(v -> {
            searchData.setStartDate(null);
            searchData.setEndDate(null);
            textStartDate.setText("开始日期");
            textEndDate.setText("结束日期");
        });



        //当点下搜索键的时候获取搜索输入，并且跳转到搜索结果页面
        search_button.setOnClickListener(v -> {
            //设置输入的关键词
            searchData.setKeyword(((EditText) findViewById(R.id.search_edittext)).getText().toString().trim());
            //类别已经添加
            //时间已经添加
            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
            intent.putExtra("searchData", searchData);
            startActivity(intent);
        });


    }
    private void showDatePickerDialog(boolean isStartDate) {
        //获取当前的年，月，日
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //定义了一个DatePickerDialog控件
        //参数依次是：上下文，监听，默认年，月，日
        //监听器就是点击了日历中的确定会执行的代码
        //监听器的参数是当前的DatePicker对象，选择的年，月，日
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            //获取日期字符串，这里将年，月，日格式化为4位，2位，2位
            //注意月是0-11，所以要加1
            String date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year1, month1 + 1, dayOfMonth);
            //更新文本框
            if (isStartDate) {
                if(searchData.getEndDate() != null) {
                    //如果结束日期不为空，则判断是否大于开始日期
                    if(dateCompare(date, searchData.getEndDate()) > 0) {
                        //如果更大，则弹出提示
                        Toast.makeText(SearchActivity.this, "开始日期不能大于结束日期", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                textStartDate.setText(date);
                searchData.setStartDate(date);
            } else {
                if(searchData.getStartDate() != null) {
                    //如果开始日期不为空，则判断是否小于开始日期
                    if(dateCompare(date, searchData.getStartDate()) < 0) {
                        //如果更小，则弹出提示
                        Toast.makeText(SearchActivity.this, "结束日期不能小于开始日期", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                textEndDate.setText(date);
                searchData.setEndDate(date);
            }
        }, year, month, day);

        // 防止用户选未来
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());


        //展示日历
        datePickerDialog.show();
    }


    //日期比较
    //约定：格式一定是年-月-日
    //约定：date1 > date2，则返回1, date1 < date2，则返回-1, date1 = date2，则返回0
    private int dateCompare(String date1, String date2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            return d1.compareTo(d2); // d1 > d2 => 1, d1 < d2 => -1, d1 == d2 => 0
        } catch (ParseException e) {
            return -2; // 格式错误
        }
    }

}