package com.example.gaursaurabh.shoppingcart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gaursaurabh.shoppingcart.Product.ProductGetValue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private ImageView btnSkip, btnNext;
    private LinearLayout container, particularSeller;
    ArrayList<Integer> slider_image_list;

    String prodname, prodprice, prodimage1, prodimage2, prodimage3;
    Intent intent;
    String getId;

    ProductGetValue productGetValue;
    String get_details[];

    TextView productname, productprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product);
        overridePendingTransition(R.anim.enter, R.anim.exit);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.product_action_bar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.grey_dark), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnSkip = findViewById(R.id.btn_skip);
        btnNext = findViewById(R.id.btn_nxt);
        container = findViewById(R.id.container);
        particularSeller = findViewById(R.id.particularSeller);

        init();
        addBottomDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter(ProductActivity.this, slider_image_list);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(-1);
                if (current>=0){
                    viewPager.setCurrentItem(current);
                }else{
                    viewPager.setCurrentItem(slider_image_list.size());
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if (current<slider_image_list.size()){
                    viewPager.setCurrentItem(current);
                }else{
                    viewPager.setCurrentItem(0);
                }
            }
        });

        particularSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductActivity.this, ProfileActivity.class));
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        productGetValue = new ProductGetValue();
        intent = getIntent();
        getId = intent.getStringExtra("product_id");

        productname = (TextView) findViewById(R.id.productname);
        productprice = (TextView) findViewById(R.id.productprice);

        get_details = productGetValue.getProdValue(getId);

        prodname = get_details[0];
        prodprice = get_details[1];
        prodimage1 = get_details[2];
        prodimage2 = get_details[3];
        prodimage3 = get_details[4];

    }



    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    private void init() {

        slider_image_list = new ArrayList<>();

        slider_image_list.add(R.drawable.one);
        slider_image_list.add(R.drawable.two);
        slider_image_list.add(R.drawable.three);
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[slider_image_list.size()];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#e5e5e5"));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#424242"));
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == slider_image_list.size()-1){

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;
        Activity activity;
        ArrayList<Integer> image_arraylist;

        public MyViewPagerAdapter(Activity activity, ArrayList<Integer> slider_image_list){
            this.activity = activity;
            image_arraylist = slider_image_list;
        }
        @Override
        public int getCount() {
            return image_arraylist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.slider, container, false);

            ImageView im_slider = (ImageView) view.findViewById(R.id.im_slider);
            Picasso.with(activity.getApplicationContext())
                    .load(image_arraylist.get(position))
                    .placeholder(R.drawable.three) // optional
                    .error(R.drawable.three)         // optional
                    .into(im_slider);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
