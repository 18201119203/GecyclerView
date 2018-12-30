package com.example.day4_rikao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day4_rikao.R;
import com.example.day4_rikao.bean.NewsInfo;
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsInfo.DataBean> list;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<NewsInfo.DataBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 获取条目布局
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsitem,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 赋值
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.news_title.setText(list.get(i).getTitle());
        viewHolder.news_price.setText(list.get(i).getPrice()+"");
        NewsInfo.DataBean dataBean = list.get(i);
        String[] split = dataBean.getImages().split("!");
        Glide.with(context).load(split[0]).into(viewHolder.news_image);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView news_image;
        private TextView news_title;
        private TextView news_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_image = itemView.findViewById(R.id.news_image);
            news_title = itemView.findViewById(R.id.news_title);
            news_price = itemView.findViewById(R.id.news_price);


        }
    }
}
