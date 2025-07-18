package com.java.huhaoran;

import static com.java.huhaoran.FetchNews.getLinks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {

    //成员变量是搜索历史列表
    private List<SearchData> list;
    Context mContext;
    //构造函数将列表传入
    public SearchHistoryAdapter(List<SearchData> newslist, Context mContext ) {
        this.mContext = mContext;
        this.list = newslist;
    }
    //创建ViewHolder
    static class SearchHistoryViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemView;
        //成员变量就是一条item中每个控件的引用
        TextView  keywords_history, category_history, time_history;
        public SearchHistoryViewHolder(View view) {
            super(view);
            itemView = view.findViewById(R.id.item_view);
            keywords_history = (TextView) view.findViewById(R.id.keywords_history);
            category_history = (TextView) view.findViewById(R.id.category_history);
            time_history = (TextView) view.findViewById(R.id.time_history);


        }

    }


    //创建ViewHolder，这里提供了viewType参数，这个参数表示当前item的类型
    @Override
    public SearchHistoryAdapter.SearchHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建item的布局,inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
        //参数：要使用的布局文件，父布局，是否将布局文件添加为父布局的子元素
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history, parent, false);
        SearchHistoryAdapter.SearchHistoryViewHolder holder = new SearchHistoryAdapter.SearchHistoryViewHolder(view);
        return holder;
    }


    //为item设置内容,holder是当前item的ViewHolder,position是当前item的位置
    @Override
    public void onBindViewHolder(SearchHistoryAdapter.SearchHistoryViewHolder holder, int position) {
        //获取对应的搜索记录
        SearchData item = list.get(position);
        String keywords = item.getKeyword();
        if(keywords == null || keywords.trim().isEmpty()) {
            keywords = "无关键词";
        }
        holder.keywords_history.setText(keywords);
        if (item.getCategories() != null && !item.getCategories().isEmpty()) {
            StringBuilder categoryText = new StringBuilder("分类：");
            int size = item.getCategories().size();
            for(String category : item.getCategories()) {
                categoryText.append(category);
                if(size > 1) {
                    categoryText.append(",");
                }
            }
            holder.category_history.setText(categoryText.toString());
        } else {
            holder.category_history.setText("分类：全部");
        }
        String startDate = item.getStartDate();
        if(startDate == null || startDate.trim().isEmpty()) {
            startDate = "不限";
        }
        String endDate = item.getEndDate();
        if(endDate == null || endDate.trim().isEmpty()) {
            endDate = "不限";
        }
        String time = "时间范围：" + item.getStartDate() + " ~ " + item.getEndDate();
        holder.time_history.setText(time);

        //设置点击时间，点击可以跳转到对应的搜索结果界面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到搜索结果页面
                Intent intent = new Intent(mContext, SearchResultActivity.class);
                intent.putExtra("searchData", item);
                mContext.startActivity(intent);
            }
        });






    }

    //获取item的总数
    @Override
    public int getItemCount() {
        return list.size();
    }


    //方便上拉获取更多的时候增加数据
    public void appendData(List<SearchData> moreData) {
        int previousSize = this.list.size(); // 获取当前数据集的大小
        this.list.addAll(moreData);          // 将新数据追加到现有列表
        notifyItemRangeInserted(previousSize, moreData.size()); // 通知适配器新增了数据
    }

    //方便刷新数据
    public void updateData(List<SearchData> newData) {
        this.list.clear();
        this.list.addAll(newData);
        notifyDataSetChanged();           // 通知 RecyclerView 所有项需要刷新
    }

    public void appendDataInHead(List<SearchData> moreData) {
        this.list.addAll(0, moreData);
        notifyItemRangeInserted(0, moreData.size());
    }


}
