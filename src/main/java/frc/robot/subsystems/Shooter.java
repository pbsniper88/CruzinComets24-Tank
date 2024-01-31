package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import frc.robot.functions.Constants;
import frc.robot.functions.TelemetryPublisher;

public class Shooter {
    private PWMTalonSRX shooterMotor;

    // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
    // Keep this to prevent brownouts
    private final SlewRateLimiter shooterSpeedLimiter = new SlewRateLimiter(3);

    private TelemetryPublisher telemetryPublisher = new TelemetryPublisher();

    public Shooter(int shooterMotor)
    {
        this.shooterMotor = new PWMTalonSRX(shooterMotor);
    }

    public void forward()
    {
        // Use slewrate speed limiters to prevent sudden jerky movements when starting
        var shooterSpeed = shooterSpeedLimiter.calculate(MathUtil.applyDeadband(Constants.maxShootSpeed, Constants.deadZone)*Constants.maxSpeed);

        // Tell the motors to start moving
        shooterMotor.set(shooterSpeed);

        telemetryPublisher.publishTelemetry("Current Shooter Speed", shooterSpeed);
    }

    public void reverse()
    {
        // Use slewrate speed limiters to prevent sudden jerky movements when starting
        var shooterSpeed = -shooterSpeedLimiter.calculate(MathUtil.applyDeadband(Constants.maxShootSpeed, Constants.deadZone)*Constants.maxSpeed);

        // Tell the motors to start moving
        shooterMotor.set(shooterSpeed);

        telemetryPublisher.publishTelemetry("Current Shooter Speed", shooterSpeed);
    }

    public void stop()
    {
        // Use slewrate speed limiters to prevent sudden jerky movements when starting
        var shooterSpeed = Constants.AutoStallRate;
        
        // Tell the motors to start moving
        shooterMotor.set(shooterSpeed);

        telemetryPublisher.publishTelemetry("Current Shooter Speed", shooterSpeed);
    }
}

