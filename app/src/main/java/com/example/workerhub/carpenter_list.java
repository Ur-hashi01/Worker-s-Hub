package com.example.workerhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class carpenter_list extends AppCompatActivity {
    RecyclerView recyclerView;
    rcview_adapter rcview_adapter;
    DatabaseReference mBase, ref;
    SearchView searchView;
    String string, refinedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpenter_list);

        mBase = FirebaseDatabase.getInstance().getReference();
        ref = FirebaseDatabase.getInstance().getReference("worker");

        recyclerView = findViewById(R.id.rcview);
        searchView = findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<rcview_model> options =
                new FirebaseRecyclerOptions.Builder<rcview_model>()
                        .setQuery(mBase.child("worker").orderByChild("services").equalTo("Carpenter"), rcview_model.class)
                        .build();

        rcview_adapter = new rcview_adapter(options);
        recyclerView.setAdapter(rcview_adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                string = dataSnapshot.getValue().toString();
                refinedData = string.substring(1, string.length() - 1);
                String List[] = refinedData.split(“,”);
                listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, List));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                int SearchIndex = refinedData.indexOf(newText);
                String SearchResult = refinedData.substring(SearchIndex);
                String SearchSplit[] = SearchResult.split(“,”);
                textViewSearch.setText(SearchSplit[0]);
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        rcview_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        rcview_adapter.stopListening();
    }

}
