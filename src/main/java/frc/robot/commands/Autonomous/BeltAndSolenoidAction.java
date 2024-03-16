package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.*;

public class BeltAndSolenoidAction implements Action{
    //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private Belt belt;
    private Solenoid solenoid;

    public BeltAndSolenoidAction(double duration, Belt subsystem, Solenoid subsystem2){
        this.duration = duration;
        belt = subsystem;
        solenoid = subsystem2;
    }


    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        belt.setSpeed(Constants.beltMoveSpeed * -1);
        Timer.delay(0.5);
        solenoid.setForward();
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
        belt.setSpeed(0);
        solenoid.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
