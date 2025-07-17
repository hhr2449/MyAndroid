package com.java.huhaoran;

import static com.java.huhaoran.FetchNews.getLinks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.java.huhaoran.note.BrowseHistoryNote;

import java.util.HashSet;
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

    private String userName = UserManager.getCurrentUserName();
    private boolean isLogin = UserManager.isLoggedIn();
    //构造函数将列表传入
    public NewsAdapter(List<FetchNews.NewsItem> newslist) {
        this.newslist = newslist;
    }
    //创建ViewHolder
    static class NewsViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemView;
        //成员变量就是一条item中每个控件的引用
        TextView title, publisher, time;
        ImageView image1, image2, image3;
        //点赞和收藏的图标
        ImageView like, favor;
        //ViewHolder类同样也有支持item类型的构造函数
        public NewsViewHolder(View view, int type) {
            super(view);
            itemView = view.findViewById(R.id.item_view);
            title = (TextView) view.findViewById(R.id.title);
            publisher = (TextView) view.findViewById(R.id.publisher);
            time = (TextView) view.findViewById(R.id.time);
            like = (ImageView) view.findViewById(R.id.like);
            favor = (ImageView) view.findViewById(R.id.favor);
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

        //如果注册了，则要根据阅读和点赞的记录来改变列表
        if(isLogin) {
            //如果已经读过该新闻（记录了对应的title），则字体显示灰色
            AppDatabase db = AppDatabase.getInstance(holder.itemView.getContext());
            new Thread(() -> {
                boolean isRead = db.browseHistoryDao().existsByTitle(newslist.get(position).title, userName);
                holder.itemView.post(() -> {
                    if (isRead) {
                        holder.title.setTextColor(Color.parseColor("#8E8B8B"));
                    }
                    else {
                        holder.title.setTextColor(Color.parseColor("#000000"));
                    }
                });
            }).start();
            //如果点过赞或是有过收藏，要改变图标
            //注意子线程中不能改变ui
            new Thread(() -> {
                boolean isLike = db.likeDao().existsByTitle(newslist.get(position).title, userName);
                boolean isFavor = db.favoritesHistoryDao().existsByTitle(newslist.get(position).title, userName);
                holder.itemView.post(() -> {
                    if(isLike) {
                        holder.like.setImageResource(R.drawable.like_light);
                    }
                    else {
                        holder.like.setImageResource(R.drawable.like_dark);
                    }

                    if(isFavor) {
                        holder.favor.setImageResource(R.drawable.favor_light);
                    }
                    else {
                        holder.favor.setImageResource(R.drawable.favor_dark);
                    }
                });
            }).start();
            //不设置点击事件，只做展示使用（因为太麻烦了,如果可以的话可能会出现有收藏无浏览的情况，这样的情况下就必须在HistoryItemAdater中进行入库操作）
        }



        //设置点击事件监听，点击列表可以跳转到对应的新闻详情页面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin) {
                    //记录浏览过的新闻
                    AppDatabase db = AppDatabase.getInstance(v.getContext());
                    new Thread(() -> {
                        BrowseHistoryNote note = new BrowseHistoryNote(userName,newsitem.title, newsitem.publishTime, newsitem.content, newsitem.publisher, newsitem.category, newsitem.image, newsitem.video, System.currentTimeMillis());
                        db.browseHistoryDao().insert(note);
                    }).start();
                }
                //改变颜色
                holder.title.setTextColor(Color.parseColor("#8E8B8B"));
                //跳转页面
                Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
                intent.putExtra("title", newsitem.title);
                intent.putExtra("content", newsitem.content);
                intent.putExtra("image", newsitem.image);
                intent.putExtra("publisher", newsitem.publisher);
                intent.putExtra("publishTime", newsitem.publishTime);
                intent.putExtra("video", newsitem.video);
                intent.putExtra("position", holder.getAdapterPosition());

                // 改为startActivityForResult
                if (v.getContext() instanceof Activity) {
                    ((Activity)v.getContext()).startActivityForResult(intent, 1003);
                } else {
                    v.getContext().startActivity(intent);
                }
            }
        });

    }
    //获取item的总数
    @Override
    public int getItemCount() {
        return newslist.size();
    }


    //方便上拉获取更多的时候增加数据
    public void appendData(List<FetchNews.NewsItem> moreData) {
        int previousSize = this.newslist.size(); // 获取当前数据集的大小
        this.newslist.addAll(moreData);          // 将新数据追加到现有列表
        notifyItemRangeInserted(previousSize, moreData.size()); // 通知适配器新增了数据
    }

    //方便刷新数据
    public void updateData(List<FetchNews.NewsItem> newData) {
        this.newslist.clear();
        this.newslist.addAll(newData);
        notifyDataSetChanged();           // 通知 RecyclerView 所有项需要刷新
    }

    public int getPosByTitle(String title) {
        int pos = -1;
        for (int i = 0; i < newslist.size(); i++) {
            if (newslist.get(i).title.equals(title)) {
                pos = i;
            }
        }
        return pos;
    }

    public void refreshSingleNews(String title) {
        int pos = getPosByTitle(title);
        if (pos != -1) {
            notifyItemChanged(pos);
        }
    }


}
