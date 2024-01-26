package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TelemetryPublisher {
    public int count;
    public int gyroCount;
    // Constructor
    public TelemetryPublisher() {
        // Initialize your telemetry system here if needed
    }

    // Method to publish telemetry data
    public void publishTelemetry(String name, double value) {
        count++;
        // Publish the telemetry data to the Smart Dashboard
        //Limit how often it updates the Smart Dashboard
        if (count > 100){
        SmartDashboard.putNumber(name, value);
        count = 0;
        }
    }

        public void publishGyroTelemetry(String name, double value) {
        gyroCount++;
        // Publish the telemetry data to the Smart Dashboard
        //Limit how often it updates the Smart Dashboard
        if (gyroCount > 100){
        SmartDashboard.putNumber(name, value);
        gyroCount = 0;
        }
    }

    // You can overload this method for different data types or sensors if needed    
}