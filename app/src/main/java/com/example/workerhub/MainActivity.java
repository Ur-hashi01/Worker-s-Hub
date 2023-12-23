// MainActivity.java
package com.example.workerhub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<CategoryModel> CategoryList = new ArrayList<>();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // menu view clickable
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        findViewById(R.id.menu_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Set navigation item click listener
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_logout) {
                    showLogoutConfirmationDialog();
                    return true;
                } else if (id == R.id.per_info) {
                    startActivity(new Intent(MainActivity.this, personal_info.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (id == R.id.w_signup) {
                    startActivity(new Intent(MainActivity.this, worker_registration.class));
                    return true;
                }
                return false;
            }
        });

        gridView = findViewById(R.id.gridview);
        CategoryList.add(new CategoryModel("Carpenter", R.drawable.carpenter));
        CategoryList.add(new CategoryModel("Cleaner", R.drawable.cleaner));
        CategoryList.add(new CategoryModel("Cook", R.drawable.cook));
        CategoryList.add(new CategoryModel("Electrician", R.drawable.electrician));
        CategoryList.add(new CategoryModel("Mason", R.drawable.mason));
        CategoryList.add(new CategoryModel("Nurse", R.drawable.nurse));
        CategoryList.add(new CategoryModel("Painter", R.drawable.painter));
        CategoryList.add(new CategoryModel("Plumber", R.drawable.plumber));
        CategoryAdapter cat = new CategoryAdapter(this, CategoryList);
        gridView.setAdapter(cat);

        // Add click listener to the carpenter image item
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { // Assuming carpenter is the first item in the CategoryList
                    // Start CarpenterListActivity
                    startActivity(new Intent(MainActivity.this, carpenter_list.class));
                }
            }
        });

        sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            startLoginActivity();
        }
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void logout() {
        setLoggedInStatus(false);
        startLoginActivity();
    }

    private void setLoggedInStatus(boolean isLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }

    private void startLoginActivity() {
        startActivity(new Intent(MainActivity.this, login.class));
        finish();
    }
}
