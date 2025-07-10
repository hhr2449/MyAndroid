package com.java.huhaoran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TitlesAdapter extends BaseAdapter {

    Context mContext;
    List<String> titles;
    //用于显示是否启用编辑图标
    boolean editMode;

    class ViewHolder {
        TextView title;
        ImageView delButton;
    }
    public TitlesAdapter(Context context, List<String> titles, boolean editMode) {
        this.mContext = context;
        this.titles = titles;
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
        ViewHolder holder = new ViewHolder();
        if(convertView == null) {
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
        if(editMode && position != 0) {
            holder.delButton.setImageResource(R.drawable.delbutton);
        }
        if(position == 0) {
            holder.title.setTextColor(mContext.getResources().getColor(R.color.light_black));;
        }
        return convertView;
    }




}
