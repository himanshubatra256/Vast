package com.example.mini_project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class m extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText t1;
    private EditText vid;
    private EditText pnum;
    private EditText id;
    private EditText password;
    private EditText cpass;
    private Button clear;
    private Button jmp;
    private Button reg;
    private TextView date1,daterc,dateins,datepol;
    Members mem = new Members();
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    FirebaseAuth mAuth =FirebaseAuth.getInstance();
    FirebaseFirestore firestore= FirebaseFirestore.getInstance();
    DatabaseReference reff;
    public String temp;
    public String drivedate;
    public String rcdate;
    public String insdate;
    public String polldate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        apply();

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker= new date();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
                date1.setText(temp);
                drivedate=temp;
                mem.setDriving(temp);
     }
        });
        daterc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker= new date();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
                daterc.setText(temp);
                rcdate=temp;
                mem.setRcertificate(temp);
            }
        });
        dateins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker= new date();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
                dateins.setText(temp);
                insdate=temp;
                mem.setInsurance(temp);
            }
        });
        datepol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker= new date();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
                datepol.setText(temp);
                polldate=temp;
                mem.setPoll(temp);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText("");
                vid.setText("");
                pnum.setText("");
                id.setText("");
                password.setText("");
                cpass.setText("");
            }
        });
        jmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(m.this, MainActivity.class);
                startActivity(in);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkemail();
            }
        });


    }

    private void checkemail() {
        if(!TextUtils.isEmpty(vid.getText().toString()) || (vid.getText().toString().length()>=6))
        {
            if(!TextUtils.isEmpty(password.getText().toString()) || (password.getText().toString().length()>=8))
            {
                if(!TextUtils.isEmpty(cpass.getText().toString()) || (cpass.getText().toString().equals(password.getText().toString())))
                {
                    if(!TextUtils.isEmpty(id.getText().toString()) || (id.getText().toString().matches(emailPattern)))
                    {
                        if(!TextUtils.isEmpty(pnum.getText().toString()) || (pnum.getText().toString().length()==10))
                        {
                            if(!TextUtils.isEmpty(drivedate))
                            {
                                if(!TextUtils.isEmpty(rcdate))
                                {
                                    if(!TextUtils.isEmpty(insdate))
                                    {
                                        if(!TextUtils.isEmpty(polldate))
                                        {
                                            reg.setEnabled(false);
                                            reg.setTextColor(Color.argb(50,0,0,0));
                                            Toast.makeText(m.this,"Loading please wait...",Toast.LENGTH_SHORT).show();
                                            mAuth.createUserWithEmailAndPassword(id.getText().toString(),password.getText().toString())
                                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if(task.isSuccessful())
                                                            {
                                                                mem.setUid(id.getText().toString());
                                                                Map<String,String> userdata=new HashMap<>();
                                                                userdata.put("Name",t1.getText().toString());
                                                                userdata.put("Vid",vid.getText().toString());
                                                                userdata.put("Password",password.getText().toString());
                                                                userdata.put("Phone",pnum.getText().toString());
                                                                userdata.put("ID",id.getText().toString());
                                                                userdata.put("drivedate",drivedate);
                                                                userdata.put("rcdate",rcdate);
                                                                userdata.put("insdate",insdate);
                                                                userdata.put("polldate",polldate);
                                                                String uid=mem.getPhoto().toLowerCase();
                                                                firestore.collection("USERS").document(uid).set(userdata)
                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                if(task.isSuccessful())
                                                                                {
                                                                                    Intent in = new Intent(m.this,scan_activity.class);
                                                                                    startActivity(in);
                                                                                }
                                                                                else{
                                                                                    reg.setEnabled(true);
                                                                                    reg.setTextColor(Color.rgb(0,0,0));
                                                                                    String err=task.getException().getMessage();
                                                                                    Toast.makeText(m.this,err,Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            }
                                                                        });

                                                            }else{
                                                                reg.setEnabled(true);
                                                                reg.setTextColor(Color.rgb(0,0,0));
                                                                String err=task.getException().getMessage();
                                                                Toast.makeText(m.this,err,Toast.LENGTH_SHORT).show();
                                                            }
                                                                            }
                                                                        });
                                        }else{
                                            datepol.setError("Cannot leave any field empty");
                                        }
                                    }else{
                                        dateins.setError("Cannot leave any field empty");
                                    }
                                }else{
                                    daterc.setError("Cannot leave any field empty");
                                }
                            }else{
                                date1.setError("Cannot leave any field empty");
                            }
                        }else{
                            pnum.setError("Only ten digit numbers");
                        }
                    }else{
                        id.setError("Invalid Email id");
                    }
                }else{
                    cpass.setError("Password should be same as above");
                }
            }else{
                password.setError("Atleast 8 characters");
            }
        }else{
            vid.setError("Atleast 4 characters");
        }

    }

    private void apply() {
        date1=(TextView)findViewById(R.id.date1);
        daterc=(TextView)findViewById(R.id.daterc);
        dateins=(TextView)findViewById(R.id.dateins);
        datepol=(TextView)findViewById(R.id.datepol);
        reff= FirebaseDatabase.getInstance().getReference().child("Members");
        t1 = (EditText) findViewById(R.id.t1);
        vid = (EditText) findViewById(R.id.vid);
        pnum = (EditText) findViewById(R.id.pnum);
        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        cpass = (EditText) findViewById(R.id.cpass);
        clear = (Button) findViewById(R.id.clear);
        jmp = (Button) findViewById(R.id.jmp);
        reg = (Button) findViewById(R.id.reg);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String str= DateFormat.getDateInstance().format(c.getTime());
        temp=str;
    }
}
