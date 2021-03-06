package com.education.counselor.trainer.admin.centers.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.education.counselor.trainer.R;
import com.education.counselor.trainer.admin.centers.AddCentersActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;
//**Main class for displaying centers**//
public class CentersListActivity extends AppCompatActivity {
    
    //variables initialization
    Button add_center;
    RecyclerView recyclerView;
    DatabaseReference db;
    CentersListEntryAdapter adapter;
    ProgressBar pg;
    Context mContext;
    private ArrayList<CentersListEntryVo> details = new ArrayList<>();
    
    /***
    This method is called at the start of the activity
    This method is also responsible for inflating layout 
    in the activity.
    ****/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centers_list);
        add_center = findViewById(R.id.add_center);
        
        //Add_center button Onclick listener
        add_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This function intents to another activity
                startActivity(new Intent(getBaseContext(), AddCentersActivity.class));
            }
        });
        mContext = this;
        pg = findViewById(R.id.progress);
        //recycler view initialization
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       
        /**
        firebase database instance initialization
        taking reference as "centers" from firebase 
        **/
        db = FirebaseDatabase.getInstance().getReference("centers");
        pg.setVisibility(View.VISIBLE);
        
        //This listener gets invoked on data change 
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    CentersListEntryVo s = new CentersListEntryVo();
                    s.setName(Objects.requireNonNull(ds.child("name").getValue()).toString());
                    details.add(s);
                }
                if (details.size() == 0) {
                    Toast.makeText(getBaseContext(), "No Centers Found", Toast.LENGTH_SHORT).show();
                }
                adapter = new CentersListEntryAdapter(mContext, details);
                pg.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
