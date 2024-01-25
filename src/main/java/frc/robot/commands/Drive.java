package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class Drive extends Command {
    public Drive(){
        // addRequirements(Robot.m_swerveDrive);

    }
    @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Robot.m_swerveDrive.drive(Constants.driverController.getLeftX(), Constants.driverController.getLeftY(),Constants.driverController.getLeftX());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
        // Robot.m_swerveDrive.stopAll();
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}
