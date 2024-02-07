package frc.robot.commands.Autonomous;

import frc.robot.subsystems.TankDrive;
import frc.robot.Constants;

public class TurnAction implements Action {
    //Determines how long to drive forward    
    private double duration;
    //Keeps track of when this action started    
    private long startTime;
    //true if we want to turn right, false if we want to turn left
    private boolean turnRight;
    //Variable that accesses our TankDrive subsystem    
    private TankDrive tankDrive;
    
    //Constructer is called within Robot class, which contains the an instance of TankDrive
    public TurnAction(double duration, boolean turnRight, TankDrive tankDrive) {
        this.duration = duration;
        this.turnRight = turnRight;
        this.tankDrive = tankDrive;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        // Code to start turning
        if(turnRight) {
            tankDrive.autonDrive(Constants.AutoTurnRate, -Constants.AutoTurnRate);
        }
        else {
            tankDrive.autonDrive(-Constants.AutoTurnRate, Constants.AutoTurnRate);
        }
    }

    @Override
    public void update() {
        // Optional: Update turning logic, if necessary
    }

    @Override
    public void end() {
        // Code to stop the robot
        tankDrive.autonDrive(Constants.AutoStallRate, Constants.AutoStallRate);
    }


    @Override
    public boolean isFinished() {
        //This determines how long the Action runs based on how long ago it started
        //end() will be called once this returns true        
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
