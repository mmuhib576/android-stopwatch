package com.example.muhibassignment1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import com.example.muhibassignment1.databinding.ActivityMainBinding;


public class muhibAssignment1 extends AppCompatActivity
{
    private ActivityMainBinding binding;
    private StopwatchLogic stopwatchLogic; //For stopwatch logic
    private Handler textViewHandler; // Handler for updating the timer display
    private boolean isTimerRunning; // Flag to track whether the timer is running

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the stopwatch
        stopwatchLogic = new StopwatchLogic();
        // Initialize the timer handler
        textViewHandler = new Handler();
        // Initialize the timer state as not running
        isTimerRunning = false;

        // Common Click Listener for all buttons
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (v == binding.startButton)
                {
                    handleStartClick();
                }
                else if (v == binding.stopButton)
                {
                    handleStopClick();
                }
                else if (v == binding.resetButton)
                {
                    handleResetClick();
                }
            }
        };

        // Set the common click listener for all buttons
        binding.startButton.setOnClickListener(buttonClickListener);
        binding.stopButton.setOnClickListener(buttonClickListener);
        binding.resetButton.setOnClickListener(buttonClickListener);

        // Initial button state setup
        updateButton();
    }

    // Handle Start Button Click
    private void handleStartClick()
    {
        if (!stopwatchLogic.isRunning())
        {
            // Start the stopwatch
            stopwatchLogic.start();
            // Set the timer state to running
            isTimerRunning = true;
            // Update button status (disable Start, enable Stop and Reset)
            updateButton();
            // Start updating the timer display
            startTimer();
        }
    }

    // Handle Stop Button Click
    private void handleStopClick()
    {
        if (stopwatchLogic.isRunning())
        {
            // Stop the stopwatch
            stopwatchLogic.stop();
            // Set the timer state to not running
            isTimerRunning = false;
            // Update button states (enable Start, disable Stop)
            updateButton();
        }
    }

    // Handle Reset Button Click
    private void handleResetClick()
    {
        // Reset the stopwatch
        stopwatchLogic.reset();
        // Set the timer state to not running
        isTimerRunning = false;
        // Update button states (enable Start, disable Stop and Reset)
        updateButton();
        // Clear the timer display
        binding.timerTextView.setText("00:00:00");
    }

    // Function to update the button states based on the timer state
    private void updateButton()
    {
        binding.startButton.setEnabled(!isTimerRunning); // Enable Start if the timer is not running
        binding.stopButton.setEnabled(isTimerRunning); // Enable Stop if the timer is running
        binding.resetButton.setEnabled(!isTimerRunning); // Enable Reset if the timer is not running
    }

    // Function to update the timer display
    private void startTimer()
    {
        textViewHandler.post(new Runnable()
        {

            @Override
            public void run()
            {
                // Check if the timer is running
                if (isTimerRunning)
                {
                    long elapsedTime = stopwatchLogic.ElapsedTime();
                    int seconds = (int) (elapsedTime / 1000);
                    int minutes = seconds / 60;
                    seconds = seconds % 60;
                    int hours = minutes / 60;

                    String timeText = String.format("%02d:%02d:%02d",hours, minutes, seconds);
                    // Update the timer display
                    binding.timerTextView.setText(timeText);

                    // Update every second
                    textViewHandler.postDelayed(this, 1000);
                }
            }
        });
    }
}