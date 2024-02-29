package frc.robot.subsystems;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Wheel extends SubsystemBase{
private PWMTalonSRX driveMotor;

public Wheel (int driveMotor) {
    this.driveMotor = new PWMTalonSRX(driveMotor);
}

//Pass in negative to go backward
public void setMotorVelocity(double speed) {
    driveMotor.set(speed);
    
}

public void stopMotor(){
    driveMotor.set(0);
    }
}
