/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SpiralSpinner;

public class SetSpiralSpinnerVelocity extends CommandBase {
  private SpiralSpinner spinner;
  private double velocity;
  /**
   * Creates a new SetLauncherVelocity.
   */
  public SetSpiralSpinnerVelocity(SpiralSpinner subsystem, double velocitySource) {
    spinner = subsystem; 
    velocity = velocitySource;
    addRequirements(spinner);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (velocity > 0){
    spinner.setVelocity(velocity);
    }
    else {
    spinner.setReverseVelocity(Math.abs(velocity));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spinner.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}