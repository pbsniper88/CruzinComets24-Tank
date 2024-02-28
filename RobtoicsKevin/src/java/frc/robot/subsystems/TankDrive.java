package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants;

public class TankDrive extends SubsystemBase {
  private Wheel backRight;
  private Wheel backLeft;
  private Wheel frontRight;
  private Wheel frontLeft;


public TankDrive (Wheel backRight, Wheel backLeft, Wheel frontRight, Wheel frontLeft) {
    this.backRight = backRight;
    this.backLeft = backLeft;
    this.frontRight = frontRight;
    this.frontLeft = frontLeft;
}
  public void drive (double leftVelocity, double rightVelocity) {
    frontLeft.setMotorVelocity(leftVelocity);
    backLeft.setMotorVelocity(leftVelocity);
    frontRight.setMotorVelocity(rightVelocity);
    backRight.setMotorVelocity(rightVelocity);
    System.out.println(leftVelocity);
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