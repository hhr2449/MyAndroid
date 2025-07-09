package com.java.huhaoran;

import static com.java.huhaoran.MainActivity.newsCache;

import android.content.Context;
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

import java.util.List;


public class TabNewsFragment extends Fragment {

    //因为Bundle储存数据是使用键值对的，所以需要设置常量参数作为key，实际想要传递的数据作为 value
    //我们需要一个参数就是页面的主题
    private static final String ARG_PARAM = "title";

    // TODO: Rename and change types of parameters
    private String title;

    public TabNewsFragment() {

    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.news_tab_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        //----------------------------该类用于实现RecyclerView的分割线-----------------------
        class SimpleDividerDecoration extends RecyclerView.ItemDecoration {

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


        //----------------------------实现数据的加载-----------------------

        //如果有缓存数据，就不开线程去请求了
        if(newsCache.containsKey(title)) {
            List<FetchNews.NewsItem> responseData = newsCache.get(title);
            if (responseData != null) {
                if (!isAdded() || getActivity() == null) return; // 添加这个判断
                requireActivity().runOnUiThread(() -> {
                    if (!isAdded() || getActivity() == null) return; // 再保险地判断一次
                    NewsAdapter adapter = new NewsAdapter(responseData);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new SimpleDividerDecoration(getContext()));
                });
            }
            return;
        }
        // 开线程请求数据
        new Thread(() -> {
            //根据newInstance时传入的title，请求数据
            String title_tmp = new String();
            if(title.equals("全部")) {
                title_tmp = "";
            }
            else {
                title_tmp = title;
            }
            FetchNews.NewsResponse response = FetchNews.fetchNews("15", "", "", new String[]{}, title_tmp, "1");
            if (response != null && response.data != null) {
                // 缓存数据
                newsCache.put(title, response.data);
                if (!isAdded() || getActivity() == null) return; // 添加这个判断
                requireActivity().runOnUiThread(() -> {
                    if (!isAdded() || getActivity() == null) return; // 再保险地判断一次
                    NewsAdapter adapter = new NewsAdapter(response.data);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new SimpleDividerDecoration(getContext()));
                });
            }

        }).start();
        //----------------------------实现数据的加载-----------------------
    }
}