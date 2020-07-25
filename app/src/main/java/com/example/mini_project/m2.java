package com.example.mini_project;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class m2 extends AppCompatActivity {
private ImageView dp,ins,picrc,pimageView,pol;
private Button b1,b2,brc,b3,pbutton,bpol;
StorageReference sreff;
DatabaseReference dreff;
private Uri dl,rc,insu,pimg,pollution;
Members mem= new Members();
String pid=mem.getPhoto().toLowerCase();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2);
        apply();
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               filechooser(1);
           }
       });
       brc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               filechooser(2);
           }
       });
       b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               filechooser(3);
           }
       });
       pbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               filechooser(4);
           }
       });
        bpol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filechooser(5);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });


    }

    private void apply() {

        bpol=(Button)findViewById(R.id.bpol);
        pol=(ImageView)findViewById(R.id.pol);
        b1=(Button)findViewById(R.id.b1);
        pbutton=(Button)findViewById(R.id.pbutton);
        b2=(Button)findViewById(R.id.b2);
        brc=(Button)findViewById(R.id.brc);
        b3=(Button)findViewById(R.id.b3);
        dp=(ImageView)findViewById(R.id.dp);
        picrc=(ImageView)findViewById(R.id.picrc);
        ins=(ImageView)findViewById(R.id.ins);
        pimageView=(ImageView)findViewById(R.id.dphoto);

        dreff= FirebaseDatabase.getInstance().getReference().child("Members");
        sreff= FirebaseStorage.getInstance().getReference().child(pid);
    }


    private void filechooser(int a)
    {
        Intent gal= new Intent();
        gal.setType("image/*");
        gal.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(gal,a);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK&& data!= null && data.getData()!=null)
        {
            dl =data.getData();
            dp.setImageURI(dl);
        }
       if(requestCode==2 && resultCode==RESULT_OK&& data!= null && data.getData()!=null)
        {
            rc =data.getData();
            picrc.setImageURI(rc);
        }
        if(requestCode==3 && resultCode==RESULT_OK&& data!= null && data.getData()!=null)
        {
            insu =data.getData();
            ins.setImageURI(insu);
        }
        if(requestCode==4 && resultCode==RESULT_OK&& data!= null && data.getData()!=null)
        {
            pimg =data.getData();
            pimageView.setImageURI(pimg);
        }
        if(requestCode==5 && resultCode==RESULT_OK&& data!= null && data.getData()!=null)
        {
            pollution =data.getData();
            pol.setImageURI(pollution);
        }

    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));}

    private void upload(){
        if(dp!=null||rc!=null||insu!=null||pimg!=null||pollution!=null)
        {



            StorageReference fileReference =sreff.child("dl." + getFileExtension(dl));
            fileReference.putFile(dl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(m2.this,"Upload sucessful",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(m2.this,"Upload failed",Toast.LENGTH_LONG).show();
                        }
                    });
      sreff.child("rc." + getFileExtension(rc)).putFile(rc)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(m2.this,"Upload sucessful",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(m2.this,"Upload failed",Toast.LENGTH_LONG).show();
                        }
                    });
           sreff.child("ins." + getFileExtension(insu)).putFile(insu)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(m2.this,"Upload sucessful",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(m2.this,"Upload failed",Toast.LENGTH_LONG).show();
                        }
                    });
           sreff.child("profile_photo."+ getFileExtension(pimg)).putFile(pimg)
                   .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           Toast.makeText(m2.this,"Upload sucessful",Toast.LENGTH_LONG).show();
                       }
                   })
                   .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(m2.this,"Upload failed",Toast.LENGTH_LONG).show();
                       }
                   });
sreff.child("pollution."+getFileExtension(pollution)).putFile(pollution)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(m2.this,"Upload sucessful",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(m2.this,"Upload failed ",Toast.LENGTH_LONG).show();

    }
});

        }
        else {

            Toast.makeText(m2.this, "Cannot leave any field empty", Toast.LENGTH_LONG).show();
        }
    }
}
