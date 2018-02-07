package com.example.kholis.smartparking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kholis.smartparking.helper.ApiUtils;
import com.example.kholis.smartparking.helper.BaseApiService;
import com.example.kholis.smartparking.helper.Helper;
import com.example.kholis.smartparking.helper.SharedPrefManager;
import com.example.kholis.smartparking.model.APIUser;
import com.example.kholis.smartparking.model.ListUser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 2;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;

    private ArrayList<APIUser> userList;

    SharedPrefManager sharedPrefManager;

    Context mContext;
    BaseApiService mApiService;

    EditText userid, pass;
    Button btn_login;
    @BindView(R.id.btn_loginManual)Button btn_loginManual;
    @BindView(R.id.btnGmail)SignInButton btnGmail;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        ButterKnife.bind(this);

        userList = new ArrayList<>();

        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getSpSudahLogin()){
            Intent i = new Intent(LoginActivity.this, DashBoardActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);;
            startActivity(i);
            finish();
        }

        mAuth = FirebaseAuth.getInstance();

        mContext = this;
        mApiService = ApiUtils.getAPIService();
        initComponents();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                }
            }
        };

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener(){
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                    .build();



        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void initComponents() {
        userid = (EditText)findViewById(R.id.userid);
        pass = (EditText)findViewById(R.id.pass);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestLogin();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.login_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }

    @OnClick(R.id.btnGmail)
    public void btnGmail(View view){
        signIn();
    }



    @OnClick(R.id.btn_loginManual)
    public void btn_loginManual(View view){
        Intent to_LoginManual = new Intent(LoginActivity.this, LoginManual.class);
        startActivity(to_LoginManual);
        finish();
    }

    private void requestLogin(){
        mApiService.loginRequest(userid.getText().toString(), pass.getText().toString())
                .enqueue(new Callback<ListUser>() {
                    @Override
                    public void onResponse(Call<ListUser> call, Response<ListUser> response) {
                        if (response.isSuccessful()){
                            userList = response.body().getData();

                            if (userList != null){
                                APIUser user =userList.get(0);
                                String nama = user.getNamaLengkap().toString();
                                String token = user.getToken().toString();
                                int id = user.getId();
                                //String foto = user.getFoto().toString();

                                sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA,nama);
                                sharedPrefManager.saveSPInt(SharedPrefManager.SP_ID, id);
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, token);
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                                Intent i = new Intent(LoginActivity.this, DashBoardActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                Bundle extras = new Bundle();
                                extras.putString("nama_lengkap", nama);
                                extras.putString("token", token);
                                i.putExtras(extras);
                                startActivity(i);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ListUser> call, Throwable t) {

                    }
                });

    }
}



//Documentation for sign using sharepref
//        final String in="";
//        mApiService.loginRequest(userid.getText().toString(),pass.getText().toString())
//                .enqueue(new Callback<APIUser>() {
//                    @Override
//                    public void onResponse(Call<APIUser> call, Response<APIUser> response) {
//                        if (response.isSuccessful()){
//                            //jsonResult.getString("error").equals("false")
//                            try {
//                                JSONObject jsonResult = new JSONObject(response.body().getNamaLengkap());
//                                //JSONObject jsonResult = new JSONObject(in);
//                                //JSONObject all = jsonResult.getJSONObject("");
//                                if (jsonResult != null){
//                                    Toast.makeText(mContext,"Login Berhasil", Toast.LENGTH_SHORT).show();
//                                    String nama = jsonResult.getString("nama_lengkap");
//                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
//                                    // Shared Pref ini berfungsi untuk menjadi trigger session login
//                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
//                                    Intent intent = new Intent(mContext, DashBoardActivity.class);
//                                    intent.putExtra("result_nama", nama);
//                                    startActivity(intent);
//                                }else{
//                                    String error_message = jsonResult.getString("error_msg");
//                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                                }
//                            }catch (JSONException e){
//                                e.printStackTrace();
//                            }catch (JsonIOException e){
//                                e.printStackTrace();
//                            }
//                        }else {
//                            //Do Nothing
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<APIUser> call, Throwable t) {
//
//                    }
//
//                });



//    @OnClick(R.id.btn_login)
//    public void btn_login(View view){
//        requestLogin();
////        Intent toDashBoard = new Intent(LoginActivity.this, DashBoardActivity.class);
////
////        toDashBoard.putExtra("userid", userid.getText().toString());
////        toDashBoard.putExtra("password", pass.getText().toString());
////
////        startActivity(toDashBoard);
//    }



//        sharedPrefManager = new SharedPrefManager(this);
//
//
//
//        if (sharedPrefManager.getSpSudahLogin()){
//            startActivity(new Intent(LoginActivity.this, DashBoardActivity.class)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            finish();
//        }

//        String namaSP = Helper.getActiveUser().getNamaLengkap();
//        if (namaSP!=null){
//            startActivity(new Intent(LoginActivity.this, DashBoardActivity.class)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            finish();
//        }