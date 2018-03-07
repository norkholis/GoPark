package com.example.kholis.smartparking;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kholis.smartparking.helper.SharedPrefManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;


public class PhoneNumberAuthActivity extends AppCompatActivity {

    @BindView(R.id.btnVerif)
    Button btnVerif;
    @BindView(R.id.btnVerifToRegister)
    Button btnVerifToRegister;
    @BindView(R.id.nomerTelp)
    EditText nomerTelp;
    @BindView(R.id.kodeVerif)
    EditText kodeVerif;

    SharedPrefManager sharedPrefManager;

    FirebaseAuth mFirebaseAuth;
    private View view;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getSpSudahLogin()){
            Intent i = new Intent(PhoneNumberAuthActivity.this, DashBoardActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);;
            startActivity(i);
            finish();
        }

        btnVerifToRegister.setEnabled(false);

        mFirebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_phone_number_auth);

    }

    @OnClick(R.id.btnVerif)
    public void btnVerif(){
        sendCode(view);
    }

    @OnClick(R.id.btnVerifToRegister)
    public void btnVerifToRegister(){
        verifyCode(view);
    }

    public void sendCode(View view){
        String phoneNumber = nomerTelp.getText().toString();
        setupCallback();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallback
        );
    }

    private void setupCallback(){
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException){
                    Toast.makeText(PhoneNumberAuthActivity.this, "Invalid request", Toast.LENGTH_SHORT).show();
                }else if(e instanceof FirebaseTooManyRequestsException){
                    Toast.makeText(PhoneNumberAuthActivity.this, "The SMS quota for the project has been exceeded", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                mVerificationId = s;
                resendToken = forceResendingToken;

                btnVerifToRegister.setEnabled(true);
            }
        };
    }

    private void verifyCode(View view){
        String getKodeVerif = kodeVerif.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, getKodeVerif);

        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();
                    Intent i = new Intent(PhoneNumberAuthActivity.this, LoginManual.class)
                            .putExtra("phoneNumber", user.getPhoneNumber().toString());
                    startActivity(i);
                    finish();
                }else {
                    if (task.getException()instanceof FirebaseAuthInvalidCredentialsException){

                    }
                }
            }
        });
    }
}



// Deprecated

//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        if (auth.getCurrentUser() != null ){
//            if (!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty()){
//                Intent i = new Intent(this, LoginActivity.class)
//                        .putExtra("phone", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
//                startActivity(i);
//                finish();
//            }
//            else{
//                startActivityForResult(AuthUI.getInstance()
//                .createSignInIntentBuilder().setAvailableProviders(
//                        Arrays.asList(
//                        new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
//                        )).build(),REQUEST_LOGIN);
//            }
//        }