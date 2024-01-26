package frc.robot.commands.Autonomous;

import frc.robot.subsystems.TankDrive;
import frc.robot.Constants;

public class TurnAction implements Action {
    private double duration;
    private long startTime;
    private boolean turnRight;
    private TankDrive tankDrive;

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
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
