package com.example.mini_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class photos extends AppCompatActivity {
    StorageReference s1 ;
    StorageReference s2;
    StorageReference s3;
    StorageReference s4;
    private ImageView i1;
    private ImageView i2;
    private ImageView i3;
    private ImageView i4;
    Members mem= new Members();
    String id=mem.getPhoto().toLowerCase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        Toast.makeText(photos.this,"Loading...",Toast.LENGTH_SHORT).show();

        s1= FirebaseStorage.getInstance().getReference(id).child("dl.jpg");
        s2= FirebaseStorage.getInstance().getReference(id).child("ins.jpg");
        s3= FirebaseStorage.getInstance().getReference(id).child("pollution.jpg");
        s4= FirebaseStorage.getInstance().getReference(id).child("rc.jpg");

        i1=(ImageView)findViewById(R.id.i1);
        try{
            final File file = File.createTempFile("image","jpg");
            s1.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    i1.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(photos.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        i2=(ImageView)findViewById(R.id.i2);
        try{
            final File file = File.createTempFile("image","jpg");
            s2.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    i2.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(photos.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        i3=(ImageView)findViewById(R.id.i3);
        try{
            final File file = File.createTempFile("image","jpg");
            s3.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    i3.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(photos.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        i4=(ImageView)findViewById(R.id.i4);
        try{
            final File file = File.createTempFile("image","jpg");
            s4.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    i4.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(photos.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
