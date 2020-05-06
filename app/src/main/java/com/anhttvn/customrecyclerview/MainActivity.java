package com.anhttvn.customrecyclerview;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.anhttvn.customrecyclerview.adapter.ListAdapter;
import com.anhttvn.customrecyclerview.model.ItemAdapter;
import com.anhttvn.customrecyclerview.utility.NetworkReceiver;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleview;
    private List<ItemAdapter.Data> mList = new ArrayList<>();
    private ListAdapter mAdapter;
    JsonListService apiInterface;
    ProgressDialog progressDialog;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init() {
        mRecycleview = findViewById(R.id.listView);
        mSwipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        this.registerReceiver(receiver, filter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        progressDialog = ProgressDialog.show(MainActivity.this,
                "ProgressDialog",
                "Please wait...");
        //I tried with Asyc Task As well after getting data from server we get display the data through interface.
        //new JsonLiveData(MainActivity.this);

        apiInterface = ApiClient.getClient().create(JsonListService.class);

        Call<ItemAdapter> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<ItemAdapter>() {
            @Override
            public void onResponse(Call<ItemAdapter> call, Response<ItemAdapter> response) {

                String displayResponse = "";

                ItemAdapter resource = response.body();
                //Log.e("Response"," Body :- "+resource);
                //Log.e("Response"," Body :- "+resource.getTitle());
                getSupportActionBar().setTitle(resource.getTitle());

                mList = resource.data;
                adapter();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ItemAdapter> call, Throwable t) {
                call.cancel();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void adapter(){
        Log.d("anhtt","mlist : " +mList.size());
        mAdapter = new ListAdapter(mList, this);
        mRecycleview.setAdapter(mAdapter);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(receiver != null) {
            this.unregisterReceiver(receiver);
        }
    }
}
