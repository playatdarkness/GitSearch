package com.rahulwadhwa238.gitsearch.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rahulwadhwa238.gitsearch.ItemAdapter;
import com.rahulwadhwa238.gitsearch.R;
import com.rahulwadhwa238.gitsearch.api.Client;
import com.rahulwadhwa238.gitsearch.api.Service;
import com.rahulwadhwa238.gitsearch.model.Item;
import com.rahulwadhwa238.gitsearch.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSearchResult extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView disconnected;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_search_result);

        initViews();

        swipeRefreshLayout = findViewById(R.id.profile_resultContainer);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(ProfileSearchResult.this, "Refreshing...",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initViews()    {
        pd = new ProgressDialog(this);
        pd.setMessage("Retrieving Information...");
        pd.setCancelable(false);
        pd.show();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON() {
        disconnected = findViewById(R.id.disconnected);

        try {
            Client client = new Client();
            final Service apiService =
                    client.getClient().create(Service.class);
            //Call<ItemResponse> call = apiService.getItems();
            Call<ItemResponse> callUsers = apiService.getItems(getIntent().getExtras().getString("query"));
            callUsers.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItems();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeRefreshLayout.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error: ", t.getMessage());
                    Toast.makeText(ProfileSearchResult.this,"Error Retrieving Information!", Toast.LENGTH_LONG).show();
                    disconnected.setVisibility(View.VISIBLE);
                    pd.hide();
                }
            });

        } catch (Exception e)   {
            Log.d("Error: ", e.getMessage());
            Toast.makeText(ProfileSearchResult.this,e.toString(),Toast.LENGTH_SHORT);
        }

    }
}
