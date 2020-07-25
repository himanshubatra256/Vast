package com.example.mini_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class scan extends AppCompatActivity {
    private Button view;
    private TextView t1;
    private ImageView profile;
    Members mem= new Members();
    String id=mem.getPhoto().toLowerCase();
    FirebaseFirestore firestore;
    DocumentReference db;
    StorageReference sreff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        t1=(TextView)findViewById(R.id.t1);
        firestore=FirebaseFirestore.getInstance();
        db=firestore.collection("USERS").document(id);
        db.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if(documentSnapshot.exists())
                        {
                            String name = documentSnapshot.getString("Name");
                            String vid = documentSnapshot.getString("Vid");
                            String phone = documentSnapshot.getString("Phone");
                            String drivedate = documentSnapshot.getString("drivedate");
                            String insdate = documentSnapshot.getString("insdate");
                            String polldate = documentSnapshot.getString("polldate");
                            String rcdate = documentSnapshot.getString("rcdate");


                            t1.setText("User Name: "+name +"\n\nVehicle ID: "+vid+"\n\nPhone: "+phone+
                                    "\n\nLisence Valid upto: "+drivedate+"\n\nInsurance upto: "+insdate+
                                    "\n\nPollution Valid upto: "+polldate+"\n\nRC Valid upto: "+rcdate);
                        }
                        else
                        {
                            Toast.makeText(scan.this,"Document doesnt exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(scan.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        view=(Button)findViewById(R.id.view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(scan.this,photos.class);
                startActivity(in);
            }
        });

        sreff= FirebaseStorage.getInstance().getReference(id).child("profile_photo.jpg");
        profile=(ImageView)findViewById(R.id.profile);
        try {
            final File file = File.createTempFile("image","jpg");
            sreff.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bit = BitmapFactory.decodeFile(file.getAbsolutePath());
                    profile.setImageBitmap(bit);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(scan.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
