package com.example.workerhub;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.rxjava3.annotations.NonNull;


public class personal_info extends AppCompatActivity {


    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;

    // variable for Text view.


    private TextView userid, name, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);


        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get
        // reference for our database.
        databaseReference = firebaseDatabase.getReference("users");
        // initializing our object class variable.
        userid = findViewById(R.id.user_id_textview);
        name = findViewById(R.id.name_textview);
        email = findViewById(R.id.email_textview);


        // calling method
        // for getting data.
        getdata();


    }

    private void getdata() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        // Get the user ID
                        String userId = userSnapshot.getKey();

                        // Get the user details
                        String phone = userSnapshot.child("phone").getValue(String.class);
                        String fullName = userSnapshot.child("fullname").getValue(String.class);
                        String userEmail = userSnapshot.child("email").getValue(String.class);

                        // Set the values in the TextView fields
                        userid.setText(userId);
                        name.setText(fullName);
                        email.setText(userEmail);
                    }
                } else {
                    // Handle the case when the data doesn't exist
                    Toast.makeText(personal_info.this, "Data does not exist.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(personal_info.this, "Failed to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}