package com.java.huhaoran;

import static com.java.huhaoran.FetchNews.getLinks;

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
    //定义常量用于表示item类型
    private static final int TYPE_NO_IMAGE = 0;
    private static final int TYPE_WITH_ONEIMAGE = 1;
    private static final int TYPE_WITH_TWOIMAGE = 2;
    private static final int TYPE_WITH_THREEIMAGE = 3;
    //构造函数将列表传入
    public NewsAdapter(List<FetchNews.NewsItem> newslist) {
        this.newslist = newslist;
    }
    //创建ViewHolder
    static class NewsViewHolder extends RecyclerView.ViewHolder {
        //成员变量就是一条item中每个控件的引用
        TextView title, publisher, time;
        ImageView image1, image2, image3;
        //ViewHolder类同样也有支持item类型的构造函数
        public NewsViewHolder(View view, int type) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            publisher = (TextView) view.findViewById(R.id.publisher);
            time = (TextView) view.findViewById(R.id.time);
            if(type == TYPE_NO_IMAGE) {

            }
            if(type == TYPE_WITH_ONEIMAGE) {
                image1 = (ImageView) view.findViewById(R.id.image1);
            }
            if(type == TYPE_WITH_TWOIMAGE) {
                image1 = (ImageView) view.findViewById(R.id.image1);
                image2 = (ImageView) view.findViewById(R.id.image2);
            }
            if(type == TYPE_WITH_THREEIMAGE) {
                image1 = (ImageView) view.findViewById(R.id.image1);
                image2 = (ImageView) view.findViewById(R.id.image2);
                image3 = (ImageView) view.findViewById(R.id.image3);
            }

        }

    }

    // 重写 getItemViewType() 方法来判断该条新闻使用哪种布局
    @Override
    public int getItemViewType(int position) {
        FetchNews.NewsItem newsitem = newslist.get(position);
        //调用工具函数
        String[] links = getLinks(newsitem.image);
        if(links == null || links.length == 0) {
            return TYPE_NO_IMAGE;
        }
        else if(links.length == 1) {
            return TYPE_WITH_ONEIMAGE;
        }
        else if(links.length == 2) {
            return TYPE_WITH_TWOIMAGE;
        }
        else {
            return TYPE_WITH_THREEIMAGE;
        }
    }


    //创建ViewHolder，这里提供了viewType参数，这个参数表示当前item的类型
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建item的布局,inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
        //参数：要使用的布局文件，父布局，是否将布局文件添加为父布局的子元素
        View view = null;
        switch (viewType) {
            case TYPE_NO_IMAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_no_image, parent, false);
                break;
                case TYPE_WITH_ONEIMAGE:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_oneimage, parent, false);
                    break;
                    case TYPE_WITH_TWOIMAGE:
                        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_twoimage, parent, false);
                        break;
                        case TYPE_WITH_THREEIMAGE:
                            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_threeimage, parent, false);
                            break;
        }

        NewsViewHolder holder = new NewsViewHolder(view, viewType);
        return holder;
    }


    //为item设置内容,holder是当前item的ViewHolder,position是当前item的位置
    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        //获取需要的那条新闻
        FetchNews.NewsItem newsitem = newslist.get(position);
        holder.title.setText(newsitem.title);
        holder.publisher.setText(newsitem.publisher);
        String time[] = newsitem.publishTime.split(" ");
        holder.time.setText(time[0]);
        String[] links = getLinks(newsitem.image);
        try{
            if(links != null && links.length > 0) {
                if(links.length == 1 && links[0] != null ) {
                    Glide.with(holder.itemView.getContext()).load(links[0]).into(holder.image1);
                }
                else if(links.length == 2 && links[0] != null && links[1] != null) {
                    Glide.with(holder.itemView.getContext()).load(links[0]).into(holder.image1);
                    Glide.with(holder.itemView.getContext()).load(links[1]).into(holder.image2);
                }
                else if(links.length >= 3 && links[0] != null && links[1] != null && links[2] != null){
                    Glide.with(holder.itemView.getContext()).load(links[0]).into(holder.image1);
                    Glide.with(holder.itemView.getContext()).load(links[1]).into(holder.image2);
                    Glide.with(holder.itemView.getContext()).load(links[2]).into(holder.image3);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }


    }
    //获取item的总数
    @Override
    public int getItemCount() {
        return newslist.size();
    }
}
