/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Launcher;

public class SetLauncherVelocity extends CommandBase {
  private Launcher launcher;
  private double velocity;
  private TelemetryPublisher telemetryPublisher = new TelemetryPublisher();
  /**
   * Creates a new SetLauncherVelocity.
   */
  public SetLauncherVelocity(Launcher subsystem, double velocitySource) {
    launcher = subsystem; 
    velocity = velocitySource;
    addRequirements(launcher);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    String curShotMode = SmartDashboard.getString("Current Shot Type", "Off");
    if (velocity == Constants.LauncherConstants.targetSpeakerRPM){
      if (curShotMode.equals("Off") || curShotMode.equals("Amp")){
      //This sets the shooter motors to the specific speed we want
      velocity = Constants.LauncherConstants.targetSpeakerRPM;
      telemetryPublisher.publishShotTelemetry("Current Shot Type", "Speaker");
      }

      else {
        velocity = 0;
        telemetryPublisher.publishShotTelemetry("Current Shot Type", "Off");
      }
    }

    else if (velocity == Constants.LauncherConstants.targetAmpRPM){
      if (curShotMode.equals("Off") || curShotMode.equals("Speaker")){
        velocity = Constants.LauncherConstants.targetAmpRPM;
        telemetryPublisher.publishShotTelemetry("Current Shot Type", "Amp");
      }
      else{
        velocity = 0;
        telemetryPublisher.publishShotTelemetry("Current Shot Type", "Off");
      }


    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    launcher.setVelocity(velocity);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    launcher.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}