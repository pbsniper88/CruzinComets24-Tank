package frc.robot.commands.Autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.*;

public class SolenoidAction implements Action{
    //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private Solenoid solenoid;

    public SolenoidAction(double duration, Solenoid subsystem){
        this.duration = duration;
        solenoid = subsystem;
    }


    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        solenoid.setForward();
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
        solenoid.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
