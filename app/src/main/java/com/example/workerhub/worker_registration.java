package com.example.workerhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class worker_registration extends AppCompatActivity {
    TextInputLayout fullname_a, email_a, phone_a, state_a, city_a, colony_a, pincode_a;
    Spinner service_a;

    DatabaseReference dataref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://workerhub-b941a-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_registration);

        // Initialize TextInputLayout fields
        fullname_a = findViewById(R.id.fullname_field);
        email_a = findViewById(R.id.email_field);
        phone_a = findViewById(R.id.phone_field);
        state_a = findViewById(R.id.state_field);
        city_a = findViewById(R.id.city_field);
        colony_a = findViewById(R.id.colony_field);
        pincode_a = findViewById(R.id.pincode_field);

        // Initialize Spinner
        service_a = findViewById(R.id.service_field);

        final Button w_registerbtn = findViewById(R.id.w_registerbtn);


        w_registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input values
                final String fullName = fullname_a.getEditText().getText().toString();
                final String email = email_a.getEditText().getText().toString();
                final String phone = phone_a.getEditText().getText().toString();
                final String state = state_a.getEditText().getText().toString();
                final String city = city_a.getEditText().getText().toString();
                final String colony = colony_a.getEditText().getText().toString();
                final String pincode = pincode_a.getEditText().getText().toString();
                final String selectedService = service_a.getSelectedItem().toString();

                // Check all fields are filled or not
                if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || state.isEmpty() || city.isEmpty() || colony.isEmpty() || pincode.isEmpty() || selectedService.isEmpty()) {
                    Toast.makeText(worker_registration.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    dataref.child("worker").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // Check if phone no is registered before
                            if (snapshot.hasChild(phone)) {
                                Toast.makeText(worker_registration.this, "Phone number is already registered", Toast.LENGTH_SHORT).show();
                            } else {
                                // Save worker data to Firebase Realtime Database
                                DatabaseReference workerRef = dataref.child("worker").child(phone);
                                workerRef.child("fullname").setValue(fullName);
                                workerRef.child("email").setValue(email);
                                workerRef.child("phono").setValue(phone);
                                workerRef.child("services").setValue(selectedService);
                                workerRef.child("state").setValue(state);
                                workerRef.child("city").setValue(city);
                                workerRef.child("colony").setValue(colony);
                                workerRef.child("pincode").setValue(pincode);

                                Toast.makeText(worker_registration.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle database error
                        }
                    });
                }
            }
        });
    }
}