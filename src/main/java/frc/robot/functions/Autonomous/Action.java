package frc.robot.functions.Autonomous;

public interface Action {
    void start();
    void update();
    void end();
    boolean isFinished();
}
