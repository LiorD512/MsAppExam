package com.dahanlior.msappexam.utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;


public abstract class BaseHolder <ITEM extends ListItem> extends RecyclerView.ViewHolder {

    protected abstract void fillViews(ITEM item);

    public BaseHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
