package com.education.counselor.trainer.counsellor;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.education.counselor.trainer.R;

class ResponsibleCentersList extends RecyclerView.ViewHolder {
    TextView sname;
    View v;

    @SuppressLint("SetTextI18n")
    ResponsibleCentersList(@NonNull View itemView) {
        super(itemView);
        sname = itemView.findViewById(R.id.c_text);
        v = itemView.findViewById(R.id.cd);
    }
}