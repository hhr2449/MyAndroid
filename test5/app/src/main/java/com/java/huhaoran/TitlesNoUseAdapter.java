package com.java.huhaoran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TitlesNoUseAdapter extends BaseAdapter {
    Context mContext;
    List<String> titlesNoUse;
    //用于显示是否启用编辑图标
    boolean editMode;

    class ViewHolder {
        TextView title;
        ImageView delButton;
    }
    public TitlesNoUseAdapter(Context context, List<String> titlesNoUse, boolean editMode) {
        this.mContext = context;
        this.titlesNoUse = titlesNoUse;
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
        TitlesNoUseAdapter.ViewHolder holder = new TitlesNoUseAdapter.ViewHolder();
        if(convertView == null) {
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
        }
        return convertView;
    }
}
