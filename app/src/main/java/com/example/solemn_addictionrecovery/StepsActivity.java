package com.example.solemn_addictionrecovery;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class StepsActivity extends AppCompatActivity {

    RecyclerView mFirestoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        mFirestoreList = findViewById(R.id.firestore_list);
    }
}