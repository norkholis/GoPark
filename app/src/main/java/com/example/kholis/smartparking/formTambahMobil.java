package com.example.kholis.smartparking;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class formTambahMobil extends AppCompatActivity {

    @BindView(R.id.ftMobilBelakang)
    Button ftMobilBelakang;
    @BindView(R.id.ftMobilDepan)
    Button ftMobilDepan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_form_tambah_mobil);
    }

    @OnClick(R.id.ftMobilDepan)
    public void ftMobilDepan(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

    @OnClick(R.id.ftMobilBelakang)
    public void ftMobilBelakang(){
        Intent pickPhoto2 = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto2 , 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode){
            case 0:
                if (resultCode==RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                }
            break;
            case 1:
                if (requestCode==RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                }
                break;
        }
    }
}
