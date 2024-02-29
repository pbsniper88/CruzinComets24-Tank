package frc.robot.commands.Autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoid;
import edu.wpi.first.wpilibj.Timer;


public class ShootWithSoleAction implements Action{
        //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private Shooter shooter;
    private Solenoid solenoid;
    private int shotType;
    
    public ShootWithSoleAction(Shooter subsystem, Solenoid subsystem2, int shotType){
        shooter = subsystem;
        solenoid = subsystem2;
        this.shotType = shotType;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        if (shotType == Constants.speakerShot){
            //This sets the shooter motors to the specific speed we want
            shooter.setSpeed(Constants.speakerShotSpeed);
          }
      
          else if (shotType == Constants.ampShot){
            shooter.setSpeed(Constants.ampShotSpeed);
          }

        Timer.delay(Constants.ShooterSpinTimeTillServoPush);
        solenoid.setForward();
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
        solenoid.stopMotor();
        shooter.setSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= (Constants.ShooterSpinTimeTillServoPush + 2) * 1000;
    }
    
}
