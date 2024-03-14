package frc.robot.commands.Autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.*;

public class WaitAction implements Action{
    //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem

    public WaitAction(double duration){
        this.duration = duration;
    }


    @Override
    public void start() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
