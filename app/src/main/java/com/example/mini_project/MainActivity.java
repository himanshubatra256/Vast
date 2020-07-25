package com.example.mini_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button su;
    private TextView vast;
    private EditText t1;
    private EditText pass;
    private Button clear;
    private Button login,sc;
DatabaseReference rff,rffg;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
Members mem =new Members();
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        final IntentResult result =IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null)
        {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Scan completed")
                    .setMessage("ID: "+result.getContents())
                    .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //ClipboardManager manager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                          //  ClipData data= ClipData.newPlainText("result",result.getContents());
                        //    manager.setPrimaryClip(data);
                            mem.setUid(result.getContents());
                            Toast.makeText(MainActivity.this,"Please wait.",Toast.LENGTH_LONG).show();
                            Intent scanIntent =new Intent(MainActivity.this,scan.class);
                            startActivity(scanIntent);

                        }
                    }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        setupUIViews();
        sc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        IntentIntegrator ig= new IntentIntegrator(MainActivity.this);
        ig.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES );
        ig.setCameraId(0);
        ig.setOrientationLocked((false));
        ig.setPrompt("Scanning");
        ig.setBeepEnabled(true);
        ig.setBarcodeImageEnabled(true);
        ig.initiateScan();
    }
});
        t1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       clear.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               t1.setText("");
               pass.setText("");
           }
       });
       su.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              Intent in = new Intent(MainActivity.this,m.class);
             startActivity(in);
           }
       });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String user = t1.getText().toString();
               mem.setUid(user);
               checkemail();
           }


       });

    }

    private void checkinput() {
        if(!TextUtils.isEmpty(t1.getText()))
        {
            if(!TextUtils.isEmpty(pass.getText()))
            {
                login.setEnabled(true);
                login.setTextColor(Color.rgb(0,0,0));
            }else{
                login.setEnabled(false);
                login.setTextColor(Color.argb(50,0,0,0));
            }
        }
        else{
            login.setEnabled(false);
            login.setTextColor(Color.argb(50,0,0,0));
        }

    }

    private void checkemail() {
        if(t1.getText().toString().matches(emailPattern))
        {
            if(pass.getText().toString().length()>=8){
                login.setTextColor(Color.argb(50,0,0,0));
                login.setEnabled(false);
                mAuth.signInWithEmailAndPassword(t1.getText().toString(),pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(MainActivity.this,"Loading...",Toast.LENGTH_SHORT).show();

                                    Intent in = new Intent(MainActivity.this,scan_activity.class);
                                    startActivity(in);
                                }
                                else{
                                    login.setTextColor(Color.rgb(0,0,0));
                                    login.setEnabled(true);
                                    String err =task.getException().getMessage();
                                    Toast.makeText(MainActivity.this,err,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }else{
                pass.setError("Password atleast 8 characters");
            }
        }else{
            t1.setError("Invalid Email");
        }
    }

    private void setupUIViews() {
        mem= new Members();
        rff= FirebaseDatabase.getInstance().getReference().child("Members");
sc=(Button)findViewById(R.id.scan);
        su=(Button)findViewById(R.id.su);
        login=(Button)findViewById(R.id.login);
        pass=(EditText) findViewById(R.id.pass);
        t1=(EditText) findViewById(R.id.id);
        clear=(Button)findViewById(R.id.clr);
    }

}

