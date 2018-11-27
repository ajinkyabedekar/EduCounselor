/*
*************************************
*      Author:Yogesh Sharma         *
*************************************
  -----------------------------------------------------------------------------------------------------
 |     Its a Placementadapter activity where it is used to view the list of  all Placement student did |
  -----------------------------------------------------------------------------------------------------

*/
package com.education.counselor.trainer.student.placement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.counselor.trainer.R;

import java.util.ArrayList;
public class PlacementListEntryAdapter extends RecyclerView.Adapter<PlacementList> {
    private Context c;
    private ArrayList<PlacementListEntryVo> details;
    PlacementListEntryAdapter(Context c, ArrayList<PlacementListEntryVo> details) {
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
    public PlacementList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_placement_list_adapter3, viewGroup, false);
        return new PlacementList(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final PlacementList holder, int i) {
        holder.s_name.setText(details.get(i).getName());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(c, PlacementDetailsActivity.class);
                c.startActivity(in);
            }
        });
    }
    @Override
    public int getItemCount() {
        return details.size();
    }
}
