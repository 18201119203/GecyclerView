package com.example.day2.adapter;

import android.content.Context;
import android.support.constraint.solver.LinearSystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day2.Bean.NewsBean;
import com.example.day2.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<NewsBean.DataBean> list;

    public NewsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setNews(List<NewsBean.DataBean> list){

        this.list = list;
        notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NewsBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return 0;
        }
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(getItemViewType(position)==0?R.layout.itemone:R.layout.itemtwo,parent,false);
            holder = new ViewHolder(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.bindData(getItem(position),position);

        return convertView;
    }

    class ViewHolder{

        TextView title,price;
        ImageView image;


        public ViewHolder(View view) {

            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
            image = view.findViewById(R.id.image);
            view.setTag(this);

        }

        public void bindData(NewsBean.DataBean item, int position) {

            title.setText(item.getTitle());
            price.setText(item.getPrice()+"");
            if (getItemViewType(position)==1){
                ImageLoader.getInstance().displayImage(item.getImages(),image);
            }

        }
    }


}
