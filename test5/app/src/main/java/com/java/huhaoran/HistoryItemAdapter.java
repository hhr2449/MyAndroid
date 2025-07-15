package com.java.huhaoran;

import static com.java.huhaoran.FetchNews.getLinks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.java.huhaoran.note.BrowseHistoryNote;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//定义RecyclerView的适配器
//这个是history_item_adapter
public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.HistoryViewHolder>{
    //成员变量是新闻列表
    private List<FetchNews.NewsItem> newslist;
    //用于显示是否启用编辑图标
    boolean editMode;
    Context mContext;
    //定义常量用于表示item类型
    //定义一个HashSet用于保存要删除的新闻的标题
    HashSet<String> titlesRemove;
    private static final int TYPE_NO_IMAGE = 0;
    private static final int TYPE_WITH_ONEIMAGE = 1;
    private static final int TYPE_WITH_TWOIMAGE = 2;
    private static final int TYPE_WITH_THREEIMAGE = 3;
    //构造函数将列表传入
    public HistoryItemAdapter(List<FetchNews.NewsItem> newslist , boolean editMode, Context mContext ) {
        this.editMode = editMode;
        this.mContext = mContext;
        this.newslist = newslist;
        titlesRemove = new HashSet<>();
    }
    //创建ViewHolder
    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemView;
        //成员变量就是一条item中每个控件的引用
        TextView title, publisher, time;
        ImageView image1, image2, image3;
        //ViewHolder类同样也有支持item类型的构造函数
        ImageView like, favor;
        public HistoryViewHolder(View view, int type) {
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
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        HistoryViewHolder holder = new HistoryViewHolder(view, viewType);
        return holder;
    }


    //为item设置内容,holder是当前item的ViewHolder,position是当前item的位置
    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
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

        AppDatabase db = AppDatabase.getInstance(holder.itemView.getContext());
        //如果点过赞或是有过收藏，要改变图标
        new Thread(() -> {
            boolean isLike = db.likeDao().existsByTitle(newslist.get(position).title);
            boolean isFavor = db.favoritesHistoryDao().existsByTitle(newslist.get(position).title);
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

        //设置点击事件监听，非编辑模式点击列表可以跳转到对应的新闻详情页面
        //如果处于编辑模式，则设置点击事件，点击某一条新闻,如果未被选中，则会变成深色，并且加入删除列表，否则则变成浅色，并且从删除列表中删除
        if (editMode) {
            holder.itemView.setOnClickListener(v -> {
                if (titlesRemove.contains(newsitem.title)) {
                    holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFFFF")); // 未选中
                    titlesRemove.remove(newsitem.title);
                } else {
                    holder.itemView.setBackgroundColor(Color.parseColor("#FFEEEEEE")); // 被选中
                    titlesRemove.add(newsitem.title);
                }
            });
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE); // 恢复颜色
            holder.itemView.setOnClickListener(v -> {
                // 跳转详情页
                Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
                intent.putExtra("title", newsitem.title);
                intent.putExtra("content", newsitem.content);
                intent.putExtra("image", newsitem.image);
                intent.putExtra("publisher", newsitem.publisher);
                intent.putExtra("publishTime", newsitem.publishTime);
                intent.putExtra("video", newsitem.video);
                v.getContext().startActivity(intent);
            });
        }



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

    //设置编辑模式
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
        notifyDataSetChanged();
    }

    //获取删除列表
    public HashSet<String> getTitlesToRemove() {
        return titlesRemove;
    }

    //删除掉列表的新闻条目
    public void removeItemsByTitle(Set<String> titles) {
        // 创建一个临时列表，用于存储要删除的项
        List<FetchNews.NewsItem> toRemove = new ArrayList<>();
        // 遍历数据集，查找要删除的项
        for (FetchNews.NewsItem item : newslist) {
            if (titles.contains(item.title)) {
                toRemove.add(item);
            }
        }
        newslist.removeAll(toRemove);
        titlesRemove.clear(); // 清空选择
        notifyDataSetChanged();
    }

}
