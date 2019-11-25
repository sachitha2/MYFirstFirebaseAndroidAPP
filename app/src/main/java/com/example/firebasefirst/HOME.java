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

    DatabaseReference databaseReference;
    //DATAABASE END



    FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.logout);

        //DATABASE Start

        databaseReference = FirebaseDatabase.getInstance().getReference("artist");

        edtTxtName = findViewById(R.id.editTextName);
        spinner = findViewById(R.id.spinner);
        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
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
            String id =  databaseReference.push().getKey();


            Artits artits = new Artits(id,name,genre);
            databaseReference.child(id).setValue(artits);
            Toast.makeText(HOME.this,"Artist Added",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(HOME.this, "You should enter a name", Toast.LENGTH_SHORT).show();
        }
    }
    //DATABASE END
}
