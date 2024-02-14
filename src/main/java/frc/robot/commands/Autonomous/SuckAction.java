package frc.robot.commands.Autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.Sucker;

public class SuckAction implements Action{
    //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private Sucker sucker;

    public SuckAction(double duration, Sucker subsystem){
        this.duration = duration;
        sucker = subsystem;
    }


    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        sucker.setSpeed(Constants.suckerSuckSpeed);
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
        sucker.setSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
