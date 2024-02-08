// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Sucker extends SubsystemBase {
    private PWMTalonSRX suckerMotor;
  public Sucker(int suckerPort) {
    suckerMotor = new PWMTalonSRX(suckerPort);
  }

  public void setSpeed(double speed){
    suckerMotor.set(speed);
  }

  public void stopMotor(){
    suckerMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
