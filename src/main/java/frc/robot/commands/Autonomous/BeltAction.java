package frc.robot.commands.Autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.*;

public class BeltAction implements Action{
    //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private Belt belt;

    public BeltAction(double duration, Belt subsystem){
        this.duration = duration;
        belt = subsystem;
    }


    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        belt.setSpeed(Constants.beltMoveSpeed);
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
        belt.setSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
