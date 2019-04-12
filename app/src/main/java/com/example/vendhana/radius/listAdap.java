package com.example.vendhana.radius;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class listAdap extends BaseAdapter {

    private LayoutInflater mInflater;

    private List<user> items;

    public listAdap(Context context, List items) {
        mInflater = LayoutInflater.from(context);
        this.items = items;
    }

    public int getCount() {
        return items.size();
    }

    public user getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        user u = items.get(position);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_list, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name1);
            holder.age = (TextView) convertView.findViewById(R.id.age1);
            holder.pic = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(u.getName());
        if (u.getImage() != null) {
            holder.pic.setImageBitmap(u.getImage());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView name,age;
        ImageView pic;
    }

}
