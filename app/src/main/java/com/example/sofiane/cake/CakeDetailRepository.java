package com.example.sofiane.cake;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sofiane on 10/19/2018.
 */

public class CakeDetailRepository {
    private APIRequest cakeAPI;
    String BASE_URL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/";

    public CakeDetailRepository(){
        cakeAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(APIRequest.class);
    }

    private static class SingletonHelper
    {
        private static final CakeDetailRepository INSTANCE = new CakeDetailRepository();
    }

    public static CakeDetailRepository getInstance()
    {
        return SingletonHelper.INSTANCE;
    }


    public LiveData<List<CakeDetail>> getCakes() {
        final MutableLiveData<List<CakeDetail>> data = new MutableLiveData<>();
        cakeAPI.getAllCakeDetails()
                .enqueue(new Callback<List<CakeDetail>>()
                {
                    @Override
                    public void onResponse(Call<List<CakeDetail>> call, Response<List<CakeDetail>> response)
                    {
                        data.setValue(response.body());

                    }

                    @Override
                    public void onFailure(Call<List<CakeDetail>> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }
}
