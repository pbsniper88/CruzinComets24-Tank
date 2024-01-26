package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants;
import frc.robot.commands.TelemetryPublisher;

public class TankDrive extends SubsystemBase {

  private Wheel rightSide;
  private Wheel leftSide;
  private final SlewRateLimiter leftSpeedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter rightSpeedLimiter = new SlewRateLimiter(3);
  private TelemetryPublisher telemetryPublisher = new TelemetryPublisher();

  public TankDrive(Wheel Right, Wheel Left) {
    this.rightSide = Right;
    this.leftSide = Left;
  }

  public void autonRotateRobot(int degreesToRotate) {

  }

  public void autonDrive(double leftJoystickY, double rightJoystickY) {
    leftSide.setMotorVelocity(leftJoystickY);
    rightSide.setMotorVelocity(rightJoystickY * -1);

    SmartDashboard.putNumber("autonL", leftJoystickY);
    SmartDashboard.putNumber("autonR", rightJoystickY);
  }

  public void drive(double leftJoystickY, double rightJoystickY) {
    double leftVelocity = leftSpeedLimiter
        .calculate(MathUtil.applyDeadband(leftJoystickY, Constants.deadZone) * Constants.maxSpeed);
    double rightVelocity = rightSpeedLimiter
        .calculate(MathUtil.applyDeadband(rightJoystickY, Constants.deadZone) * Constants.maxSpeed);

    leftSide.setMotorVelocity(leftVelocity * -1);
    rightSide.setMotorVelocity(rightVelocity);

    telemetryPublisher.publishTelemetry("Current Left JoystickY", leftJoystickY * -1);
    telemetryPublisher.publishTelemetry("Current Right JoystickY", rightJoystickY * -1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void stopAll() {
    rightSide.stopMotor();
    leftSide.stopMotor();
    
  }

}