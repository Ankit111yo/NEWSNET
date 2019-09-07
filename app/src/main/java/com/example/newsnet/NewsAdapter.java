package com.example.newsnet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewholder> {
    @NonNull

    private Context mContext;
    private List<News> cardList;

    public NewsAdapter(Context mContext, List<News> cardList)
    {
        this.mContext = mContext;
        this.cardList = cardList;
    }

    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards, parent, false);
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder item, int i) {

        final News currentnews=cardList.get(i);
        item.mtitle.setText(currentnews.getmTitle());
        item.msource.setText(currentnews.getmSource());
        item.mdesc.setText(currentnews.getmDescription());
        String date=currentnews.getmDate();
        if(date!=null){
            StringBuilder newDate=new StringBuilder();
            newDate.append(date);
            newDate.deleteCharAt(10);
            newDate.deleteCharAt(18);
            newDate.insert(10,' ');
            newDate.insert(10,' ');
            newDate.insert(10,' ');
            newDate.append(" UTC");
            item.mdate.setText(newDate.toString());
        }
        String imgUri = currentnews.getmImageUrl();
        if(imgUri!=null){
            Glide.with(mContext).load(imgUri).into(item.mimage);
        }

        item.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri=currentnews.getmUrl();
                Uri url =Uri.parse(uri);
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(url);
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
       return cardList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView mdate;
        TextView mdesc;
        TextView mtitle;
        TextView msource;
        ImageView mimage;
        View view;

        public MyViewholder(View view)
        {
            super(view);
            mimage = view.findViewById(R.id.image);
            mdesc = view.findViewById(R.id.desc);
            mdate = view.findViewById(R.id.date);
            msource = view.findViewById(R.id.source);
            mtitle = view.findViewById(R.id.title);
            this.view=view;


        }
    }
}
