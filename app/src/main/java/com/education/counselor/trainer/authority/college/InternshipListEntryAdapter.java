package com.education.counselor.trainer.authority.college;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.counselor.trainer.R;

import java.util.ArrayList;

public class InternshipListEntryAdapter extends RecyclerView.Adapter<InternshipList> {
    private Context c;
    private ArrayList<InternshipListEntryVo> details;

    InternshipListEntryAdapter(Context c, ArrayList<InternshipListEntryVo> details) {
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
    public InternshipList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_internship_list_adapter, viewGroup, false);
        return new InternshipList(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final InternshipList holder, int i) {
        holder.sname.setText(details.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }
}