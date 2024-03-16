package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Belt;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveBelt extends Command{
    private final Belt m_belt;
    public boolean isOn;
    public boolean isForward;

    public MoveBelt(Belt subsystem, boolean on, boolean forward){
        m_belt = subsystem;
        isOn = on;
        isForward = forward;
        addRequirements(subsystem);
    }
    @Override
    public void initialize() {
        if(isOn){
            if (isForward){
                m_belt.setSpeed(Constants.beltMoveSpeed * -1);
            }

            else {
                m_belt.setSpeed(Constants.beltMoveSpeed);
            }
        }

        else{
            m_belt.setSpeed(0);
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
