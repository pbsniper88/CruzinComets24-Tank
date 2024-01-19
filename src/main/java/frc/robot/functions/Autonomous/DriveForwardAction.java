package frc.robot.functions.Autonomous;
import frc.robot.subsystems.DriveTrain;
import frc.robot.functions.Constants;

public class DriveForwardAction implements Action {
    private double duration;
    private long startTime;
    private DriveTrain tankDrive;

    public DriveForwardAction(double duration, DriveTrain tankDrive) {
        this.duration = duration;
        this.tankDrive = tankDrive;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        // Code to start driving forward
        tankDrive.drive(Constants.AutoDriveRate, Constants.AutoDriveRate);
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
