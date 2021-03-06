package com.education.counselor.trainer.login.authority.login;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.education.counselor.trainer.R;
import com.education.counselor.trainer.authority.college.AuthorityCollegeDashboardActivity;
import com.education.counselor.trainer.launcher.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
public class AuthorityCollegeLoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login, reset;
    DatabaseReference ref;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority_college_login);
        username = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        reset = findViewById(R.id.reset);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("")) {
                    username.requestFocus();
                    username.setError("This Is A Required Field");
                } else if (password.getText().toString().equals("")) {
                    password.requestFocus();
                    password.setError("This Is A Required Field");
                } else {
                    check(username.getText().toString());
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setCancelable(false);
                dialog.setMessage("RESET YOUR PASSWORD");
                LayoutInflater inflater = getLayoutInflater();
                @SuppressLint("InflateParams") final View v = inflater.inflate(R.layout.layout_alert, null);
                dialog.setView(v).setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText email = v.findViewById(R.id.email);
                        if (email.getText().toString().equals("")) {
                            email.requestFocus();
                            email.setError("This Is A Required Field");
                        } else {
                            reset(email.getText().toString());
                            reset.setEnabled(false);
                            Timer disable = new Timer();
                            disable.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            reset.setEnabled(true);
                                        }
                                    });
                                }
                            }, 60000);
                        }
                    }
                });
                dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(getBaseContext(), AuthorityCollegeDashboardActivity.class));
        }
    }
    public void login(String email) {
        final String pass = password.getText().toString();
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Authentication succeeded.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), AuthorityCollegeDashboardActivity.class));
                } else {
                    Toast.makeText(getBaseContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void check(final String email) {
        final boolean isEmail;
        isEmail = email.contains("@");
        ref = database.getReference("college_authority");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (isEmail) {
                        if (Objects.equals(snapshot.child("mail").getValue(String.class), email)) {
                            login(email);
                            return;
                        }
                    } else {
                        if (Objects.requireNonNull(snapshot.getKey()).equalsIgnoreCase(email)) {
                            login(Objects.requireNonNull(snapshot.child("mail").getValue()).toString());
                            return;
                        }
                    }
                }
                Toast.makeText(AuthorityCollegeLoginActivity.this, "Entered username doesn't exist", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AuthorityCollegeLoginActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void reset(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Password reset Link sent to your email", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getBaseContext(), "Check Your Email", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Reset Password Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}