package com.java.huhaoran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashSet;

//回调接口，用于将选择信息传递到主界面
interface OnCategoryTransferListener {
    void onCategoryTransfer(String tag, boolean isChoosed);
}

public class SearchCategoryAdapter extends BaseAdapter {

    private final String[] categories = {"全部", "文化", "娱乐", "军事", "教育", "健康", "财经", "体育", "汽车", "科技", "社会"};
    //设置是否被选择

    //！！！注意，每个item的isChoose属性都是单独的，不能被其他item所共享，所以这里应该使用一个set，里面放入被选择的item的position
    HashSet<Integer> selectedPositions;
    //设置当前的context
    Context mContext;
    //接口的对象
    OnCategoryTransferListener listener;

    class ViewHolder
    {
        TextView textView;
    }

    //设置接口对象
    public void setOnCategoryTransferListener(OnCategoryTransferListener listener)
    {
        this.listener = listener;
    }

    public SearchCategoryAdapter(Context mContext)
    {
        this.mContext = mContext;
        selectedPositions = new HashSet<>();
    }

    @Override
    public int getCount()
    {
        return categories.length;
    }

    @Override
    public Object getItem(int position)
    {
        return categories[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    //生成View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //如果没有可以复用的view，就生成一个
        if(convertView == null) {
            //生成一个ViewHolder
            holder = new ViewHolder();
            //生成view
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_category, parent, false);
            //从刚刚生成的view中获取TextView
            holder.textView = (TextView) convertView.findViewById(R.id.category);
            //将holder保存在view中
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        //根据position设置内容
        holder.textView.setText(categories[position]);
        //根据选中状态设置背景
        if(selectedPositions.contains(position)) {
            if(position == 0) {
                holder.textView.setBackgroundResource(R.drawable.click_effect3);
            }
            else {
                holder.textView.setBackgroundResource(R.drawable.click_effect2);
            }
        }
        else {
            holder.textView.setBackgroundResource(R.drawable.click_effect);
        }

        //设置点击事件
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = selectedPositions.contains(position);

                if (position == 0) {
                    // 点击“全部”时切换全选状态
                    if (isSelected) {
                        // 当前是选中状态，取消所有选择
                        selectedPositions.clear();
                    } else {
                        // 清除其他所有选择，只选中“全部”
                        selectedPositions.clear();
                        selectedPositions.add(0);
                    }
                    notifyDataSetChanged();

                    // 通知外部更新所有分类的选择状态
                    if (listener != null) {
                        for (int i = 1; i < categories.length; i++) {
                            listener.onCategoryTransfer(categories[i], false); // 取消其他
                        }
                        listener.onCategoryTransfer(categories[0], !isSelected); // 更新“全部”
                    }

                } else {
                    // 普通分类点击行为
                    if (isSelected) {
                        selectedPositions.remove(position);
                    } else {
                        selectedPositions.add(position);
                    }

                    // 若“全部”被选中，取消它
                    if (selectedPositions.contains(0)) {
                        selectedPositions.remove(0);
                    }

                    notifyDataSetChanged();

                    if (listener != null) {
                        listener.onCategoryTransfer(categories[position], !isSelected);
                    }
                }
            }

        });

        return convertView;
    }








}
