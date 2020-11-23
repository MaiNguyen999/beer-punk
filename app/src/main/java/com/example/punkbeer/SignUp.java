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

public class SignUp extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {
    private EditText etEmail, etPassword, etConfirmPassword;
    private Button btnSignUp;
    private TextView txtSignIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirmPassword);
        btnSignUp = findViewById(R.id.btn_register);
        progressDialog = new ProgressDialog(this);
        txtSignIn = findViewById(R.id.txt_signIn);

        btnSignUp.setOnClickListener(this);
        txtSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_register:
                register();
                break;
            case R.id.txt_signIn:
                redirect(MainActivity.class);
                break;
        }
    }
    private void register(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmedPassword = etConfirmPassword.getText().toString();
        validate(email, password, confirmedPassword);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this);
    }
    private void validate(String email,String password,String confirmedPassword){
        if(TextUtils.isEmpty(email)){
            etEmail.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            etPassword.setError("Enter your password");
            return;
        }
        else if(TextUtils.isEmpty(confirmedPassword)){
            etConfirmPassword.setError("Confirm your password");
            return;
        }
        else if(!password.equals(confirmedPassword)){
            etConfirmPassword.setError("Password does not match");
            return;
        }
        else if(password.length() < 4){
            etPassword.setError("Length should be > 4");
            return;
        }
    }
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            Toast.makeText(SignUp.this, "Successfully registered", Toast.LENGTH_LONG).show();
            redirect(Dashboard.class);
        } else {
            Toast.makeText(SignUp.this, "Sign up fail!", Toast.LENGTH_LONG).show();
        }
        progressDialog.dismiss();
    }
    private void redirect(Class differentActivity){
        Intent intent = new Intent(SignUp.this, differentActivity);
        startActivity(intent);
        finish();
    }
}