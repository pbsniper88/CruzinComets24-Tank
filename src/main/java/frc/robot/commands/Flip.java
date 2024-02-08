
package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Sucker;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Flipper;
import edu.wpi.first.wpilibj.Timer;
//Shoot command that uses the Shooter subsystem
public class Flip extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //Variable to hold our Shooter subsystem
  private final Flipper m_flipper;
  //Is either 1 or 2, determines if we want to shoot powerful or weak
  //Keeps track of when this command started
  public long startTime;

  //This command is constructed within Robot class, found in configAuxBindings() method
  public Flip(Flipper subsystem) {
    m_flipper = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
    m_flipper.setSpeed(Constants.flipperFlipSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_flipper.setSpeed(Constants.flipperFlipSpeed * -1);
    Timer.delay(3);
    //Stop the shooter motors
    m_flipper.setSpeed(0);
  }

  // Returns true to call end()
  @Override
  public boolean isFinished() {
    return (System.currentTimeMillis() - startTime) >= Constants.spinShooterTimeMs;
  }
}
