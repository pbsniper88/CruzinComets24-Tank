package frc.robot.commands.Autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.Servo;

public class ServoAction implements Action{
    //Determines how long to drive forward
    private double duration;
    //Keeps track of when this action started
    private long startTime;
    //Variable that accesses our TankDrive subsystem
    private Servo servo;

    public ServoAction(double duration, Servo subsystem){
        this.duration = duration;
        servo = subsystem;
    }


    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        servo.setSpeed(Constants.servoMoveSpeed);
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
        servo.setSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
