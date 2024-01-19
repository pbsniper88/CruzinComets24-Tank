// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.functions.TelemetryPublisher;
import frc.robot.functions.Autonomous.DriveForwardAction;
import frc.robot.functions.Autonomous.DriveReverseAction;
import frc.robot.functions.Autonomous.TurnAction;
import frc.robot.functions.Autonomous.AutonomousScheduler;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.functions.Constants;
/*
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private XboxController driveController;
  private XboxController auxController;
  private TelemetryPublisher telemetryPublisher;
  private AutonomousScheduler autoScheduler;
  private static DriveTrain tankDrive;
  private static Shooter shooter;
  private static int rateCounter;

  @Override
  public void robotInit() {
    rateCounter = 0;
    tankDrive = new DriveTrain(Constants.leftMotor,Constants.rightMotor);
    shooter = new Shooter(Constants.shooterMotor);
    telemetryPublisher = new TelemetryPublisher();
    driveController = new XboxController(Constants.driverController);
    autoScheduler = new AutonomousScheduler();
    auxController = new XboxController(Constants.auxController);
  }

  @Override
  public void autonomousInit() {
    // Autonomous Init
    // Example movements to get started. Update based on field position, task, etc.

    // Drive forward for 3 seconds
    autoScheduler.addAction(new DriveForwardAction(3, tankDrive));
    // Turn left for 1.5 seconds
    autoScheduler.addAction(new TurnAction(1.5, false, tankDrive));
    // Drive forward again for 2 seconds
    autoScheduler.addAction(new DriveForwardAction(2, tankDrive));
    // Turn right for 1.5 seconds
    autoScheduler.addAction(new TurnAction(1.5, true, tankDrive));
    // Reverse for 1 second
    autoScheduler.addAction(new DriveReverseAction(1.0, tankDrive));
    autoScheduler.init();
  }

  @Override
  public void autonomousPeriodic() {
    rateCounter++;
    autoScheduler.run();

    // Robot SmartDashboard Telemetry, service every 100ms (for now).
    if (rateCounter % 5 == 0) {
      robotTelemetry();
    }

    if (rateCounter == 100) {
      rateCounter = 0; // Reset to zero to prevent overflows.
    }
  }

  @Override
  public void teleopPeriodic() {
    rateCounter++;
    driveWithJoystick();
    auxControls();

    // Robot SmartDashboard Telemetry, service every 100ms (for now).
    if (rateCounter % 5 == 0) {
      robotTelemetry();
    }

    if (rateCounter == 100) {
      rateCounter = 0; // Reset to zero to prevent overflows.
    }
  }

  private void driveWithJoystick()
  {
    tankDrive.drive(driveController.getLeftY(), driveController.getRightY());
  }

  private void auxControls()
  {
    if(auxController.getAButton())
    {
      shooter.forward();
    }
    else if(auxController.getBButton())
    {
      shooter.reverse();
    }
    else
    {
      shooter.stop();
    }
  }

  private void robotTelemetry() {
    // Controller variables
    telemetryPublisher.publishTelemetry("Driver Left Y", driveController.getLeftY());
    telemetryPublisher.publishTelemetry("Driver Right Y", driveController.getRightY());
  
  }
}
