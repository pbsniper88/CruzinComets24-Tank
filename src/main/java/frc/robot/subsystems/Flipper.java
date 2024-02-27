package frc.robot.subsystems;



import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flipper extends SubsystemBase {
    private PWMTalonSRX flipperMotor;

    public Flipper(int flipperPort){
        flipperMotor = new PWMTalonSRX(flipperPort);
    }

    public void setSpeed(double speed){
        //Multiple by -1 to reverse direction
        flipperMotor.set(speed * -1);
    }
    
    public void stopMotor(){
        flipperMotor.set(0);
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
