package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;

public class SolenoidMove extends Command{
    private final Solenoid m_Solenoid;
    public boolean isOn;

    public SolenoidMove(Solenoid subsystem, boolean isUp){
        m_Solenoid = subsystem;
        isOn = isUp;
        addRequirements(subsystem);
    }
    @Override
    public void initialize() {
        if(isOn){
            m_Solenoid.setForward();
        }

        else{
            m_Solenoid.stopMotor();
        }
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
  
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }
  
    // Returns true to call end()
    @Override
    public boolean isFinished() {
      return true;
    }

    
}
