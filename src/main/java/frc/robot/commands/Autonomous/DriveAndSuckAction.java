package frc.robot.commands.Autonomous;
import frc.robot.subsystems.*;
import frc.robot.Constants;

public class DriveAndSuckAction implements Action {
    //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private TankDrive tankDrive;

    private Sucker sucker;
    
    //Constructer is called within Robot class, which contains the an instance of TankDrive
    public DriveAndSuckAction(double duration, TankDrive tankDrive, Sucker sucker) {
        this.duration = duration;
        this.tankDrive = tankDrive;
        this.sucker = sucker;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        // Code to start driving forward
        //Speed for autonDrive is within Constants class
        tankDrive.autonDrive(Constants.AutoDriveRate, Constants.AutoDriveRate);
        sucker.setSpeed(Constants.suckerSuckSpeed);
    }

    @Override
    public void update() {
        // Code to update driving logic (if necessary)
    }

    @Override
    public void end() {
        // Code to stop the robot
        tankDrive.autonDrive(Constants.AutoStallRate, Constants.AutoStallRate);
        sucker.setSpeed(0);
    }

    @Override
    public boolean isFinished() {
        //This determines how long the Action runs based on how long ago it started
        //end() will be called once this returns true        
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
