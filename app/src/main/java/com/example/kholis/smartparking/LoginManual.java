package com.example.kholis.smartparking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.example.kholis.smartparking.helper.ApiUtils;
import com.example.kholis.smartparking.helper.BaseApiService;
import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManual extends AppCompatActivity {
//    @BindView(R.id.reg_nama)EditText reg_nama;
//    @BindView(R.id.reg_email)EditText reg_email;
//    @BindView(R.id.reg_username)EditText reg_username;
//    @BindView(R.id.reg_pass)EditText reg_pass;
//    @BindView(R.id.btn_reg)Button btn_reg;

    EditText reg_nama, reg_email, reg_username, reg_pass,reg_alamat,reg_notelp;
    Button btn_reg;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manual);

        mContext = this;
        mApiService = ApiUtils.getAPIService();
        initComponents();
    }

    private void initComponents() {
        reg_nama = (EditText)findViewById(R.id.reg_nama);
        reg_email = (EditText)findViewById(R.id.reg_email);
        reg_username = (EditText)findViewById(R.id.reg_username);
        reg_pass = (EditText)findViewById(R.id.reg_pass);
        reg_alamat = (EditText)findViewById(R.id.reg_alamat);
        reg_notelp = (EditText)findViewById(R.id.reg_notelp);
        btn_reg = (Button)findViewById(R.id.btn_reg);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestRegister();
            }
        });
    }

    private void requestRegister() {
        mApiService.registerRequest(reg_nama.getText().toString(),
                reg_email.getText().toString(),
                reg_alamat.getText().toString(),
                reg_notelp.getText().toString(),
                reg_username.getText().toString(),
                reg_pass.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            try{
                                Log.i("debug","Berhasil. json : " + response.body().string());
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")){
                                    Toast.makeText(mContext,"Berhasil Registrasi, Masuk melalui halaman login", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                }else{
                                    String error_message = jsonResult.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Log.i("debug","Registrasi Gagal");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "Error >"+t.getMessage());
                        Toast.makeText(mContext, "Cek Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
