package frc.robot.commands.Autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Servo;

public class ShootWithServoAction implements Action{
        //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private Shooter shooter;
    private Servo servo;
    private int shotType;
    
    public ShootWithServoAction(Shooter subsystem, Servo subsystem2, int shotType){
        shooter = subsystem;
        servo = subsystem2;
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
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
        shooter.setSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= Constants.ShooterSpinTimeTillServoPush * 1000;
    }
    
}
