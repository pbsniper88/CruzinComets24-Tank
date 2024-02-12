package frc.robot.commands.Autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class ShootAction implements Action{
        //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private Shooter shooter;
    private int shotType;
    
    public ShootAction(double duration, Shooter subsystem, int shotType){
        this.duration = duration;
        shooter = subsystem;
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
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
    
}
