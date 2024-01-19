package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import frc.robot.functions.Constants;
import frc.robot.functions.TelemetryPublisher;

public class DriveTrain {
    private PWMTalonSRX leftMotor;
    private PWMTalonSRX rightMotor;
    // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
    private final SlewRateLimiter leftSpeedLimiter = new SlewRateLimiter(3);
    private final SlewRateLimiter rightSpeedLimiter = new SlewRateLimiter(3);
    private TelemetryPublisher telemetryPublisher = new TelemetryPublisher();

    public DriveTrain(int leftMotor, int rightMotor)
    {
        this.leftMotor = new PWMTalonSRX(leftMotor);
        this.rightMotor = new PWMTalonSRX(rightMotor);
    }

    public void drive(double leftStick, double rightStick)
    {
        // Use slewrate speed limiters to prevent sudden jerky movements when starting
        var leftSpeed = leftSpeedLimiter.calculate(MathUtil.applyDeadband(leftStick, Constants.deadZone)*Constants.maxSpeed);
        var rightSpeed = rightSpeedLimiter.calculate(MathUtil.applyDeadband(rightStick, Constants.deadZone)*Constants.maxSpeed);

        // One side needs to be inverted. 
        // (Vannan likes to do this by inverting wires, don't let him!)
        rightSpeed = rightSpeed * Constants.inversion;

        // Tell the motors to start moving
        leftMotor.set(leftSpeed);
        rightMotor.set(rightSpeed);

        telemetryPublisher.publishTelemetry("Current Left Value", leftStick);
        telemetryPublisher.publishTelemetry("Current Right Value", rightStick);

        telemetryPublisher.publishTelemetry("Current Left Speed", leftSpeed);
        telemetryPublisher.publishTelemetry("Current Right Speed", rightSpeed);
    }
}

