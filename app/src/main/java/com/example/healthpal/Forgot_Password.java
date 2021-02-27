package com.example.healthpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        emailEditText=(EditText)findViewById(R.id.email);
        Button resetPasswordButton=findViewById(R.id.resetPassword);
        firebaseAuth=FirebaseAuth.getInstance();
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }
    private void resetPassword()
    {
        String email=emailEditText.getText().toString().trim();
        if(email.isEmpty())
        {
            emailEditText.setError("Email is Required!");
            emailEditText.requestFocus();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Forgot_Password.this, "Check your Email to reset your Password!",Toast.LENGTH_LONG ).show();
                    return;
                }
                else
                {
                    Toast.makeText(Forgot_Password.this, "Try Again!",Toast.LENGTH_LONG ).show();
                    return;
                }
            }
        });


    }
}