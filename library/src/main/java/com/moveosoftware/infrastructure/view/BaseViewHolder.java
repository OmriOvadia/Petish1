package com.moveosoftware.infrastructure.view;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ofer Dan-On on 1/20/2017.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void bind(T t, int position);
}
