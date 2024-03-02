
package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.TelemetryPublisher;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//Shoot command that uses the Shooter subsystem
public class Shoot extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //Variable to hold our Shooter subsystem
  private final Shooter m_shooter;
  //Is either 1 or 2, determines if we want to shoot powerful or weak
  public int shotType;
  //Keeps track of when this command started
  public long startTime;
  private TelemetryPublisher telemetryPublisher = new TelemetryPublisher();

  //This command is constructed within Robot class, found in configAuxBindings() method
  public Shoot(Shooter subsystem, int shotType) {
    m_shooter = subsystem;
    this.shotType = shotType;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
    String curShotMode = SmartDashboard.getString("Current Shot Type", "Off");

    if (shotType == Constants.speakerShot){
      if (curShotMode.equals("Off") || curShotMode.equals("Amp")){
      //This sets the shooter motors to the specific speed we want
      m_shooter.setSpeed(Constants.speakerShotSpeed);
      telemetryPublisher.publishShotTelemetry("Current Shot Type", "Speaker");
      }

      else {
        m_shooter.setSpeed(0);
        telemetryPublisher.publishShotTelemetry("Current Shot Type", "Off");
      }
    }

    else if (shotType == Constants.ampShot){
      if (curShotMode.equals("Off") || curShotMode.equals("Speaker")){
        m_shooter.setSpeed(Constants.ampShotSpeed);
        telemetryPublisher.publishShotTelemetry("Current Shot Type", "Amp");
      }
      else{
        m_shooter.setSpeed(0);
        telemetryPublisher.publishShotTelemetry("Current Shot Type", "Off");
      }


    }

    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stop the shooter motors
    // m_shooter.setSpeed(0);
  }

  // Returns true to call end()
  @Override
  public boolean isFinished() {
    // return (System.currentTimeMillis() - startTime) >= Constants.spinShooterTimeMs;
    return true;
  }
}
