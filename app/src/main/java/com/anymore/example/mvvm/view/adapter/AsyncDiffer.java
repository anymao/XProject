package com.anymore.example.mvvm.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import com.anymore.example.app.executors.AppExecutors;

import java.util.List;

/**
 * Created by anymore on 2019/5/18.
 */
@Deprecated
public abstract class AsyncDiffer<T> extends DiffUtil.ItemCallback<T> {
    private List<T> oldSnapshot;
    private List<T> newData;

    private final RecyclerView.Adapter mTargetAdapter;

    public AsyncDiffer(@NonNull RecyclerView.Adapter adapter) {
        this.mTargetAdapter = adapter;
    }

    public void apply(List<T> data) {
        newData = data;
        calculate();
    }

    private void calculate() {
        AppExecutors.INSTANCE.getDiskIoExecutor().execute(() -> {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return oldSnapshot != null ? oldSnapshot.size() : 0;
                }

                @Override
                public int getNewListSize() {
                    return newData != null ? newData.size() : 0;
                }

                @Override
                public boolean areItemsTheSame(int i, int i1) {
                    T oldItem = oldSnapshot.get(i);
                    T newItem = newData.get(i1);
                    return AsyncDiffer.this.areItemsTheSame(oldItem, newItem);
                }

                @Override
                public boolean areContentsTheSame(int i, int i1) {
                    T oldItem = oldSnapshot.get(i);
                    T newItem = newData.get(i1);
                    return AsyncDiffer.this.areContentsTheSame(oldItem, newItem);
                }
            }, true);
            oldSnapshot = newData;
            AppExecutors.INSTANCE.getMainExecutor().execute(() -> result.dispatchUpdatesTo(mTargetAdapter));
        });
    }

}
