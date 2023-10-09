package com.example.perfectingposture;

import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView goodStandingCount;
    private TextView goodSittingCount;
    private TextView badPostureCount;
    private TextView greetingsTextView;
    private TextView dateTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize UI elements
        goodStandingCount = findViewById(R.id.goodStandingCount);
        goodSittingCount = findViewById(R.id.goodSittingCount);
        badPostureCount = findViewById(R.id.badPostureCount);
        greetingsTextView = findViewById(R.id.greetingsTextView);
        dateTimeTextView = findViewById(R.id.dateTimeTextView);

        // Example: Update dashboard counts
        updateDashboardCounts(10, 8, 2);

        // Example: Set greetings and date/time
        greetingsTextView.setText("Hello, User!");
        dateTimeTextView.setText("Updated: 07-15-2023 10:30 AM");

        // Example: Handle menu item clicks
        findViewById(R.id.homeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle home button click
            }
        });
    }

    private void updateDashboardCounts(int standingCount, int sittingCount, int badCount) {
        goodStandingCount.setText("Good Standing: " + standingCount);
        goodSittingCount.setText("Good Sitting: " + sittingCount);
        badPostureCount.setText("Bad Posture: " + badCount);
    }
}
