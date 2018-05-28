package com.example.gaursaurabh.shoppingcart;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gaursaurabh.shoppingcart.DataFile.NotificationDataAdapter;
import com.example.gaursaurabh.shoppingcart.DataFile.NotificationDataList;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShimmerFrameLayout mShimmerViewContainer;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private NotificationDataAdapter adapter;
    private List<NotificationDataList> dataLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_notification);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        dataLists = new ArrayList<>();
        load_data_from_server();

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter = new NotificationDataAdapter(this,dataLists);
        recyclerView.setAdapter(adapter);
    }

    private void load_data_from_server() {

        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://wallnit.com/shopping/notification.php").build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);

                        NotificationDataList data = new NotificationDataList(object.getString("noti_icon"),object.getString("noti_title"), object.getString("noti_desc"), object.getString("noti_image"));
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
