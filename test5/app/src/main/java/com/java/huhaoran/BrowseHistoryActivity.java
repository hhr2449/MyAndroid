package com.java.huhaoran;



import static java.lang.Math.max;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.java.huhaoran.note.BrowseHistoryNote;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.java.huhaoran.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BrowseHistoryActivity extends AppCompatActivity {

    boolean isLoading = false;
    boolean isEdit = false;
    private int currentPage = 1;
    //控件
    private TextView deleteButton;
    private TextView clearButton;
    private ImageView backButton;
    private RecyclerView recyclerView;
    private HistoryItemAdapter browseHistoryAdapter;


    //获取控件
    private void getViewById() {
        clearButton = findViewById(R.id.clear_button_browse);
        deleteButton = findViewById(R.id.delete_button_browse);
        backButton = findViewById(R.id.back_button_browse);
        recyclerView = findViewById(R.id.browse_list);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browse_history);

        //获取控件
        getViewById();
        //设置返回框的点击事件
        backButton.setOnClickListener(v -> finish());

        //创建适配器
        browseHistoryAdapter = new HistoryItemAdapter(new ArrayList<>(), false, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(browseHistoryAdapter);
        recyclerView.addItemDecoration(new TabNewsFragment.SimpleDividerDecoration(this));

        //初始化界面
        initView();





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //用于加载历史数据
    //这里的逻辑应该是从数据库中进行分页请求，然后页数一直增加，直到没有更多数据为止（不用考虑日期等）
    private void loadMoreNewsData() {
        // 防止重复加载
        if (isLoading) return;
        isLoading = true;


        // 从数据库请求下一页数据
        new Thread(() -> {

            List<BrowseHistoryNote> browseHistoryNotes = AppDatabase.getInstance(this).browseHistoryDao().getBrowseHistoryPage(10, (currentPage - 1) * 10);
            // 递增页码
            currentPage++;

            if (browseHistoryNotes != null && browseHistoryNotes.size() > 0) {
                List<FetchNews.NewsItem> newslist = TransferHistoryToNewsItem.transfer(browseHistoryNotes);

                //更新ui界面（因为当前在一个新线程，不在主线程中是无法直接更新界面的，需要使用runOnUiThread）
                runOnUiThread(() -> {
                    // RecyclerView 相关更新
                    browseHistoryAdapter.appendData(newslist);
                    RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
                    refreshLayout.finishLoadMore(); // 或 finishRefresh()
                    isLoading = false;
                });
            } else {
                //没有更多数据了
                runOnUiThread(() -> {
                    Toast.makeText(BrowseHistoryActivity.this, "没有更多记录了", Toast.LENGTH_SHORT).show();
                    RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
                    refreshLayout.finishLoadMore(); // 或 finishRefresh()
                    isLoading = false;
                });

            }
        }).start();
    }

    private void initView() {
        //加载数据
        loadMoreNewsData();
        RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        // 上拉加载更多
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            // 加载下一页
            loadMoreNewsData();
        });

        //删除按钮的点击事件
        deleteButton.setOnClickListener(v -> {
            //如果点击是是编辑模式，则进行删除
            if(isEdit) {
                //获取要删除新闻的标题
                HashSet<String> titlesToRemove = browseHistoryAdapter.getTitlesToRemove();

                if (!titlesToRemove.isEmpty()) {
                    // 弹出确认对话框
                    new androidx.appcompat.app.AlertDialog.Builder(this)
                            .setTitle("确认删除")
                            .setMessage("确定要删除选中的 " + titlesToRemove.size() + " 条记录吗？\n 删除后无法恢复")
                            .setPositiveButton("删除", (dialog, which) -> {
                                new Thread(() -> {
                                    AppDatabase db = AppDatabase.getInstance(this);
                                    // 优化：批量删除
                                    db.browseHistoryDao().deleteByTitles(new ArrayList<>(titlesToRemove));

                                    runOnUiThread(() -> {
                                        browseHistoryAdapter.removeItemsByTitle(titlesToRemove);
                                        Toast.makeText(this, "已删除选中记录", Toast.LENGTH_SHORT).show();
                                    });
                                }).start();
                            })
                            .setNegativeButton("取消", null)
                            .show();
                }


            }

            isEdit = !isEdit;
            //改变编辑模式
            browseHistoryAdapter.setEditMode(isEdit);
            //改变按键的文字
            deleteButton.setText(isEdit ? "完成" : "编辑");

        });

        //清空按钮的点击事件
        clearButton.setOnClickListener(v -> {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("确认清空")
                    .setMessage("确认要清空所有记录吗,\n 清空后无法恢复")
                    .setPositiveButton("清空", (dialog, which) -> {
                        new Thread(() -> {
                            AppDatabase db = AppDatabase.getInstance(this);
                            db.browseHistoryDao().deleteAll();

                            runOnUiThread(() -> {
                                browseHistoryAdapter.updateData(new ArrayList<>());
                                Toast.makeText(this, "已清空所有记录", Toast.LENGTH_SHORT).show();
                            });
                        }).start();
                    })
                .setNegativeButton("取消", null)
                .show();
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1004 && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            browseHistoryAdapter.refreshSingleNews(title);
        }
    }
}