package com.evodesign.navtest;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;  // Add this import for AdapterView
import androidx.appcompat.app.AppCompatActivity;
import com.ainirobot.coreservice.client.RobotApi;
import com.ainirobot.coreservice.client.actionbean.Pose;
import com.ainirobot.coreservice.client.listener.CommandListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView placeListView;
    private ArrayAdapter<String> adapter;
    private List<String> placeNames;

    private CommandListener mMotionListener = new CommandListener() {
        @Override
        public void onResult(int result, String message) {
            // Handle the result of the navigation command here if needed
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeListView = findViewById(R.id.place_list_view);
        placeNames = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, placeNames);
        placeListView.setAdapter(adapter);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            // Navigate to another activity
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        loadPlaceList();

        // Set an item click listener for the ListView
        placeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedPlace = placeNames.get(position);
                navigateToPlace(selectedPlace);  // Trigger navigation
            }
        });
    }

    private void loadPlaceList() {
        // Fetch the place list from the robot API
        List<Pose> route = RobotApi.getInstance().getPlaceList();
        if (route != null && !route.isEmpty()) {
            for (Pose pose : route) {
                placeNames.add(pose.getName()); // Assuming Pose has a getName() method
            }
            adapter.notifyDataSetChanged(); // Update the ListView
        } else {
            Toast.makeText(this, "No places available", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToPlace(String placeName) {
        // Trigger navigation to the selected place
        RobotApi.getInstance().startNavigation(0, placeName, 1, 10 * 1000, mMotionListener);
        showToast("Going to " + placeName);  // Show the toast message
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}


