package com.java.huhaoran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

interface OnTitlesNoUseTransferListener {
    void onTitlesNoUseTransfer(String tag);
}

public class TitlesNoUseAdapter extends BaseAdapter {
    Context mContext;
    List<String> titlesNoUse;
    //用于显示是否启用编辑图标
    boolean editMode;
    //定义接口的引用
    OnTitlesNoUseTransferListener mOnTitlesNoUseTransferListener;

    //传入接口的实现
    public void setOnTitlesNoUseTransferListener(OnTitlesNoUseTransferListener listener) {
        mOnTitlesNoUseTransferListener = listener;
    }

    class ViewHolder {
        TextView title;
        ImageView delButton;
    }
    public TitlesNoUseAdapter(Context context, List<String> titlesNoUse, boolean editMode) {
        this.mContext = context;
        this.titlesNoUse = new ArrayList<String>(titlesNoUse);
        this.editMode = editMode;
    }

    @Override
    public int getCount() {
        return titlesNoUse.size();
    }

    @Override
    public Object getItem(int position) {
        return titlesNoUse.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TitlesNoUseAdapter.ViewHolder holder;
        if(convertView == null) {
            holder = new TitlesNoUseAdapter.ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tab_manager, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.delButton = (ImageView) convertView.findViewById(R.id.delButton);
            convertView.setTag(holder);
        }
        else {
            holder = (TitlesNoUseAdapter.ViewHolder) convertView.getTag();
        }
        //第position个item显示第position个标题
        holder.title.setText(titlesNoUse.get(position));
        //如果开启编辑模式，就显示删除图标
        if(editMode) {
            holder.delButton.setImageResource(R.drawable.delbutton);
            holder.delButton.setVisibility(View.VISIBLE);
            Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
            holder.delButton.startAnimation(shake);
            holder.title.startAnimation(shake);
            holder.title.setOnClickListener(v -> {
                //只要点击，就调用接口中的方法来调整list
                mOnTitlesNoUseTransferListener.onTitlesNoUseTransfer(titlesNoUse.get(position));
            });
        }
        else {
            //取消各种效果
            holder.delButton.setVisibility(View.GONE); // 编辑模式为 false 时隐藏
            holder.title.clearAnimation();
            holder.delButton.clearAnimation();
            holder.title.setOnClickListener(null);
        }
        return convertView;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
        notifyDataSetChanged();
    }

    //设置适配器中成员变量List的增删方法，同时可以刷新
    public void add(String title) {
        titlesNoUse.add(title);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        titlesNoUse.remove(position);
        notifyDataSetChanged();
    }
}
