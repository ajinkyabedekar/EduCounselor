package com.education.counselor.trainer.admin.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.education.counselor.trainer.R;
import com.education.counselor.trainer.admin.AdminDashboardActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class AddStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText name, mobile_number, mail, total_fee, department, total_fee_submitted, date_of_fee_1, date_of_fee_2, date_of_fee_3, date_of_fee_4, payment_1, payment_2, payment_3, payment_4, reference_id_1, reference_id_2, reference_id_3, reference_id_4, student_id;
    Spinner payment_type, payment_1_mode, payment_2_mode, payment_3_mode, payment_4_mode, student_year;
    Button transfer_payment, submit;
    String status_of_payment = "PENDING";
    DatabaseReference db;
    long n;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name = findViewById(R.id.name);
        mobile_number = findViewById(R.id.mobile_number);
        mail = findViewById(R.id.mail);
        total_fee = findViewById(R.id.total_fee);
        department = findViewById(R.id.department);
        total_fee_submitted = findViewById(R.id.total_fee_submitted);
        date_of_fee_1 = findViewById(R.id.date_of_fee_1);
        date_of_fee_2 = findViewById(R.id.date_of_fee_2);
        date_of_fee_3 = findViewById(R.id.date_of_fee_3);
        date_of_fee_4 = findViewById(R.id.date_of_fee_4);
        payment_1 = findViewById(R.id.payment_1);
        payment_2 = findViewById(R.id.payment_2);
        payment_3 = findViewById(R.id.payment_3);
        payment_4 = findViewById(R.id.payment_4);
        reference_id_1 = findViewById(R.id.reference_id_1);
        reference_id_2 = findViewById(R.id.reference_id_2);
        reference_id_3 = findViewById(R.id.reference_id_3);
        reference_id_4 = findViewById(R.id.reference_id_4);
        student_id = findViewById(R.id.student_id);
        payment_type = findViewById(R.id.payment_type);
        payment_1_mode = findViewById(R.id.payment_1_mode);
        payment_2_mode = findViewById(R.id.payment_2_mode);
        payment_3_mode = findViewById(R.id.payment_3_mode);
        payment_4_mode = findViewById(R.id.payment_4_mode);
        student_year = findViewById(R.id.student_year);
        transfer_payment = findViewById(R.id.transfer_payment);
        submit = findViewById(R.id.submit);
        mAuth = FirebaseAuth.getInstance();
        payment_type.setOnItemSelectedListener(this);
        payment_1_mode.setOnItemSelectedListener(this);
        payment_2_mode.setOnItemSelectedListener(this);
        payment_3_mode.setOnItemSelectedListener(this);
        payment_4_mode.setOnItemSelectedListener(this);
        student_year.setOnItemSelectedListener(this);
        List<String> payment_type_list = new ArrayList<>();
        List<String> payment_mode_1_list = new ArrayList<>();
        List<String> payment_mode_2_list = new ArrayList<>();
        List<String> payment_mode_3_list = new ArrayList<>();
        List<String> payment_mode_4_list = new ArrayList<>();
        List<String> student_year_list = new ArrayList<>();
        payment_type_list.add("Payment Type");
        payment_type_list.add("Installment");
        payment_type_list.add("Full");
        payment_mode_1_list.add("Payment Mode 1");
        payment_mode_1_list.add("NEFT");
        payment_mode_1_list.add("PayTM");
        payment_mode_1_list.add("Online");
        payment_mode_1_list.add("Cash");
        payment_mode_2_list.add("Payment Mode 2");
        payment_mode_2_list.add("NEFT");
        payment_mode_2_list.add("PayTM");
        payment_mode_2_list.add("Online");
        payment_mode_2_list.add("Cash");
        payment_mode_3_list.add("Payment Mode 3");
        payment_mode_3_list.add("NEFT");
        payment_mode_3_list.add("PayTM");
        payment_mode_3_list.add("Online");
        payment_mode_3_list.add("Cash");
        payment_mode_4_list.add("Payment Mode 4");
        payment_mode_4_list.add("NEFT");
        payment_mode_4_list.add("PayTM");
        payment_mode_4_list.add("Online");
        payment_mode_4_list.add("Cash");
        student_year_list.add("Student Year");
        student_year_list.add("First");
        student_year_list.add("Second");
        student_year_list.add("Third");
        student_year_list.add("Fourth");
        ArrayAdapter<String> payment_type_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, payment_type_list);
        ArrayAdapter<String> payment_mode_1_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, payment_mode_1_list);
        ArrayAdapter<String> payment_mode_2_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, payment_mode_2_list);
        ArrayAdapter<String> payment_mode_3_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, payment_mode_3_list);
        ArrayAdapter<String> payment_mode_4_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, payment_mode_4_list);
        ArrayAdapter<String> student_year_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, student_year_list);
        payment_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment_mode_1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment_mode_2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment_mode_3_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment_mode_4_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        student_year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment_type.setAdapter(payment_type_adapter);
        payment_1_mode.setAdapter(payment_mode_1_adapter);
        payment_2_mode.setAdapter(payment_mode_2_adapter);
        payment_3_mode.setAdapter(payment_mode_3_adapter);
        payment_4_mode.setAdapter(payment_mode_4_adapter);
        student_year.setAdapter(student_year_adapter);
        transfer_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status_of_payment = "SUCCESS";
                Toast.makeText(getBaseContext(), "Payment Transferred Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        generate_random();
        student_id.setText(String.valueOf(n));
        student_id.setEnabled(false);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")) {
                    name.requestFocus();
                    name.setError("This Is A Required Field");
                } else if (mobile_number.getText().toString().equals("")) {
                    mobile_number.requestFocus();
                    mobile_number.setError("This Is A Required Field");
                } else if (mail.getText().toString().equals("")) {
                    mail.requestFocus();
                    mail.setError("This Is A Required Field");
                } else if (total_fee.getText().toString().equals("")) {
                    total_fee.requestFocus();
                    total_fee.setError("This Is A Required Field");
                } else if (department.getText().toString().equals("")) {
                    department.requestFocus();
                    department.setError("This Is A Required Field");
                } else if (total_fee_submitted.getText().toString().equals("")) {
                    total_fee_submitted.requestFocus();
                    total_fee_submitted.setError("This Is A Required Field");
                } else if (date_of_fee_1.getText().toString().equals("")) {
                    date_of_fee_1.requestFocus();
                    date_of_fee_1.setError("This Is A Required Field");
                } else if (date_of_fee_2.getText().toString().equals("")) {
                    date_of_fee_2.requestFocus();
                    date_of_fee_2.setError("This Is A Required Field");
                } else if (date_of_fee_3.getText().toString().equals("")) {
                    date_of_fee_3.requestFocus();
                    date_of_fee_3.setError("This Is A Required Field");
                } else if (date_of_fee_4.getText().toString().equals("")) {
                    date_of_fee_4.requestFocus();
                    date_of_fee_4.setError("This Is A Required Field");
                } else if (payment_1.getText().toString().equals("")) {
                    payment_1.requestFocus();
                    payment_1.setError("This Is A Required Field");
                } else if (payment_2.getText().toString().equals("")) {
                    payment_2.requestFocus();
                    payment_2.setError("This Is A Required Field");
                } else if (payment_3.getText().toString().equals("")) {
                    payment_3.requestFocus();
                    payment_3.setError("This Is A Required Field");
                } else if (payment_4.getText().toString().equals("")) {
                    payment_4.requestFocus();
                    payment_4.setError("This Is A Required Field");
                } else if (payment_type.getSelectedItem().toString().equals("Payment Type")) {
                    payment_type.requestFocus();
                    Toast.makeText(getBaseContext(), "Please Select A Payment Type", Toast.LENGTH_SHORT).show();
                } else if (payment_1_mode.getSelectedItem().toString().equals("Payment Mode 1")) {
                    payment_type.requestFocus();
                    Toast.makeText(getBaseContext(), "Please Select A Payment Mode 1", Toast.LENGTH_SHORT).show();
                } else if (payment_2_mode.getSelectedItem().toString().equals("Payment Mode 2")) {
                    payment_type.requestFocus();
                    Toast.makeText(getBaseContext(), "Please Select A Payment Mode 2", Toast.LENGTH_SHORT).show();
                } else if (payment_3_mode.getSelectedItem().toString().equals("Payment Mode 3")) {
                    payment_type.requestFocus();
                    Toast.makeText(getBaseContext(), "Please Select A Payment Mode 3", Toast.LENGTH_SHORT).show();
                } else if (payment_4_mode.getSelectedItem().toString().equals("Payment Mode 4")) {
                    payment_type.requestFocus();
                    Toast.makeText(getBaseContext(), "Please Select A Payment Mode 4", Toast.LENGTH_SHORT).show();
                } else if (reference_id_1.getText().toString().equals("")) {
                    reference_id_1.requestFocus();
                    reference_id_1.setError("This Is A Required Field");
                } else if (reference_id_2.getText().toString().equals("")) {
                    reference_id_2.requestFocus();
                    reference_id_2.setError("This Is A Required Field");
                } else if (reference_id_3.getText().toString().equals("")) {
                    reference_id_3.requestFocus();
                    reference_id_3.setError("This Is A Required Field");
                } else if (reference_id_4.getText().toString().equals("")) {
                    reference_id_4.requestFocus();
                    reference_id_4.setError("This Is A Required Field");
                } else if (student_id.getText().toString().equals("")) {
                    student_id.requestFocus();
                    student_id.setError("This Is A Required Field");
                } else if (student_year.getSelectedItem().toString().equals("Student Year")) {
                    student_year.requestFocus();
                    Toast.makeText(getBaseContext(), "Please Select A Student Year", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("student").child(student_id.getText().toString());
                    myRef.child("name").setValue(name.getText().toString());
                    myRef.child("mobile_number").setValue(mobile_number.getText().toString());
                    myRef.child("mail").setValue(mail.getText().toString());
                    myRef.child("total_fee").setValue(total_fee.getText().toString());
                    myRef.child("department").setValue(department.getText().toString());
                    myRef.child("total_fee_submitted").setValue(total_fee_submitted.getText().toString());
                    myRef.child("date_of_fee_1").setValue(date_of_fee_1.getText().toString());
                    myRef.child("date_of_fee_2").setValue(date_of_fee_2.getText().toString());
                    myRef.child("date_of_fee_3").setValue(date_of_fee_3.getText().toString());
                    myRef.child("date_of_fee_4").setValue(date_of_fee_4.getText().toString());
                    myRef.child("payment_1").setValue(payment_1.getText().toString());
                    myRef.child("payment_2").setValue(payment_2.getText().toString());
                    myRef.child("payment_3").setValue(payment_3.getText().toString());
                    myRef.child("payment_4").setValue(payment_4.getText().toString());
                    myRef.child("payment_type").setValue(payment_type.getSelectedItem().toString());
                    myRef.child("payment_1_mode").setValue(payment_1_mode.getSelectedItem().toString());
                    myRef.child("payment_2_mode").setValue(payment_2_mode.getSelectedItem().toString());
                    myRef.child("payment_3_mode").setValue(payment_3_mode.getSelectedItem().toString());
                    myRef.child("payment_4_mode").setValue(payment_4_mode.getSelectedItem().toString());
                    myRef.child("payment_1_reference_id").setValue(reference_id_1.getText().toString());
                    myRef.child("payment_2_reference_id").setValue(reference_id_2.getText().toString());
                    myRef.child("payment_3_reference_id").setValue(reference_id_3.getText().toString());
                    myRef.child("payment_4_reference_id").setValue(reference_id_4.getText().toString());
                    myRef.child("student_year").setValue(student_year.getSelectedItem().toString());
                    myRef.child("payment_status").setValue(status_of_payment);
                    mAuth.createUserWithEmailAndPassword(mail.getText().toString(), student_id.getText().toString());
                    Toast.makeText(getBaseContext(), "Student Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), AdminDashboardActivity.class));
                }
            }
        });
    }
    private void generate_random() {
        n = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        validate_random();
    }
    private void validate_random() {
        db = FirebaseDatabase.getInstance().getReference("student");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (Objects.equals(ds.getKey(), String.valueOf(n))) {
                        generate_random();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}