package com.evodesign.navtest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.ainirobot.coreservice.client.RobotApi;
import com.ainirobot.coreservice.client.listener.CommandListener;
import com.evodesign.navtest.R;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private static int reqId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button back = findViewById(R.id.back);
        ImageButton decorative = findViewById(R.id.decorative);
        ImageButton indoor = findViewById(R.id.indoor);
        ImageButton cr = findViewById(R.id.cr);
        ImageButton charge = findViewById(R.id.charge);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to go back to MainActivity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        decorative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RobotApi.getInstance().startNavigation(0, "decorative", 1, 10 * 1000, mMotionListener);
                showToast("Going to decorative lights");
            }
        });

        indoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RobotApi.getInstance().startNavigation(0, "indoor", 1, 10 * 1000, mMotionListener);
                showToast("Going to indoor lights");
            }
        });

        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RobotApi.getInstance().startNavigation(0, "cr", 1, 10 * 1000, mMotionListener);
                showToast("Going to comfort room");
            }
        });

        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RobotApi.getInstance().startNaviToAutoChargeAction(0, 10 * 1000, mMotionListener);
                showToast("Going to charging pole");
            }
        });

    }



    private CommandListener mMotionListener = new CommandListener() {
        @Override
        public void onResult(int result, String message) {
            if ("succeed".equals(message)) {
            } else {
            }
        }
    };

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
