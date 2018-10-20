package com.example.sofiane.cake;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.databinding.ObservableField;


import java.util.List;

/**
 * Created by Sofiane on 10/19/2018.
 */

public class CakeDetailViewModel extends AndroidViewModel {

    private CakeDetailRepository cakeDetailRepository;
    private LiveData<List<CakeDetail>> cakesLive;

    private static final MutableLiveData MUTABLE_LIVE_DATA = new MutableLiveData();
    {
        MUTABLE_LIVE_DATA.setValue(null);
    }

    public final ObservableField<List<CakeDetail>> project = new ObservableField<>();
    public  final

    LiveData<List<CakeDetail>> newsResponseObservable;

    public CakeDetailViewModel(@NonNull Application application)
    {
        super(application);
        newsResponseObservable = CakeDetailRepository.getInstance()
                .getCakes();
    }

    public LiveData<List<CakeDetail>> getNewsResponseObservable()
    {
        return newsResponseObservable;
    }
}
