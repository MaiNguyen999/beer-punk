package com.example.punkbeer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {
    private EditText etEmail, etPassword;
    private Button btnSignIn;
    private TextView txtSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(this);
        txtSignUp = findViewById(R.id.txt_signUp);

        btnSignIn.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.txt_signUp:
                redirect(SignUp.class);
                break;
        }
    }
    private void redirect(Class differentActivity){
        Intent intent = new Intent(MainActivity.this, differentActivity);
        startActivity(intent);
        finish();
    }
    private void login(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        validate(email, password);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this);
    }
    private void validate(String email,String password){
        if(TextUtils.isEmpty(email)){
            etEmail.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            etPassword.setError("Enter your password");
            return;
        }
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            Toast.makeText(MainActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
            redirect(Dashboard.class);
        } else {
            Toast.makeText(MainActivity.this, "Sign in fail!", Toast.LENGTH_LONG).show();
        }
        progressDialog.dismiss();
    }
}