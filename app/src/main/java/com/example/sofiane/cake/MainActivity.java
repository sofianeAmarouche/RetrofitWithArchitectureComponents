package com.example.sofiane.cake;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    CakeDetailViewModel cakeDetailViewModel;
    ProgressDialog progressDoalog;
    Context context;
    CakeDetailAdapter  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        recyclerView =(RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        cakeDetailViewModel = ViewModelProviders.of(this).get(CakeDetailViewModel.class);
        if(cakeDetailViewModel.getNewsResponseObservable().getValue() == null)
            System.out.println("Something went wrong...Please try later!");

        adapter = new CakeDetailAdapter(cakeDetailViewModel.getNewsResponseObservable().getValue(),context);
         recyclerView.setAdapter(adapter);
        cakeDetailViewModel.getNewsResponseObservable().observe(this, new Observer<List<CakeDetail>>() {
            @Override
            public void onChanged(final List<CakeDetail> cakes) {
                adapter.setCakeDetail(cakes);
            }
        });
    }
}
