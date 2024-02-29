package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Relay;
import frc.robot.Constants;


public class Solenoid extends SubsystemBase {
private Relay m_relay;
    public Solenoid(int solenoidPort){
        m_relay = new Relay(Constants.solenoidPort);
    }

    public void setForward(){
        //Multiple by -1 to reverse direction
        m_relay.set(Relay.Value.kForward);
    }
    
    public void stopMotor(){
        m_relay.set(Relay.Value.kOff);
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
