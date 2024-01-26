package frc.robot.commands.Autonomous;

import frc.robot.subsystems.TankDrive;
import frc.robot.Constants;

public class DriveReverseAction implements Action {
    private double duration;
    private long startTime;
    private TankDrive tankDrive;

    public DriveReverseAction(double duration, TankDrive tankDrive) {
        this.duration = duration;
        this.tankDrive = tankDrive;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        // Code to start driving forward
        tankDrive.drive(Constants.AutoReverseRate, Constants.AutoReverseRate);
    }

    @Override
    public void update() {
        // Code to update driving logic (if necessary)
    }

    @Override
    public void end() {
        // Code to stop the robot
        tankDrive.drive(Constants.AutoStallRate, Constants.AutoStallRate);
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
