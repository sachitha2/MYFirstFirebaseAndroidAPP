package com.example.firebasefirst;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    public EditText emailId,pass;
    public Button btn;
    public TextView text;
    FirebaseAuth mFirebaseAuth;

    private  FirebaseAuth.AuthStateListener mAuthStateLister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.edtUsername);
        pass = findViewById(R.id.edtPassword);

        text = findViewById(R.id.text);

        btn = findViewById(R.id.btnRegister);


        mAuthStateLister = new FirebaseAuth.AuthStateListener() {



            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(Login.this,"You are loged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this,HOME.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Login.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String password = pass.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Please enter email");
                    emailId.requestFocus();
                }else if(password.isEmpty()){
                    pass.setError("Enter a password");
                    pass.requestFocus();
                }else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(Login.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this,"Login error,Please login again",Toast.LENGTH_SHORT).show();
                            }else{
                                Intent i = new Intent(Login.this,HOME.class);
                                startActivity(i);
                            }
                        }
                    });
                }
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,MainActivity.class);
                startActivity(i);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateLister);
    }
}
