package com.java.huhaoran;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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

        // 开线程请求数据
        new Thread(() -> {
            //根据newInstance时传入的title，请求数据
            FetchNews.NewsResponse response = FetchNews.fetchNews("10", "1900-01-01", "", new String[]{}, title, "1");
            if (response != null && response.data != null) {
                requireActivity().runOnUiThread(() -> {
                    NewsAdapter adapter = new NewsAdapter(response.data);
                    recyclerView.setAdapter(adapter);
                });
            }
        }).start();
    }
}