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
  private Wheel backRight;
  private Wheel backLeft;
  private Wheel frontRight;
  private Wheel frontLeft;
  private final SlewRateLimiter leftSpeedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter rightSpeedLimiter = new SlewRateLimiter(3);
  private TelemetryPublisher telemetryPublisher = new TelemetryPublisher();



public TankDrive (Wheel backRight, Wheel backLeft, Wheel frontRight, Wheel frontLeft) {
    this.backRight = backRight;
    this.backLeft = backLeft;
    this.frontRight = frontRight;
    this.frontLeft = frontLeft;
}
  public void drive(double leftJoystickY, double rightJoystickY) {
    double leftVelocity = leftSpeedLimiter.calculate(MathUtil.applyDeadband(leftJoystickY, Constants.deadZone)*Constants.maxSpeed);
    double rightVelocity = rightSpeedLimiter.calculate(MathUtil.applyDeadband(rightJoystickY, Constants.deadZone)*Constants.maxSpeed);

    frontLeft.setMotorVelocity(leftVelocity);
    backLeft.setMotorVelocity(leftVelocity);
    frontRight.setMotorVelocity(rightVelocity);
    backRight.setMotorVelocity(rightVelocity);

    telemetryPublisher.publishTelemetry("Current Left JoystickY", leftJoystickY);
    telemetryPublisher.publishTelemetry("Current Right JoystickY", rightJoystickY);

    telemetryPublisher.publishTelemetry("Current Left Velocity", leftVelocity);
    telemetryPublisher.publishTelemetry("Current Right Velocity", rightVelocity);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void stopAll(){
    backRight.stopMotor();
    backLeft.stopMotor();
    frontRight.stopMotor();;
    frontLeft.stopMotor();;
  }


  }