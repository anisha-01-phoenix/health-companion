package com.example.healthpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity1 extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        EditText txtName= findViewById(R.id.txt_name);

        EditText txtEmail= findViewById(R.id.txt_email);
        EditText txtPassword= findViewById(R.id.txt_password);
        EditText txtConfirmPassword= findViewById(R.id.txt_confirmpassword);
        Button button3=findViewById(R.id.button3);
        firebaseAuth=FirebaseAuth.getInstance();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email= txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();
                String name=txtName.getText().toString().trim();
                String ConfirmPassword=txtConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name))
                {
                    Toast.makeText(Activity1.this,"Enter Name",Toast.LENGTH_SHORT) .show();
                    return;
                }
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(Activity1.this,"Enter Email id",Toast.LENGTH_SHORT) .show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(Activity1.this,"Enter Password",Toast.LENGTH_SHORT) .show();
                    return;
                }
                if (TextUtils.isEmpty(ConfirmPassword))
                {
                    Toast.makeText(Activity1.this,"Confirm Password",Toast.LENGTH_SHORT) .show();
                    return;
                }
                if(password.length()<8)
                {
                    Toast.makeText(Activity1.this,"Password must contain atleast 8 characters",Toast.LENGTH_SHORT) .show();
                    return;
                }
                if(password.equals(ConfirmPassword))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(Activity1.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                        Toast.makeText(Activity1.this,"Welcome to Healthpal!",Toast.LENGTH_SHORT) .show();
                                    }
                                    else
                                    {
                                        Toast.makeText(Activity1.this, "Authentication Failed!", Toast.LENGTH_SHORT) .show();
                                    }
                                }
                            });
                }
            }
        });
    }

}