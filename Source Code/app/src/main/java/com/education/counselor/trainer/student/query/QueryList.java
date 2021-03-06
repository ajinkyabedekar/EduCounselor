/*
*************************************
*      Author:Yogesh Sharma         *
*************************************
--------------------------------------------------------------------------------------------------------------------------
 |     Its an query activity detail class which shhow  details of the individual  query  of the registered student       |
  -------------------------------------------------------------------------------------------------------------------------

*/
package com.education.counselor.trainer.student.query;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.education.counselor.trainer.R;
class QueryList extends RecyclerView.ViewHolder {
    TextView s_name, p_name;
    private View v;
    @SuppressLint("SetTextI18n")
    QueryList(@NonNull View itemView) {
        super(itemView);
        s_name = itemView.findViewById(R.id.s_text);
        p_name = itemView.findViewById(R.id.p_name);
        v = itemView.findViewById(R.id.cd);
    }
    public TextView getS_name() {
        return s_name;
    }
    public void setS_name(TextView s_name) {
        this.s_name = s_name;
    }
    public TextView getP_name() {
        return p_name;
    }
    public void setP_name(TextView p_name) {
        this.p_name = p_name;
    }
    public View getV() {
        return v;
    }
    public void setV(View v) {
        this.v = v;
    }
}
