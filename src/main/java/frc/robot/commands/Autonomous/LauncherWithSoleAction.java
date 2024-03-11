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
    private boolean stop;
    
    public LauncherWithSoleAction(Launcher subsystem, Solenoid subsystem2, int shotType){
        stop = false;
        launcher = subsystem;
        solenoid = subsystem2;
        this.shotType = shotType;
    }

    @Override
    public void start() {
        if (shotType == Constants.speakerShot){
            //This sets the shooter motors to the specific speed we want
            launcher.setVelocity(Constants.LauncherConstants.targetSpeakerRPM);
          }
      
          else if (shotType == Constants.ampShot){
            launcher.setVelocity(Constants.LauncherConstants.targetAmpRPM);
          }

    }

    @Override
    public void update() {
        Timer.delay(2);
        if (launcher.isOnTarget()){
            solenoid.setForward();
            Timer.delay(2);
            stop = true;
        }
    }

    @Override
    public void end() {
        solenoid.stopMotor();
        launcher.stop();
    }

    //Ends action when true
    @Override
    public boolean isFinished() {
        return stop;
    }
    
}
