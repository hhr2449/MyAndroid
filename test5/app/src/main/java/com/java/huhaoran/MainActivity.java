package com.java.huhaoran;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private final int REQUEST_CODE = 1001;
    private TabLayout tablayout;
    private ViewPager2 viewpager;
    //这是新闻主题的数组，用来创建TabLayout的标签
    public static List<String> titles = new ArrayList<String>();
    public static List<String> titlesNoUse = new ArrayList<String>();

    private final String[] categories = {"全部", "文化", "娱乐", "军事", "教育", "健康", "财经", "体育", "汽车", "科技", "社会"};

    //newsCache用于缓存新闻数据，避免重复请求
    public static Map<String, List<FetchNews.NewsItem>> newsCache = new HashMap<>();

    //缓存每个分类当前加载到的页数
    public static Map<String, Integer> currentPage = new HashMap<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles = new ArrayList<>(Arrays.asList(categories));

        //初始化标列表
        TabPreference tabPreference = new TabPreference(this);
        titles = tabPreference.loadTitles();
        titlesNoUse = tabPreference.loadTitlesNoUse();

        //菜单页点击跳转
        // 获取 NavigationView 引用
        navigationView = findViewById(R.id.nav_view);

        // 设置菜单项点击监听器
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_history) {
                    // 处理浏览历史逻辑
                    Intent intent = new Intent(MainActivity.this, BrowseHistoryActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_favorites) {
                    // 处理收藏夹逻辑
                    Intent intent2 = new Intent(MainActivity.this, FavoritesHistoryActivity.class);
                    startActivity(intent2);
                } else if (id == R.id.nav_password) {
                    // 处理密码管理逻辑
                } else if (id == R.id.nav_logout) {
                    // 处理登出逻辑
                } else if (id == R.id.nav_about) {
                    // 处理关于页面逻辑
                }

                // 关闭抽屉（如果使用了 DrawerLayout）
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });


//----------------------注册主菜单点击事件-------------------------//
        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(v -> {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
        });
//----------------------注册主菜单点击事件-------------------------//

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

//----------------------注册tab栏管理键的点击事件-------------------------//
        ImageView tab_manager = findViewById(R.id.tab_manager);
        tab_manager.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TabsManagerActivity.class);
            //启动界面并且接受返回结果
            startActivityForResult(intent, REQUEST_CODE);
        });


//----------------------实注册tab栏管理键的点击事件-------------------------//


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
                return TabNewsFragment.newInstance(titles.get(position));
            }

            @Override
            //告诉ViewPager一共有多少个页面
            public int getItemCount() {
                //总的页面数就是标题的个数
                return titles.size();
            }
        });

        //设置预加载界面数
        viewpager.setOffscreenPageLimit(1);
        //tablayout点击事件
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置TabLayout的选中事件
                //tab.getPosition()获取当前选中的tab的索引
                //setCurrentItem()方法可以实现跳转到ViewPager的某个页面
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
                tab.setText(titles.get(position));
            }

        });

        //启用绑定
        tabLayoutMediator.attach();
//----------------------实现主题栏-----------------------------------------------


        //-------------------搜索栏跳转--------------------------

        RelativeLayout search = findViewById(R.id.search);
        search.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        //-------------------搜索栏跳转--------------------------







    }

    //-------------------同步标签----------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断返回码
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            //获取返回的数据
            List<String> update_titles = data.getStringArrayListExtra("update_titles");
            if (update_titles != null) {
                //更新标题列表
                MainActivity.titles = update_titles;
                //再次设置tablayout和viewpager
                viewpager.setAdapter(new FragmentStateAdapter(this) {
                    //这里创建了一个匿名的FragmentStateAdapter对象
                    @NonNull
                    @Override
                    //这个方法的返回值是一个Fragment对象，当ViewPager需要显示一个页面的时候就会调用
                    //这个方法，根据位置返回一个Fragment对象
                    public Fragment createFragment(int position) {
                        //使用newInstance()方法创建一个Fragment对象，传入的主题参数使用titles[position]获取
                        return TabNewsFragment.newInstance(titles.get(position));
                    }

                    @Override
                    //告诉ViewPager一共有多少个页面
                    public int getItemCount() {
                        //总的页面数就是标题的个数
                        return titles.size();
                    }
                });

            }
            //viewPager和tab_layout关联在一起
            TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tablayout, viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    tab.setText(titles.get(position));
                }

            });

            //启用绑定
            tabLayoutMediator.attach();
        }
    }
    //-------------------同步标签----------------------------






}