package com.java.huhaoran;

import static com.java.huhaoran.MainActivity.newsCache;

import static java.lang.Math.max;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;


public class SearchResultActivity extends AppCompatActivity {

    //相关的搜索数据
    private SearchData searchData;
    private String[] keywords;
    private String[] categories;
    private String startDate;
    private String endDate;
    //控件
    private TextView search_text;
    private TextView search_button;
    private ImageView icon_clear;
    private ImageView backButton;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    private int currentPage = 1;

    //记录最新日期的新闻最大页数
    private int newDayPageLimit = 0;

    //记录最新日期（因为垃圾的后端接口，所以获取的新闻最新日期不一定是当天）
    private String newDay = null;

    //是否正在加载
    //防止在开新的线程获取数据的时候又请求新的数据，
    // 只要请求数据的线程没有结束就是true，此时禁止再次请求数据
    boolean isLoading = false;





    //获取控件
    private void getViewById() {
        search_text = findViewById(R.id.search_text);
        search_button = findViewById(R.id.btn_search_text);
        icon_clear = findViewById(R.id.icon_clear);
        backButton = findViewById(R.id.back_button);
        recyclerView = findViewById(R.id.news_list);
    }

    //分类获取搜索数据
    private void getSearchData() {
        //获取搜索数据
        searchData = (SearchData) getIntent().getSerializableExtra("searchData");
        //做判空处理
        if(searchData == null) {
            keywords = new String[]{};
            categories = new String[0];
            startDate = null;
            endDate = null;
        }
        //用户的输入
        String rawKeywords = searchData.getKeyword();
        if (rawKeywords != null && !rawKeywords.trim().isEmpty()) {
            keywords = SearchData.simpleChineseSegment(rawKeywords).toArray(new String[0]);
        } else {
            keywords = new String[]{};
        }

        //类别
        if(searchData.getCategories() != null) {
            categories = searchData.getCategories().toArray(new String[0]);
            if(categories.length == 1 && categories[0].equals("全部")) {
                categories = null;
            }
        }
        else {
            categories = null;
        }

        if(searchData.getStartDate() == null) {
            startDate = "";
        }
        else {
            startDate = searchData.getStartDate();
        }

        if (searchData.getEndDate() == null) {
            endDate = "";
        }
        else {
            endDate = searchData.getEndDate();
        }
    }

