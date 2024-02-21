// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Belt extends SubsystemBase {
    private PWMTalonSRX beltMotor;
  public Belt(int beltPort) {
    beltMotor = new PWMTalonSRX(beltPort);
  }

  public void setSpeed(double speed){
    beltMotor.set(speed);
  }

  public void stopMotor(){
    beltMotor.set(0);
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
