package frc.robot.commands.Autonomous;

public interface Action {
    void start();
    void update();
    void end();
    boolean isFinished();
}
