package com.example.kholis.smartparking;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class formTambahMobil extends AppCompatActivity {

    @BindView(R.id.tambahDataMobil)
    Button tambahDataMobil;
    @BindView(R.id.captureDepan)
    Button captureDepan;
    @BindView(R.id.captureBelakang)
    Button captureBelakang;
    @BindView(R.id.cariDepan)
    Button cariDepan;
    @BindView(R.id.cariBelakang)
    Button cariBelakang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_form_tambah_mobil);

        cariBelakang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

//    @OnClick(R.id.cariDepan)
//    public void cariDepan(){
//        Intent pickPhotoDepan = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickPhotoDepan , 1);
//    }
//
//    @OnClick(R.id.cariBelakang)
//    public void cariBelakang(){
//        Intent pickPhotoBelakang = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickPhotoBelakang , 1);
//    }
//
//    @OnClick(R.id.captureDepan)
//    public void captureDepan(){
//
//    }

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