    private void showSearchDataSummary() {
        search_text.setText(searchData.getKeyword());
        TextView categorySummary = findViewById(R.id.search_category_summary);
        TextView timeSummary = findViewById(R.id.search_time_summary);
        // 设置分类摘要（用逗号隔开）
        if (categories != null && categories.length > 0) {
            StringBuilder categoryText = new StringBuilder("分类：");
            for (int i = 0; i < categories.length; i++) {
                categoryText.append(categories[i]);
                if (i < categories.length - 1) {
                    categoryText.append("，");
                }
            }
            categorySummary.setText(categoryText.toString());
        } else {
            categorySummary.setText("分类：全部");
        }

        // 设置时间摘要
        if (startDate != null && endDate != null) {
            timeSummary.setText("时间：" + startDate + " ~ " + endDate);
        } else if (startDate != null) {
            timeSummary.setText("时间：自 " + startDate + " 起");
        } else if (endDate != null) {
            timeSummary.setText("时间：截至 " + endDate);
        } else {
            timeSummary.setText("时间：不限");
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_result);
        //获取控件
        getViewById();
        //获取搜索数据
        getSearchData();
        //设置返回框的点击事件
        backButton.setOnClickListener(v -> finish());
        //展示搜索信息
        showSearchDataSummary();

        //设置搜索框的点击事件，点击后会进入搜索界面，同时保留原先的输入结果
        search_text.setOnClickListener(v -> {
            Intent intent = new Intent(SearchResultActivity.this, SearchActivity.class);
            intent.putExtra("searchData", searchData);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        //搜索结果的新闻列表，仍然可以使用recyclerView来滚动展示
        //这里可以直接重用之前的主界面的recyclerView的adapter
        newsAdapter = new NewsAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addItemDecoration(new TabNewsFragment.SimpleDividerDecoration(this));
        // 初始化 SmartRefreshLayout
        initRefreshLayout();
        loadNewsData(true);

    }


    //加载更多数据
    private void loadMoreNewsData() {
        // 防止重复加载
        if (isLoading) return;
        isLoading = true;


        // 从网络请求下一页数据
        new Thread(() -> {
            // 递增页码
            currentPage++;
            FetchNews.NewsResponse response = FetchNews.fetchNews("10", startDate, endDate, keywords, categories, String.valueOf(currentPage)); // 使用 currentPage

            if (response != null && response.data != null&& !response.data.isEmpty()) {
                String time[] = response.data.get(0).publishTime.split(" ");
                if(newDay != null &&newDay.equals(time[0])) {
                    newDayPageLimit = max(newDayPageLimit, currentPage);
                }
                List<FetchNews.NewsItem> newslist = response.data;

                //更新ui界面（因为当前在一个新线程，不在主线程中是无法直接更新界面的，需要使用runOnUiThread）
                runOnUiThread(() -> {
                    // RecyclerView 相关更新
                    newsAdapter.appendData(newslist);
                    RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
                    refreshLayout.finishLoadMore(); // 或 finishRefresh()
                    isLoading = false;
                });
            } else {
                //没有更多数据了
                runOnUiThread(() -> {
                    Toast.makeText(SearchResultActivity.this, "没有更多新闻了", Toast.LENGTH_SHORT).show();
                    RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
                    refreshLayout.finishLoadMore(); // 或 finishRefresh()
                    isLoading = false;
                });

            }
        }).start();
    }


    //刷新数据
    //更新了刷新逻辑，如果每次刷新都回到第一页的话显得重复，效果不好
    //所以我记录了有当前日期新闻的界面，如果刷新时的page是当天的新闻，则获取下一页
    //如果不是，则回到有当前日期新闻的随机一页

    private void loadNewsData(boolean isRefresh) {
        // 防止重复加载
        if (isLoading) return;
        isLoading = true;

        new Thread(() -> {
            FetchNews.NewsResponse response = FetchNews.fetchNews("10", startDate, endDate, keywords, categories, String.valueOf(currentPage)); // 使用 currentPage


            if (response != null && response.data != null&& !response.data.isEmpty()) {

                String time[] = response.data.get(0).publishTime.split(" ");
                if(currentPage == 1) {
                    newDay = time[0];
                }


                if(newDay != null && newDay.equals(time[0])) {
                    newDayPageLimit = max(currentPage, newDayPageLimit);
                } else {
                    currentPage = (int)(Math.random()*newDayPageLimit+1);
                    response = FetchNews.fetchNews("10", startDate, endDate, keywords, categories, String.valueOf(currentPage)); // 使用 currentPage
                }
                currentPage++;
                List<FetchNews.NewsItem> newslist = response.data;

                runOnUiThread(() -> {
                    // RecyclerView 相关更新
                    newsAdapter.updateData(newslist);
                    RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
                    if (isRefresh) {
                        refreshLayout.finishRefresh();
                    } else {
                        refreshLayout.finishLoadMore();
                    }
                    isLoading = false;
                });
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(SearchResultActivity.this, "没有更多新闻了", Toast.LENGTH_SHORT).show();
                    isLoading = false;
                    RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
                    if (isRefresh) {
                        refreshLayout.finishRefresh();
                    } else {
                        refreshLayout.finishLoadMore();
                    }
                });
            }
        }).start();
    }

    private void initRefreshLayout() {
        RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);

        // 下拉刷新
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            loadNewsData(true); // 加载第一页
        });

        // 上拉加载更多
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            // 加载下一页
            loadMoreNewsData();
        });
    }
}