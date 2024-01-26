// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.swing.plaf.TreeUI;
import edu.wpi.first.cameraserver.CameraServer;


import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.TelemetryPublisher;

import frc.robot.commands.TelemetryPublisher;
import frc.robot.subsystems.Wheel;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.commands.Autonomous.AutonomousScheduler;
import frc.robot.commands.Autonomous.DriveForwardAction;
import frc.robot.commands.Autonomous.TurnAction;
import frc.robot.commands.Autonomous.DriveReverseAction;
// import frc.robot.subsystems.Accel;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private TelemetryPublisher telemetryPublisher;
  private AutonomousScheduler autoScheduler;
  public AnalogInput ultrasonicSensor = new AnalogInput(0);
  private static Wheel rightSide = new Wheel (Constants.rightMotor);
  private static Wheel leftSide = new Wheel (Constants.leftMotor);
  private static XboxController controllerOne = new XboxController(Constants.driverController);
  private PowerDistribution m_PD = new PowerDistribution();
  private static int counter = 0;
  private int autonStyle;
  public boolean slowMode = false;
  public static TankDrive m_tankdrive = new TankDrive (rightSide, leftSide);
  // public static AccelerometerSubsystem accel = new AccelerometerSubsystem();
  private RobotContainer m_robotContainer;
  public double ultrasonicSensorRange = 0;
  public double voltageScaleFactor = 1;

  // private static GyroSubsystem m_gyro = new GyroSubsystem();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Robot Vision to See
    CameraServer.startAutomaticCapture();
    



    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    
    m_robotContainer = new RobotContainer();
    telemetryPublisher = new TelemetryPublisher();
    autoScheduler = new AutonomousScheduler();
    telemetryPublisher.publishTelemetry("Auton Style", 0);
    telemetryPublisher.publishTelemetry("Ultra Sensor Range", 500);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    telemetryPublisher.publishTelemetry("Ultra Sensor Range", ultrasonicSensorRange);


    voltageScaleFactor = 5/RobotController.getVoltage5V(); //Calculate % of current voltage
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    autonStyle = (int) SmartDashboard.getNumber("Auton Style", 0);

    if (autonStyle == 1){
    // Drive forward for 3 seconds
    autoScheduler.addAction(new DriveForwardAction(3, m_tankdrive));
    // Turn left for 1.5 seconds
    autoScheduler.addAction(new TurnAction(1.5, false, m_tankdrive));
    autoScheduler.init();
    }

    else if (autonStyle == 2){
      m_tankdrive.autonDrive(-0.1, -0.1);
      Timer.delay(5);
      m_tankdrive.autonDrive(0, 0);
    }


  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    autoScheduler.run();
    
  }

  @Override
  public void teleopInit() {


    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //Update our range in cm, dependent on our current voltage
    ultrasonicSensorRange = ultrasonicSensor.getValue()*voltageScaleFactor*0.125;
    double leftY = controllerOne.getLeftY();
    double rightY = controllerOne.getRightY();
    m_tankdrive.drive(leftY, rightY);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  

  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
    
  }
}
