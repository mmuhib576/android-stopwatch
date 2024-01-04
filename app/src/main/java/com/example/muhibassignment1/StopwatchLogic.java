package com.example.muhibassignment1;

public class StopwatchLogic
{
    private long startTime; // Private fields to store the start times
    private boolean running; // Boolean flag to indicate whether the stopwatch is running or not

    // Constructor to initialize the stopwatch
    public StopwatchLogic()
    {
        reset();
    }

    // Method to start the stopwatch
    public void start()
    {
        if (!running)
        {
            // Set the start time and mark the stopwatch as running
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    // Method to stop the stopwatch
    public void stop()
    {
        if (running)
        {
            // Set the stop time and mark the stopwatch as not running
            running = false;
        }
    }

    // Method to reset the stopwatch
    public void reset()
    {
        // Reset the start times and set the running flag to false
        running = false;
        startTime = 0;
    }

    // Method to set the stopwatch running
    public boolean isRunning()
    {
        return running;
    }

    // Method to get the elapsed time since the stopwatch was started
    public long ElapsedTime()
    {
        if (running)
        {
            //return the total elapsed time from the start times
            return System.currentTimeMillis() - startTime;
        }
        return 0;
    }
}