package com.example.gaursaurabh.shoppingcart;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.gaursaurabh.shoppingcart.DataFile.NotificationDataAdapter;
import com.example.gaursaurabh.shoppingcart.DataFile.NotificationDataList;
import com.example.gaursaurabh.shoppingcart.HomeDataFile.HomeDataAdapter;
import com.example.gaursaurabh.shoppingcart.HomeDataFile.HomeDataList;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ShimmerFrameLayout mShimmerViewContainer;

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private HomeDataAdapter adapter;
    private List<HomeDataList> dataLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        dataLists = new ArrayList<>();
        load_data_from_server();

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter = new HomeDataAdapter(this,dataLists);
        recyclerView.setAdapter(adapter);
    }

    private void load_data_from_server() {

        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://wallnit.com/shopping/home.php").build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);

                        HomeDataList data = new HomeDataList(object.getString("prod_id"),object.getString("prod_name"), object.getString("prod_price"), object.getString("prod_image"));
                        dataLists.add(data);

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mShimmerViewContainer.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }
        };
        task.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}
