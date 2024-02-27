// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Shooter extends SubsystemBase {
private PWMTalonSRX shooterMotor;
  // Contructs a shooter to control.
  public Shooter(int shooterPort) {
    shooterMotor = new PWMTalonSRX(shooterPort);
  }

  /* 
  The shooter spins the wrong way when positive. 
  Constants.reverse is -1 to reverse the direction of spin
  */
  public void setSpeed(double speed){
    //Multiple by -1 to reverse direction
    shooterMotor.set(speed * Constants.reverse);
  }
  
  // Stops both motors for the shooter
  public void stopMotor(){
    shooterMotor.set(0);
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
