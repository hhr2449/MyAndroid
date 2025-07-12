package com.java.huhaoran;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

interface OnTitlesTransferListener {
    void onTitlesTransfer(String tag);
}

public class TitlesAdapter extends BaseAdapter {

    Context mContext;
    List<String> titles;
    //用于显示是否启用编辑图标
    boolean editMode;
    //定义接口的引用
    OnTitlesTransferListener mOnTitlesTransferListener;

    //传入接口的实现
    //这样就可以调用接口中的方法，实现与activity和其他adapter之间的交互
    public void setOnTitlesTransferListener(OnTitlesTransferListener listener) {
        mOnTitlesTransferListener = listener;
    }

    class ViewHolder {
        TextView title;
        ImageView delButton;
    }
    public TitlesAdapter(Context context, List<String> titles, boolean editMode) {
        this.mContext = context;
        this.titles = new ArrayList<String>(titles);
        this.editMode = editMode;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tab_manager, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.delButton = (ImageView) convertView.findViewById(R.id.delButton);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        //第position个item显示第position个标题
        holder.title.setText(titles.get(position));
        //如果开启编辑模式，就显示删除图标,注意全部这个分类不能够删除
        //在xml中不设置ImageView中的src属性，而是在getView中动态设置
        if(editMode) {
            if(position != 0) {
                holder.delButton.setImageResource(R.drawable.delbutton);
                holder.delButton.setVisibility(View.VISIBLE);
                Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
                holder.delButton.startAnimation(shake);
                holder.title.startAnimation(shake);
                //设置item的点击事件
                //注意点击事件只要注册一次就可以一直生效，所以我们可以直接在适配器中设置点击事件
                holder.title.setOnClickListener(v -> {
                    //只要点击，就调用接口中的方法来调整list
                    mOnTitlesTransferListener.onTitlesTransfer(titles.get(position));
                });
            }
            else {
                holder.delButton.setImageResource(R.drawable.forbit_change);
                holder.delButton.setVisibility(View.VISIBLE);
                holder.delButton.clearAnimation();
                holder.title.clearAnimation();

            }
        }
        else {
            holder.delButton.setVisibility(View.GONE); // 编辑模式为 false 时隐藏
            holder.title.clearAnimation();
            holder.delButton.clearAnimation();
            holder.title.setOnClickListener(null);
        }


        if(position == 0) {
            holder.title.setTextColor(mContext.getResources().getColor(R.color.light_black));;
        }else {
            holder.title.setTextColor(mContext.getResources().getColor(R.color.black)); // 用你默认的颜色
        }
        return convertView;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
        notifyDataSetChanged();
    }

    //设置适配器中成员变量List的增删方法，同时可以刷新
    public void add(String title) {
        titles.add(title);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        titles.remove(position);
        notifyDataSetChanged();
    }




}
