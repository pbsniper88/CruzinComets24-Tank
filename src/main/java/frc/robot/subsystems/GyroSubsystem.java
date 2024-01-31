// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.TelemetryPublisher;

public class GyroSubsystem extends SubsystemBase {
    int count = 0;
    ADXRS450_Gyro gyro;
    TelemetryPublisher telemetryPublisher;
  /** Creates a new ExampleSubsystem. */
  public GyroSubsystem() {
    gyro = new ADXRS450_Gyro();
    telemetryPublisher = new TelemetryPublisher();
    
  }


  /**
   * Example command factory method.
   *
   * @return a command
   */


  @Override
  public void periodic() {
    // telemetryPublisher.publishGyroTelemetry("Angle", gyro.getAngle());
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }


}
