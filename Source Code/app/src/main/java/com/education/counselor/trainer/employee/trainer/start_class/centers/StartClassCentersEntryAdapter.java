package com.education.counselor.trainer.employee.trainer.start_class.centers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.counselor.trainer.R;
import com.education.counselor.trainer.employee.trainer.start_class.courses.StartClassCoursesActivity;

import java.util.ArrayList;
public class StartClassCentersEntryAdapter extends RecyclerView.Adapter<StartClassCenters> {
    private Context c;
    private ArrayList<StartClassCentersEntryVo> details;
    StartClassCentersEntryAdapter(Context c, ArrayList<StartClassCentersEntryVo> details) {
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
    public StartClassCenters onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_start_class_centers_adapter, viewGroup, false);
        return new StartClassCenters(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final StartClassCenters holder, int i) {
        holder.s_name.setText(details.get(i).getName());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(c, StartClassCoursesActivity.class);
                in.putExtra("name", details.get(holder.getAdapterPosition()).getName());
                c.startActivity(in);
            }
        });
    }
    @Override
    public int getItemCount() {
        return details.size();
    }
}