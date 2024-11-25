package com.evodesign.navtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button navigation = findViewById(R.id.navigation);
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the second activity
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button map = findViewById(R.id.maptools);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Try using an implicit intent to open the map tool app
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ainirobot.maptool");

                if (intent != null) {
                    // Launch the map tool app
                    startActivity(intent);
                } else {
                    // Handle the case where the app is not installed
                    Toast.makeText(MainActivity2.this, "Map tool app not installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
