package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Sucker;
import edu.wpi.first.wpilibj2.command.Command;

public class Suck extends Command{
    private final Sucker m_sucker;
    public boolean isOn;

    public Suck (Sucker subsystem, boolean on){
        m_sucker = subsystem;
        isOn = on;
        addRequirements(subsystem);
    }
    @Override
    public void initialize() {
        if(isOn){
            m_sucker.setSpeed(Constants.suckerSuckSpeed * -1);
        }

        else{
            m_sucker.setSpeed(0);
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
