package com.java.huhaoran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


//定义RecyclerView的适配器
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    //成员变量是新闻列表
    private List<FetchNews.NewsItem> newslist;
    //构造函数将列表传入
    public NewsAdapter(List<FetchNews.NewsItem> newslist) {
        this.newslist = newslist;
    }
    //创建ViewHolder
    static class NewsViewHolder extends RecyclerView.ViewHolder {
        //成员变量就是一条item中每个控件的引用
        TextView title, publisher, time;
        ImageView image;
        public NewsViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            publisher = (TextView) view.findViewById(R.id.publisher);
            time = (TextView) view.findViewById(R.id.time);
            image = (ImageView) view.findViewById(R.id.image);
        }

    }
    //创建ViewHolder
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建item的布局,inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
        //参数：要使用的布局文件，父布局，是否将布局文件添加为父布局的子元素
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        NewsViewHolder holder = new NewsViewHolder(view);
        return holder;
    }

    //获取图片链接,因为直接爬取得到的图片链接可能有中括号和,分割，所以需要处理
    String[] getLinks(String input) {
        if(input.length() <= 2) {
            return null;
        }
        input = input.substring(1, input.length()-1);
        String[] links = input.split(",\\s*");
        return links;
    }

    //为item设置内容,holder是当前item的ViewHolder,position是当前item的位置
    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        //获取需要的那条新闻
        FetchNews.NewsItem newsitem = newslist.get(position);
        holder.title.setText(newsitem.title);
        holder.publisher.setText(newsitem.publisher);
        holder.time.setText(newsitem.publishTime);
        String[] links = getLinks(newsitem.image);
        if(links != null && links.length > 0) {
            // 使用 Glide 加载网络图片
            Glide.with(holder.itemView.getContext())  // 使用 ViewHolder 的 itemView 的 Context
                    .load(links[0])                       // 图片 URL
                    .into(holder.image);              // 绑定到 ImageView
        }

    }
    //获取item的总数
    @Override
    public int getItemCount() {
        return newslist.size();
    }
}
