package com.education.counselor.trainer.admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.counselor.trainer.R;

import java.util.ArrayList;

public class CentersListEntryAdapter extends RecyclerView.Adapter<CentersList> {
    private Context c;
    private ArrayList<CentersListEntryVo> details;

    CentersListEntryAdapter(Context c, ArrayList<CentersListEntryVo> details) {
        setHasStableIds(true);
        this.c = c;
        this.details = details;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public CentersList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_centers_list_adapter, viewGroup, false);
        return new CentersList(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CentersList holder, int i) {
        holder.sname.setText(details.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return details.size();
    }
}
