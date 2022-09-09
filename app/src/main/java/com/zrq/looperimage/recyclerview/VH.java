package com.zrq.looperimage.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public final class VH<T extends ViewBinding> extends RecyclerView.ViewHolder {

    private final T mBinding;

    public VH(@NonNull T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}