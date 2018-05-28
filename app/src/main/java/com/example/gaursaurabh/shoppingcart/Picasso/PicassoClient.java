package com.example.gaursaurabh.shoppingcart.Picasso;

import android.content.Context;
import android.widget.ImageView;

import com.example.gaursaurabh.shoppingcart.R;
import com.squareup.picasso.Picasso;

public class PicassoClient {

    public static void downloadImgae(Context c, String url, ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.drawable.placeholder).into(img);
        }else {
            Picasso.with(c).load(R.drawable.placeholder).into(img);
        }
    }
}
