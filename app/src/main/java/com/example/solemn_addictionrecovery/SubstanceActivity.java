package com.example.solemn_addictionrecovery;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class SubstanceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    GoogleSignInClient mGoogleSignInClient;
    CardView cardviewAlcohol, cardViewOpioids, cardViewBenzo, cardViewCanna, cardViewBarbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substance);


        cardviewAlcohol = findViewById(R.id.alcoholCardView);
        cardViewOpioids = findViewById(R.id.opioidsCardView);
        cardViewBenzo = findViewById(R.id.benzoCardView);
        cardViewCanna = findViewById(R.id.cannaCardView);
        cardViewBarbi = findViewById(R.id.barbiCardView);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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



        cardviewAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubstanceActivity.this, AlcoholActivity.class);
                startActivity(intent);
            }
        });

        cardViewOpioids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubstanceActivity.this, OpioidActivity.class);
                startActivity(intent);
            }
        });

        cardViewBenzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubstanceActivity.this, BenzoActivity.class);
                startActivity(intent);
            }
        });

        cardViewCanna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubstanceActivity.this, CannaActivity.class);
                startActivity(intent);
            }
        });

        cardViewBarbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubstanceActivity.this, BarbiActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(SubstanceActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_calorie:
                Intent intent2 = new Intent(SubstanceActivity.this, TFMainActivity.class);
                startActivity(intent2);
                break;

            case R.id.nav_account:
                Intent intent3 = new Intent(SubstanceActivity.this, ProfileActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_substance:
                break;

            case R.id.nav_alcohol:
                Intent intent4 = new Intent(SubstanceActivity.this, InfoActivity.class);
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
                Toast.makeText(SubstanceActivity.this, "Signed out successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SubstanceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}