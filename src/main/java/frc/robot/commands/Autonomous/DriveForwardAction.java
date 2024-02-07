package frc.robot.commands.Autonomous;
import frc.robot.subsystems.TankDrive;
import frc.robot.Constants;

public class DriveForwardAction implements Action {
    //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private TankDrive tankDrive;
    
    //Constructer is called within Robot class, which contains the an instance of TankDrive
    public DriveForwardAction(double duration, TankDrive tankDrive) {
        this.duration = duration;
        this.tankDrive = tankDrive;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        // Code to start driving forward
        //Speed for autonDrive is within Constants class
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
        //This determines how long the Action runs based on how long ago it started
        //end() will be called once this returns true        
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
