package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Servo;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveServo extends Command{
    private final Servo m_servo;
    public boolean isOn;

    public MoveServo(Servo subsystem, boolean on){
        m_servo = subsystem;
        isOn = on;
        addRequirements(subsystem);
    }
    @Override
    public void initialize() {
        if(isOn){
            m_servo.setSpeed(Constants.servoMoveSpeed);
        }

        else{
            m_servo.setSpeed(0);
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
