package frc.robot.commands.Autonomous;
import frc.robot.subsystems.TankDrive;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;

public class SmoothDriveForwardAction implements Action {
    //Determines how long to drive forward
    private double distance;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private TankDrive tankDrive;
    Encoder encoder = new Encoder(0, 1);
    // Configures the encoder's distance-per-pulse
    // The robot moves forward 1 foot per encoder rotation
    // There are 256 pulses per encoder rotation
    encoder.setDistancePerPulse(Constants.WheelDiameter*Constants.Pi/256.0);
    encoder.setSamplesToAverage(5);

    
    //Constructer is called within Robot class, which contains the an instance of TankDrive
    // Distance is in feet
    public DriveForwardAction(double distance, TankDrive tankDrive) {
        this.distance = distance;
        this.tankDrive = tankDrive;
        
    }

    @Override
    public void start() {
        if(encoder.getDistance() < distance/5) {
            tankDrive.autonDrive(Constants.AutoDriveRate/5, Constants.AutoDriveRate/5);
        }
        if(encoder.getDistance() < distance/3) {
            tankDrive.autonDrive(Constants.AutoDriveRate/3, Constants.AutoDriveRate/3);
        } 
        else {
            tankDrive.autonDrive(Constants.AutoStallRate, Constants.AutoStallRate);
        }
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
        return encoder.getDistance()>=distance;
    }
}
