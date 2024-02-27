package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.Command;


public class Solenoid extends Command {
    public int m_direction;
    public Relay m_relay = new Relay(0);
    public Solenoid(int direction){
        m_direction = direction;
    }

    @Override
    public void initialize() {
        if (m_direction == 1){
            m_relay.set(Relay.Value.kForward);
        }else{
            m_relay.set(Relay.Value.kOff);
        }
    }
}
