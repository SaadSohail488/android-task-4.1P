package com.example.task41;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the layout views
        LinearLayout firstLayout = findViewById(R.id.firstLayout);
        LinearLayout secondLayout = findViewById(R.id.secondLayout);
        LinearLayout thirdLayout = findViewById(R.id.thirdLayout);

        // Set click listeners for each layout
        firstLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        secondLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        thirdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }

    // Opening the WorkOut Activity
    private void openActivity1() {
        Intent intent = new Intent(MainActivity.this, WorkOut.class);
        startActivity(intent);
    }

    // Opening the WorkOut Activity
    private void openActivity2() {
        Intent intent = new Intent(MainActivity.this, WorkOut.class);
        startActivity(intent);
    }

    // Opening the WorkOut Activity
    private void openActivity3() {
        Intent intent = new Intent(MainActivity.this, WorkOut.class);
        startActivity(intent);
    }
}