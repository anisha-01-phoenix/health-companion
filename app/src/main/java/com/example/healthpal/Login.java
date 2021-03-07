package com.example.healthpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView forgotpassword=findViewById(R.id.forgot_password);
        Button button= findViewById(R.id.button);
        EditText txtEmail=findViewById(R.id.txt_Email);
        EditText txtPassword=findViewById(R.id.txt_Password);
        Button btn_login=findViewById(R.id.btn_login);
        firebaseAuth=FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Login.this,"Enter Email id",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Login.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<8)
                {
                    Toast.makeText(Login.this,"Password must contain at least 8 characters",Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                                    Toast.makeText(Login.this,"Successfully Logged In!",Toast.LENGTH_SHORT) .show();
                                }
                                else
                                {
                                    Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT) .show();
                                }
                            }
                        });
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgot_Password();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
    }
    public void openActivity1(){
        Intent intent=new Intent(this, Signup.class);
        startActivity(intent);
    }
    public void openForgot_Password()
    {
        Intent intent =new Intent(this,Forgot_Password.class);
        startActivity(intent);
    }
}