package frc.robot.functions.Autonomous;
import frc.robot.subsystems.DriveTrain;
import frc.robot.functions.Constants;

public class TurnAction implements Action {
    private double duration;
    private long startTime;
    private boolean turnRight;
    private DriveTrain tankDrive;

    public TurnAction(double duration, boolean turnRight, DriveTrain tankDrive) {
        this.duration = duration;
        this.turnRight = turnRight;
        this.tankDrive = tankDrive;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        // Code to start turning
        if(turnRight) {
            tankDrive.drive(Constants.AutoTurnRate, -Constants.AutoTurnRate);
        }
        else {
            tankDrive.drive(-Constants.AutoTurnRate, Constants.AutoTurnRate);
        }
    }

    @Override
    public void update() {
        // Optional: Update turning logic, if necessary
    }

    @Override
    public void end() {
        // Code to stop the robot
        tankDrive.drive(Constants.AutoStallRate, Constants.AutoStallRate);
    }

    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= duration * 1000;
    }
}
