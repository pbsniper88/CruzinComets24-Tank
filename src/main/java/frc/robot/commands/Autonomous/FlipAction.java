package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.Flipper;

public class FlipAction implements Action{
    //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private Flipper flipper;

    public FlipAction(double duration, Flipper subsystem){
        this.duration = duration;
        flipper = subsystem;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        flipper.setSpeed(Constants.flipperFlipSpeed);    
}

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public void end() {
        flipper.setSpeed(Constants.flipperFlipSpeed * -1);
        Timer.delay(Constants.flipperFlipDuration);
        //Stop the shooter motors
        flipper.setSpeed(0);    
}

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
    
}
