/*
 * Copyright (c)
 * Created by Marcis Šic on 12.3.18 11:37
 */

package com.dahanlior.msappexam.utils;

import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ViewHolder {

    private Unbinder unbinder;
    public View itemView;


    public ViewHolder(View v) {
        itemView = v;
        unbinder = ButterKnife.bind(this, v);
    }

}
