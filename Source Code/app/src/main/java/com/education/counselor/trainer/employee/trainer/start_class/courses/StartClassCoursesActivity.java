package com.education.counselor.trainer.employee.trainer.start_class.courses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.education.counselor.trainer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;
public class StartClassCoursesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference db;
    StartClassCoursesEntryAdapter adapter;
    ProgressBar pg;
    Context mContext;
    String center = "";
    private ArrayList<StartClassCoursesEntryVo> details = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_class_courses);
        Intent i = getIntent();
        if (i.hasExtra("name")) {
            center = i.getStringExtra("name");
        }
        mContext = this;
        pg = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseDatabase.getInstance().getReference("centers");
        pg.setVisibility(View.VISIBLE);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (Objects.equals(ds.child("name").getValue(String.class), center)) {
                        for (DataSnapshot d : ds.child("courses").getChildren()) {
                            StartClassCoursesEntryVo s = new StartClassCoursesEntryVo();
                            s.setName(Objects.requireNonNull(d.child("name").getValue()).toString());
                            details.add(s);
                        }
                    }
                }
                if (details.size() == 0) {
                    Toast.makeText(getBaseContext(), "No Courses Found", Toast.LENGTH_SHORT).show();
                }
                adapter = new StartClassCoursesEntryAdapter(mContext, details, center);
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