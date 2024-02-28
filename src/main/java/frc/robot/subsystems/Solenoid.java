package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.Command;


public class Solenoid extends Command {
    public int m_direction;
    public Relay relay;
    public Solenoid(int direction, Relay m_Relay){
        m_direction = direction;
        relay=m_Relay;
    }

    @Override
    public void initialize() {
        if (m_direction == 1){
            relay.set(Relay.Value.kForward);
        }else{
            relay.set(Relay.Value.kOff);
        }
    }
}
