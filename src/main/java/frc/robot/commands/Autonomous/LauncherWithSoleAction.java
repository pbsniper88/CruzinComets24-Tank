package frc.robot.commands.Autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Solenoid;
import edu.wpi.first.wpilibj.Timer;


public class LauncherWithSoleAction implements Action{
        //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    //Variable that accesses our TankDrive subsystem
    private Launcher launcher;
    private Solenoid solenoid;
    private int shotType;
    private boolean alreadyShot;
    private long startTime;
    private int velocity;
    
    public LauncherWithSoleAction(Launcher subsystem, Solenoid subsystem2, int shotType){
        launcher = subsystem;
        solenoid = subsystem2;
        alreadyShot = false;
        this.shotType = shotType;
        if (shotType == Constants.speakerShot){
            //This sets the shooter motors to the specific speed we want
            // launcher.setVelocity(Constants.LauncherConstants.targetSpeakerRPM);
            velocity = Constants.LauncherConstants.targetSpeakerRPM;
        }
      
        else if (shotType == Constants.ampShot){
            // launcher.setVelocity(Constants.LauncherConstants.targetAmpRPM);
            velocity = Constants.LauncherConstants.targetAmpRPM;
        }
    }

    @Override
    public void start() {
          startTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        launcher.setVelocity(velocity);
        if(launcher.isOnTarget() && alreadyShot == false){
            alreadyShot = true;
            solenoid.setForward();
        }
    }

    @Override
    public void end() {
        Timer.delay(0.6);
        solenoid.stopMotor();
        launcher.setVelocity(0);
    }

    //Ends action when true
    @Override
    public boolean isFinished() {
        // return (System.currentTimeMillis() - startTime) >= (Constants.ShooterSpinTimeTillServoPush + 2) * 1000;
        return alreadyShot;
    }
    
}
