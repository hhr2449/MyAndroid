package com.java.huhaoran;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.java.huhaoran.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager2 viewpager;
    //这是新闻主题的数组，用来创建TabLayout的标签
    private String[] titles = {"全部", "文化", "娱乐", "军事", "教育", "健康", "财经", "体育", "汽车", "科技", "社会"};

    //newsCache用于缓存新闻数据，避免重复请求
    public static Map<String, List<FetchNews.NewsItem>> newsCache = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(v -> {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
        });

//----------------------实现抽屉栏的返回键-------------------------//
        //注意这个图标是属于 NavigationView中的头部栏的，所以不能直接获取
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0); // 获取第一个头部视图
        ImageView backbutton = headerView.findViewById(R.id.backbutton);
        //设置点击滑动抽屉的返回键
        backbutton.setOnClickListener(v -> {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            //关闭抽屉
            drawer.closeDrawer(GravityCompat.START);
        });
//----------------------实现抽屉栏的返回键-------------------------//


//----------------------实现主题栏-----------------------------------------------
        //初始化tablayout和viewpager，用于实现滑动新闻分类
        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);

        //viewpager需要设置Adapter,这里使用FragmentStateAdapter
        //ViewPager是容器，负责显示页面和处理手部滑动
        //FragmentStateAdapter是Fragment的适配器，负责向ViewPager提供Fragment，并且管理Fragment的生命周期
        //Fragment是每个页面具体显示的内容
        viewpager.setAdapter(new FragmentStateAdapter(this) {
            //这里创建了一个匿名的FragmentStateAdapter对象
            @NonNull
            @Override
            //这个方法的返回值是一个Fragment对象，当ViewPager需要显示一个页面的时候就会调用
            //这个方法，根据位置返回一个Fragment对象
            public Fragment createFragment(int position) {
                //使用newInstance()方法创建一个Fragment对象，传入的主题参数使用titles[position]获取
                return TabNewsFragment.newInstance(titles[position]);
            }

            @Override
            //告诉ViewPager一共有多少个页面
            public int getItemCount() {
                //总的页面数就是标题的个数
                return titles.length;
            }
        });

        //设置预加载界面数
        viewpager.setOffscreenPageLimit(1);
        //tablayout点击事件
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置viewPager选中当前页
                //tab.getPosition()获取当前选中的tab的索引
                viewpager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //viewPager和tab_layout关联在一起
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tablayout, viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        });

        //启用绑定
        tabLayoutMediator.attach();
//----------------------实现主题栏-----------------------------------------------





    }


}