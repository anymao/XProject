package com.anymore.example.mvvm.view.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by liuyuanmao on 2019/5/18.
 */
public abstract class AsyncDiffer<T> extends DiffUtil.ItemCallback<T> {
    private List<T> oldSnapshot;
    private List<T> newData;
    private final Executor mDifferExecutor = Executors.newSingleThreadExecutor();
    private final RecyclerView.Adapter mTargetAdapter;

    public AsyncDiffer(@NonNull RecyclerView.Adapter adapter) {
        this.mTargetAdapter = adapter;
    }

    public void apply(List<T> data){
        newData = data;
        calculate();
    }

    private void calculate(){
        mDifferExecutor.execute(() -> {
//            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(this,true);
//            oldSnapshot = newData;
//            result.dispatchUpdatesTo(mTargetAdapter);
        });
    }

}
