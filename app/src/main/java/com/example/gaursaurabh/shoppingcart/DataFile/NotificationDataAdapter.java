package com.example.gaursaurabh.shoppingcart.DataFile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaursaurabh.shoppingcart.Picasso.PicassoClient;
import com.example.gaursaurabh.shoppingcart.R;

import java.util.List;

public class NotificationDataAdapter extends RecyclerView.Adapter<NotificationDataAdapter.ViewHolder> {

    private Context context;
    private List<NotificationDataList> dataLists;
    Intent intent;

    public NotificationDataAdapter(Context context, List<NotificationDataList> dataLists){
        this.context = context;
        this.dataLists = dataLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list_item, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.title.setText(dataLists.get(position).getTitle());
        holder.desc.setText(dataLists.get(position).getDesc());
        PicassoClient.downloadImgae(context, dataLists.get(position).getIcon(), holder.icon);
        PicassoClient.downloadImgae(context, dataLists.get(position).getImage(), holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title, desc;
        public ImageView icon, imageView;

        public ViewHolder(View itemView){
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            imageView = (ImageView) itemView.findViewById(R.id.notification_image);
        }
    }
}
