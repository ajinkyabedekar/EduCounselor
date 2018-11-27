package com.education.counselor.trainer.authority.college.rating;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/*-------------------------------------------------------------------------------------------
 |     This file is for populating componetns of the Lists                                    |
 |---------------------------------------------------------------------------------------------
*/

import com.education.counselor.trainer.R;

import java.util.ArrayList;
public class RatingListEntryAdapter extends RecyclerView.Adapter<RatingList> {
    private Context c;
    private ArrayList<RatingListEntryVo> details;
    private RatingList holder;
    RatingListEntryAdapter(Context c, ArrayList<RatingListEntryVo> details) {
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
    public RatingList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_rating_list_adapter, viewGroup, false);
        return new RatingList(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final RatingList holder, int i) {
        this.holder = holder;
        holder.s_name.setText(details.get(i).getName());
        holder.rating.setRating(details.get(i).getRating());
        holder.v.setEnabled(false);
    }

    public float getRating() {
        return holder.rating.getRating();
    }
    @Override
    public int getItemCount() {
        return details.size();
    }
}
