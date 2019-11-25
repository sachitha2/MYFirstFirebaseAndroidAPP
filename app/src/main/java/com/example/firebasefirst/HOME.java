package com.example.firebasefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HOME extends AppCompatActivity {
    Button logout;

    //DATAABASE START
    EditText edtTxtName;
    Spinner spinner;
    Button btnSave;
    //DATAABASE END



    FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.logout);

        //DATABASE Start
        edtTxtName = findViewById(R.id.editTextName);
        spinner = findViewById(R.id.spinner);
        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Database END



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();

                Intent i = new Intent(HOME.this,Login.class);
                startActivity(i);
            }
        });


        //DATABASE START

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("B");

        myRef.setValue("2588!");
        myRef = database.getReference("A");
        myRef.setValue("25!");

        //DATABASE END
    }


    //DATABASE START
    private void addArtist(){
        String name = edtTxtName.getText().toString();
        String genre = spinner.getSelectedItem().toString();


        if(!TextUtils.isEmpty(name)){

        }else{
            Toast.makeText(HOME.this, "You should enter a name", Toast.LENGTH_SHORT).show();
        }
    }
    //DATABASE END
}
