package com.education.counselor.trainer.counsellor;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.education.counselor.trainer.R;

class StartupList extends RecyclerView.ViewHolder {
    TextView s_name;
    View v;

    @SuppressLint("SetTextI18n")
    StartupList(@NonNull View itemView) {
        super(itemView);
        s_name = itemView.findViewById(R.id.s_text);
        v = itemView.findViewById(R.id.cd);
    }
}