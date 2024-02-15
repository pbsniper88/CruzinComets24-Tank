package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import frc.robot.Constants;
import frc.robot.commands.TelemetryPublisher;

/*
Tank drive is where you have two sets of wheels. 
Each set of wheel goes in the same direction, like a tank.
*/
public class TankDrive extends SubsystemBase {
  /* Each motor controls 3 wheels, 
  so to control 6 wheels we only need two Wheels which are actually motors.*/ 
  private Wheel rightSide;
  private Wheel leftSide;
  /* 
  Slew rate clamps acceleration. If the magnitude of the acceleration exceeds the slew rate value
  the value will be clamped to the slew rate value.
  */
  private final SlewRateLimiter leftSpeedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter rightSpeedLimiter = new SlewRateLimiter(3);
  private TelemetryPublisher telemetryPublisher = new TelemetryPublisher();

  // Asigns the wheels to the TankDrive object.
  public TankDrive(Wheel Right, Wheel Left) {
    this.rightSide = Right;
    this.leftSide = Left;
  }

  public void autonRotateRobot(int degreesToRotate) {

  }

  public void autonDrive(double leftY, double rightY) {
    leftSide.setMotorVelocity(leftY);
    /* 
    Someone screwed up the wiring, so the right joystick has to be reversed.
    If a future team wired the robot correctly, remove Constants.reverse.
    */
    rightSide.setMotorVelocity(rightY * Constants.reverse);

    // Displays the autonomous speeds to the smart dashboard for debugging.
    SmartDashboard.putNumber("autonL", leftY);
    SmartDashboard.putNumber("autonR", rightY);
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