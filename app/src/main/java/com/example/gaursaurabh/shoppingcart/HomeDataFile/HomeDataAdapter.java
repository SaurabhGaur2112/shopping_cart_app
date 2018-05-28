package com.example.gaursaurabh.shoppingcart.HomeDataFile;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaursaurabh.shoppingcart.Picasso.PicassoClient;
import com.example.gaursaurabh.shoppingcart.ProductActivity;
import com.example.gaursaurabh.shoppingcart.R;

import java.util.List;

public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataAdapter.ViewHolder> {

    private Context context;
    private List<HomeDataList> dataLists;
    Intent intent;

    public HomeDataAdapter(Context context, List<HomeDataList> dataLists){
        this.context = context;
        this.dataLists = dataLists;
    }

    @Override
    public HomeDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent,false);
        return new HomeDataAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HomeDataAdapter.ViewHolder holder, final int position) {

        holder.name.setText(dataLists.get(position).getName());
        holder.price.setText(dataLists.get(position).getPrice());
        PicassoClient.downloadImgae(context, dataLists.get(position).getImage(), holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("product_id", dataLists.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name, price;
        public ImageView imageView;
        public CardView cardView;

        public ViewHolder(View itemView){
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.homeCardView);
            name = (TextView) itemView.findViewById(R.id.product_name);
            price = (TextView) itemView.findViewById(R.id.product_price);
            imageView = (ImageView) itemView.findViewById(R.id.product_image);
        }
    }
}
