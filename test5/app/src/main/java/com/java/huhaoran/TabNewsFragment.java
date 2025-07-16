package com.java.huhaoran;

import static android.app.Activity.RESULT_OK;
import static com.java.huhaoran.MainActivity.newsCache;

import static java.lang.Math.max;
import static java.lang.StrictMath.random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TabNewsFragment extends Fragment {

    //因为Bundle储存数据是使用键值对的，所以需要设置常量参数作为key，实际想要传递的数据作为 value
    //我们需要一个参数就是页面的主题
    private static final String ARG_PARAM = "title";

    //!!!注意TabNewsFragment的一个类对象就对应一个fragment，所以可以将页面的数据作为成员变量进行储存
    private String title;

    //当前页面的页数
    private int currentPage = 1;

    //记录最新日期的新闻最大页数
    private int newDayPageLimit = 0;

    //记录最新日期（因为垃圾的后端接口，所以获取的新闻最新日期不一定是当天）
    private String newDay = null;


    //是否正在加载
    //防止在开新的线程获取数据的时候又请求新的数据，
    // 只要请求数据的线程没有结束就是true，此时禁止再次请求数据
    boolean isLoading = false;

    // RecyclerView 和 Adapter,因为在同一个界面中，recyclerView和adapter是同一个对象
    //所以直接将其作为成员变量引用，避免重复创建
    //NewsAdapter中提供了appendData()方法，将数据追加到adapter中，这样每次需要增加数据的时候可以直接添加进去,同时更新页面
    //提供了updateData()方法，在刷新的时候可以直接调用，同时更新页面
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    public TabNewsFragment() {

    }

    //----------------------------该类用于实现RecyclerView的分割线-----------------------
    static class SimpleDividerDecoration extends RecyclerView.ItemDecoration {

        private int dividerHeight;
        private Paint dividerPaint;

        public SimpleDividerDecoration(Context context) {
            dividerPaint = new Paint();
            dividerPaint.setColor(context.getResources().getColor(R.color.colorAccent));
            dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        }


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = dividerHeight;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int childCount = parent.getChildCount();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            for (int i = 0; i < childCount - 1; i++) {
                View view = parent.getChildAt(i);
                float top = view.getBottom();
                float bottom = view.getBottom() + dividerHeight;
                c.drawRect(left, top, right, bottom, dividerPaint);
            }
        }
    }
    //----------------------------该类用于实现RecyclerView的分割线-----------------------


    public static TabNewsFragment newInstance(String title) {
        //newInstance()方法返回一个TabNewsFragment对象
        TabNewsFragment fragment = new TabNewsFragment();
        //Bundle类是Android中用于在Activity之间传递数据的类
        /*
        ①putXxx(String key,Xxx value):Xxx表示一系列的数据类型，
        比如String,int,float,Parcelable，Serializable等类型，
        以键-值对形式保存数据。
        ②getXxx(String key):根据key值获取Bundle中的数据。
        */
        //创建Bundle对象，将参数传入并且使用setArguments()方法将Bundle对象与Fragment对象关联起来
        //Bundle对象的作用在于，当旋转屏幕等情况下，Fragment需要销毁后再重新创建，此时会保存Bundle对象用于重建
        //如果直接赋值给成员变量，那么销毁的时候数据就会丢失
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    //生命周期开始，注意onCreate()方法是系统自动调用的，
    // newInstance()方法只是预先创建对象并且传入参数，
    // 等到需要页面的时候其返回的TabNewsFragment对象会自动调用这个方法创建页面
    public void onCreate(Bundle savedInstanceState) {
        //调用父类的onCreate()方法，初始化页面
        super.onCreate(savedInstanceState);
        //将之前的参数传入
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM);
        }
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
            //开线程请求数据
            String title_tmp = title.equals("全部") ? "" : title;
            FetchNews.NewsResponse response = FetchNews.fetchNews("10", "1900-01-01", "", new String[]{}, new String[]{title_tmp}, String.valueOf(currentPage)); // 使用 currentPage


            if (response != null && response.data != null&& !response.data.isEmpty()) {
                String time[] = response.data.get(0).publishTime.split(" ");
                if(newDay != null &&newDay.equals(time[0])) {
                    newDayPageLimit = max(newDayPageLimit, currentPage);
                }


                //缓存数据
                // 确保 newsCache 中有该 title 的列表
                if (!newsCache.containsKey(title)) {
                    newsCache.put(title, new ArrayList<>());
                }
                newsCache.get(title).addAll(response.data);

                //更新ui界面（因为当前在一个新线程，不在主线程中是无法直接更新界面的，需要使用runOnUiThread）
                requireActivity().runOnUiThread(() -> {
                    //增加数据
                    newsAdapter.appendData(response.data);
                    RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);
                    refreshLayout.finishLoadMore(); // 结束加载动画
                    isLoading = false; // 允许下次加载
                });
            } else {
                requireActivity().runOnUiThread(() -> {
                    if (getView() != null) {
                        RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);
                        refreshLayout.finishLoadMore(false); // 加载失败
                    }
                    isLoading = false; // 允许下次加载
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
            String title_tmp = title.equals("全部") ? "" : title;
            FetchNews.NewsResponse response = FetchNews.fetchNews("10", "2000-01-01", "", new String[]{}, new String[]{title_tmp}, String.valueOf(currentPage)); // 使用 currentPage


            if (response != null && response.data != null&& !response.data.isEmpty()) {

                String time[] = response.data.get(0).publishTime.split(" ");
                if(currentPage == 1) {
                    newDay = time[0];
                }


                if(newDay != null && newDay.equals(time[0])) {
                    newDayPageLimit = max(currentPage, newDayPageLimit);
                } else {
                    currentPage = (int)(Math.random()*newDayPageLimit+1);
                    response = FetchNews.fetchNews("10", "2000-01-01", "", new String[]{}, new String[]{title_tmp}, String.valueOf(currentPage)); // 使用 currentPage
                }
                currentPage++;
                List<FetchNews.NewsItem> newslist = response.data;

                newsCache.put(title, response.data); // 缓存数据
                requireActivity().runOnUiThread(() -> {
                    newsAdapter.updateData(newslist); // 更新适配器数据
                    RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);
                    refreshLayout.finishRefresh(); // 结束刷新动画
                });
                isLoading = false;
            } else {
                requireActivity().runOnUiThread(() -> {
                    if (isRefresh) {
                        RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);
                        refreshLayout.finishRefresh(false); // 刷新失败
                    }
                });
                isLoading = false;
            }
        }).start();
    }


    //设置事件，每当上拉或是下拉就会触发事件从而进行更新或是获取更多数据
    private void initRefreshLayout() {
        RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);

        // 下拉刷新
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            newsCache.remove(title); // 移除缓存
            loadNewsData(true); // 加载第一页
        });

        // 上拉加载更多
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            // 加载下一页
            loadMoreNewsData();
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 使用 SmartRefreshLayout 的布局
        View view = inflater.inflate(R.layout.fragment_tab_news, container, false);
        recyclerView = view.findViewById(R.id.news_tab_recycler);
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);

        // 设置 RecyclerView 的 LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 初始化 Adapter，初始为空列表
        newsAdapter = new NewsAdapter(new ArrayList<>());
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addItemDecoration(new SimpleDividerDecoration(getContext()));

        // 返回视图
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 初始化 SmartRefreshLayout
        initRefreshLayout();

        if(newsCache.containsKey(title)) {
            newsAdapter.updateData(newsCache.get(title));
        }
        else {
            // 首次加载数据（无缓存时）
            loadNewsData(true);
        }

    }


    public void refreshSingleNews(String title) {
        int position = newsAdapter.getPosByTitle(title);
        if (newsAdapter != null) {
            newsAdapter.notifyItemChanged(position);
        }
    }
}