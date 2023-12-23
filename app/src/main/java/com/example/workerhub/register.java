package com.example.workerhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
    TextInputLayout fullname_var,email_var,phoneno_var,pswrd_var,conpswrd_var;

    // create object of database reference class to access firebase realtime database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://workerhub-b941a-default-rtdb.firebaseio.com/");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        fullname_var = findViewById(R.id.fullname_input_field);
        email_var = findViewById(R.id.email_input_field);
        phoneno_var = findViewById(R.id.phone_input_field);
        pswrd_var = findViewById(R.id.password_input_field);
        conpswrd_var = findViewById(R.id.con_password_field);

        final Button registerbtnclick = findViewById(R.id.registerbtnclick);
        final Button loginbtnclick = findViewById(R.id.loginbtnclick);


        registerbtnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //get data from edit texts into string variable

                final String fullnameTxt = fullname_var.getEditText().getText().toString();
                final String emailTxt = email_var.getEditText().getText().toString();
                final String phonnoTxt = phoneno_var.getEditText().getText().toString();
                final String pswrdTxt = pswrd_var.getEditText().getText().toString();
                final String conpswrdTxt = conpswrd_var.getEditText().getText().toString();

                // check if user fill all the fields before sending data to firebase

                if(fullnameTxt.isEmpty() || emailTxt.isEmpty() || phonnoTxt.isEmpty() || pswrdTxt.isEmpty()){
                    Toast.makeText(register.this, "Please fill the all fields", Toast.LENGTH_SHORT).show();
                }

                // check if password are matching with each other
                else if (!pswrdTxt.equals(conpswrdTxt)) {

                    Toast.makeText(register.this,"password are not matching",Toast.LENGTH_SHORT).show();

                }
                else {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // check if phone no is not registered before
                            if (snapshot.hasChild(phonnoTxt)){
                                Toast.makeText(register.this,"phone no is already registered",Toast.LENGTH_SHORT).show();

                            }

                            else {
                                //showing data to firebase realtime database
                                databaseReference.child("users").child(phonnoTxt).child("fullname").setValue(fullnameTxt);
                                databaseReference.child("users").child(phonnoTxt).child("email").setValue(emailTxt);
                                databaseReference.child("users").child(phonnoTxt).child("password").setValue(pswrdTxt);

                                Toast.makeText(register.this,"user register successfully",Toast.LENGTH_SHORT).show();
                                finish();


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });





                }
            }
        });

        loginbtnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}