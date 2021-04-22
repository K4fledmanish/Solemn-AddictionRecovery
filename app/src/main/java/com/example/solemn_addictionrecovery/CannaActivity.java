package com.example.solemn_addictionrecovery;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class CannaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    GoogleSignInClient mGoogleSignInClient;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    private TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canna);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        title = findViewById(R.id.cannaTitle);
        description = findViewById(R.id.cannaDescription);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        DocumentReference df = fStore.collection("Info").document("Canna");
        df.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                title.setText(documentSnapshot.getString("title"));
                description.setText(documentSnapshot.getString("description"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(CannaActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_calorie:
                Intent intent2 = new Intent(CannaActivity.this, CalorieCounterActivity.class);
                startActivity(intent2);
                break;

            case R.id.nav_account:
                Intent intent3 = new Intent(CannaActivity.this, ProfileActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_substance:
                Intent intent5 = new Intent(CannaActivity.this, SubstanceActivity.class);
                startActivity(intent5);
                break;

            case R.id.nav_alcohol:
                Intent intent4 = new Intent(CannaActivity.this, AlcoholActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_logout:
                signOut();
                break;

        }

        return true;
    }

    private void signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(CannaActivity.this, "Signed out successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CannaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}