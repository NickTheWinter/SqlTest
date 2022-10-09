package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ProfileAdapter extends BaseAdapter implements Filterable {
    LayoutInflater inflater;
    private List<Profile> mOriginalValues;
    private List<Profile> mDisplayedValues;

    public ProfileAdapter(Context context, List<Profile> profiles) {
        this.mOriginalValues = profiles;
        this.mDisplayedValues = profiles;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDisplayedValues.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder {
        RelativeLayout rlContainer;
        TextView tvName, tvId, tvWebSite;
        ImageView ivImage;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.table_list, null);
            holder.rlContainer = convertView.findViewById(R.id.rlayout);
            holder.tvId = convertView.findViewById(R.id.TextID);
            holder.tvName = convertView.findViewById(R.id.TextName);
            holder.tvWebSite = convertView.findViewById(R.id.TextWebsite);
            holder.ivImage = convertView.findViewById(R.id.ItemImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvId.setText(mDisplayedValues.get(i).id);
        holder.tvName.setText(mDisplayedValues.get(i).airline_name);
        holder.tvWebSite.setText(mDisplayedValues.get(i).airline_website);
        holder.ivImage.setImageBitmap(mDisplayedValues.get(i).image);

        return convertView;
    }
    @Override
    public Filter getFilter() {
        return null;
    }
}