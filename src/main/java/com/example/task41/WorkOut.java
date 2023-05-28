package com.example.task41;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WorkOut extends AppCompatActivity {
    private boolean isworkingRunning; // Flag to track if the workout timer is running
    private boolean isrestRunning; // Flag to track if the rest timer is running
    private long workoutTime; // Total duration of the workout in milliseconds
    private long restTime; // Total duration of the rest period in milliseconds
    private TextView workout_time; // TextView to display the workout time
    private EditText workout_time_show; // EditText to input the workout time
    private TextView rest_time; // TextView to display the rest time
    private EditText rest_time_show; // EditText to input the rest time
    private ProgressBar progressBar; // ProgressBar to visualize the progress of the timers
    private CountDownTimer work_timer; // Countdown timer for the workout
    private CountDownTimer rest_timer; // Countdown timer for the rest period
    private Button start; // Button to start the workout
    private Button stop; // Button to stop the workout


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_out);

        // Initialize views
        progressBar = findViewById(R.id.progressBar);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        workout_time = findViewById(R.id.workout_time);
        workout_time_show = findViewById(R.id.workout_time_show);
        rest_time = findViewById(R.id.rest_time);
        rest_time_show = findViewById(R.id.rest_time_show);

        // Set click listeners for buttons
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWorkout();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopWorkout();
            }
        });
    }

    // Update the workout time display
    private void updateWorkoutTime(long millisUntilFinished) {
        long minutes = millisUntilFinished / (60 * 1000);
        long seconds = (millisUntilFinished / 1000) % 60;
        workout_time.setText("Workout Time: " + String.format("%02d:%02d", minutes, seconds));
        int progress = (int) ((1 - (double) millisUntilFinished / workoutTime) * 100);
        progressBar.setProgress(progress);
    }

    // Play sound indicating the end of a workout
    private void playSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.workout);
        mediaPlayer.start();
    }

    // Update the rest time display
    private void updateRestTime(long millisUntilFinished) {
        long minutes = millisUntilFinished / (60 * 1000);
        long seconds = (millisUntilFinished / 1000) % 60;
        rest_time.setText("Rest Time: " + String.format("%02d:%02d", minutes, seconds));
        int progress = (int) ((1 - (double) millisUntilFinished / restTime) * 100);
        progressBar.setProgress(progress);
    }

    // Stop the workout and reset the timers
    private void stopWorkout() {
        work_timer.cancel();
        rest_timer.cancel();
        isworkingRunning = false;
        isrestRunning = false;
        start.setEnabled(true);
        stop.setEnabled(false);
    }

    // Start the rest timer
    private void startRest() {
        rest_timer.start();
        isrestRunning = true;
    }

    // Start the workout
// Start the workout
    private void startWorkout() {
        if (!isworkingRunning && !isrestRunning) {
            long workoutMinutes = Long.parseLong(workout_time_show.getText().toString());
            long restMinutes = Long.parseLong(rest_time_show.getText().toString());

            workoutTime = workoutMinutes * 60 * 1000; // Convert workout duration to milliseconds
            restTime = restMinutes * 60 * 1000; // Convert rest duration to milliseconds

            // Configure and start the workout time
            work_timer = new CountDownTimer(workoutTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateWorkoutTime(millisUntilFinished); // Update workout time display
                }

                @Override
                public void onFinish() {
                    updateWorkoutTime(0); // Set workout time display to 0
                    startRest(); // Start the rest timer
                    playSound(); // Play a sound indicating the end of the workout
                }
            };

            // Configuring the rest timer
            rest_timer = new CountDownTimer(restTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateRestTime(millisUntilFinished); // Update rest time display
                }

                @Override
                public void onFinish() {
                    updateRestTime(0); // Set rest time display to 0
                    startWorkout(); // Start the next workout cycle
                }
            };

            work_timer.start(); // Start the workout time
            isworkingRunning = true; // Set the flag indicating workout timer is running
            start.setEnabled(false); // Disable the start button
            stop.setEnabled(true); // Enable the stop button
        }
    }
}
