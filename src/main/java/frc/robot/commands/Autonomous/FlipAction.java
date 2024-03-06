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
    private int state;

    public FlipAction(double duration, Flipper subsystem, int state){
        this.duration = duration;
        flipper = subsystem;
        this.state = state;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        if (state == 1){
            flipper.setSpeed(Constants.flipperFlipSpeed);    
        }

        else if (state == 2) {
            flipper.setSpeed(-Constants.flipperDownSpeed);
        }

        else if (state == 3){
            flipper.setSpeed(Constants.flipperStaySpeed);
        }
}

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public void end() {
        if (state == 1 || state == 2){
            flipper.setSpeed(0);    
        }
}

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
    
}
