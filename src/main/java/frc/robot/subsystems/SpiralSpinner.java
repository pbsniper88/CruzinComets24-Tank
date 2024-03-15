/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.SpiralSpinnerConstants;

public class SpiralSpinner extends SubsystemBase {

  private CANSparkMax rightSpinnerMotor;
  private CANSparkMax leftSpinnerMotor; 
  private CANEncoder rightMotorEncoder;
  private CANEncoder leftMotorEncoder;
  private CANPIDController rightPIDController;
  private CANPIDController leftPIDController;
  private double targetVelocity = 0; 
  private int rollingAvg = 0;
  private int desiredRPM;
  private DigitalInput topLeftLimitSwitch = new DigitalInput(0);
  private DigitalInput bottomLeftLimitSwitch = new DigitalInput(1);
  private DigitalInput topRightLimitSwitch = new DigitalInput(2);
  private DigitalInput bottomRightLimitSwitch = new DigitalInput(3);

  public SpiralSpinner() {
    leftSpinnerMotor = new CANSparkMax(Constants.SpiralSpinnerConstants.leftSpinnerMotor, MotorType.kBrushless);
    rightSpinnerMotor = new CANSparkMax(Constants.SpiralSpinnerConstants.rightSpinnerMotor, MotorType.kBrushless);

    rightSpinnerMotor.setInverted(true);
    leftSpinnerMotor.setInverted(true);

    leftMotorEncoder = leftSpinnerMotor.getEncoder();
    rightMotorEncoder = rightSpinnerMotor.getEncoder();

    leftPIDController = leftSpinnerMotor.getPIDController();
    rightPIDController = rightSpinnerMotor.getPIDController();

    leftPIDController.setP(SpiralSpinnerConstants.proportialPIDConstant);
    leftPIDController.setI(SpiralSpinnerConstants.integralPIDConstant);
    leftPIDController.setD(SpiralSpinnerConstants.derivativePIDConstant);
    leftPIDController.setIZone(SpiralSpinnerConstants.integralPIDConstant);
    leftPIDController.setFF(SpiralSpinnerConstants.leftFeedForwardPIDConstant);
    leftPIDController.setOutputRange(SpiralSpinnerConstants.minPIDOutput, SpiralSpinnerConstants.maxPIDOutput);

    rightPIDController.setP(SpiralSpinnerConstants.proportialPIDConstant);
    rightPIDController.setI(SpiralSpinnerConstants.integralPIDConstant);
    rightPIDController.setD(SpiralSpinnerConstants.derivativePIDConstant);
    rightPIDController.setIZone(SpiralSpinnerConstants.integralPIDConstant);
    rightPIDController.setFF(SpiralSpinnerConstants.rightFeedForwardPIDConstant);
    rightPIDController.setOutputRange(SpiralSpinnerConstants.minPIDOutput, SpiralSpinnerConstants.maxPIDOutput);
    stop();
    SmartDashboard.putNumber("Desired Spinner RPM", 0);

    leftSpinnerMotor.burnFlash();
    rightSpinnerMotor.burnFlash();
  }  

  public void setVelocity(double velocity) {
    rightSpinnerMotor.setInverted(true);
    leftSpinnerMotor.setInverted(true);
    targetVelocity = velocity;
    if (topRightLimitSwitch.get()){
      rightPIDController.setReference(0, ControlType.kVelocity);
    }
    else {
      rightPIDController.setReference(targetVelocity, ControlType.kVelocity);
    }

    if (topLeftLimitSwitch.get()){
      leftPIDController.setReference(0, ControlType.kVelocity);
    }
    else {
      leftPIDController.setReference(targetVelocity, ControlType.kVelocity);
    }
  }

  public void setReverseVelocity(double velocity){
    rightSpinnerMotor.setInverted(false);
    leftSpinnerMotor.setInverted(false);
    targetVelocity = velocity;
    if (bottomRightLimitSwitch.get()){
      rightPIDController.setReference(0, ControlType.kVelocity);
    }
    else {
      rightPIDController.setReference(targetVelocity, ControlType.kVelocity);
    }

    if (bottomLeftLimitSwitch.get()){
      leftPIDController.setReference(0, ControlType.kVelocity);
    }
    else {
      leftPIDController.setReference(targetVelocity, ControlType.kVelocity);
    }
  }

  public void setSpeed(double speed) {
    leftSpinnerMotor.set(speed);
    rightSpinnerMotor.set(speed);
  }

  public void stop() {
    setSpeed(0.0);
  }

  // Finds the average velocity of the two motors 
  public double getVelocity() {
    double sum = leftMotorEncoder.getVelocity() + rightMotorEncoder.getVelocity();
    double average = sum / 2;
    return average;
  }

  // For the target velocity
  public boolean isOnTarget() {
    boolean leftOnTarget = Math.abs(targetVelocity - leftMotorEncoder.getVelocity()) <= SpiralSpinnerConstants.velocityPIDTolerance;
    boolean rightOnTarget = Math.abs(targetVelocity - rightMotorEncoder.getVelocity()) <= SpiralSpinnerConstants.velocityPIDTolerance;
    return (rightOnTarget && leftOnTarget);
  }

  public boolean isOnTargetAverage(int percent) {
    if(percent > 10) {
      percent = 10;
    } else if(percent < 0) {
      percent = 0;
    }

    if(rollingAvg >= percent) {
      return true;
    }
    return false;
  }

  public static double distanceToVelocity(double distance) {
    //TODO tune distance convertion 
    return 0.0;
  }

  @Override
  public void periodic() {
    if (isOnTarget()) {
      if(rollingAvg < 10) {
        rollingAvg++;
      }
    } else if(rollingAvg > 0) {
      if(rollingAvg > 0) {
        rollingAvg--;
      }
    }


    SmartDashboard.putNumber("Left Spinner Velocity", leftMotorEncoder.getVelocity());
    SmartDashboard.putNumber("Right Spinner Velocity", rightMotorEncoder.getVelocity());
    SmartDashboard.putNumber("Target Spinner Velocity", targetVelocity);
    // This method will be called once per scheduler run
  }
}