package frc.robot.commands.Autonomous;
import frc.robot.subsystems.TankDrive;
import frc.robot.Constants;

public class DriveForwardAction implements Action {
    private double duration;
    private long startTime;
    private TankDrive tankDrive;

    public DriveForwardAction(double duration, TankDrive tankDrive) {
        this.duration = duration;
        this.tankDrive = tankDrive;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        // Code to start driving forward
        tankDrive.autonDrive(Constants.AutoDriveRate, Constants.AutoDriveRate);
    }

    @Override
    public void update() {
        // Code to update driving logic (if necessary)
    }

    @Override
    public void end() {
        // Code to stop the robot
        tankDrive.autonDrive(Constants.AutoStallRate, Constants.AutoStallRate);
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
