// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.swing.plaf.TreeUI;
import edu.wpi.first.cameraserver.CameraServer;


import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.Drive;
import frc.robot.subsystems.Wheel;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.GyroSubsystem;
// import frc.robot.subsystems.Accel;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  public AnalogInput ultrasonicSensor = new AnalogInput(0);
  private static Wheel backRight = new Wheel (4);
  private static Wheel backLeft = new Wheel (3);
  private static Wheel frontRight = new Wheel (2);
  private static Wheel frontLeft = new Wheel (1);
  private static XboxController controllerOne = new XboxController(0);
  private PowerDistribution m_PD = new PowerDistribution();
  private static int counter = 0;
  private int autonStyle;
  public boolean slowMode = false;
  public static TankDrive m_tankdrive = new TankDrive (backRight, backLeft, frontRight, frontLeft);
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
    SmartDashboard.putNumber("Auton Style", 0);
    SmartDashboard.putNumber("Ultra Sensor Range", 500);
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
    SmartDashboard.putNumber("Ultra Sensor Range", ultrasonicSensor.getVoltage());


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
    // new SequentialCommandGroup(new AutonMove(1.9, -0.6,0 ), new WaitCommand(1)).schedule();
    autonStyle = (int) SmartDashboard.getNumber("Auton Style", 0);

  


    // // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    //test out zeroize encoders 
     m_tankdrive.zeroizeEncoders();
     counter++;
     if(counter%100==0){
     SmartDashboard.putNumber("Talon 1 Position", frontLeft.getSensorValue() );
     SmartDashboard.putNumber("Talon 2 Position", frontRight.getSensorValue() );
     SmartDashboard.putNumber("Talon 3 Position", backLeft.getSensorValue());
     SmartDashboard.putNumber("Talon 4 Position", backRight.getSensorValue());
   }

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //help 

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    // new Drive().schedule();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //Update our range in cm, dependent on our current voltage
    ultrasonicSensorRange = ultrasonicSensor.getValue()*voltageScaleFactor*0.125;
    double leftY = controllerOne.getLeftY();
    double rightY = controllerOne.getRightY();


    if(Math.abs(leftY)>0.1 || Math.abs(rightY)>0.1){
      m_tankdrive.drive(leftY, rightY);
      SmartDashboard.putNumber("Velocity Left", leftY);
      SmartDashboard.putNumber("Velocity Right", rightY);

    }
    else{
      m_tankdrive.stopAll();
      SmartDashboard.putNumber("Velocity Left", 0);
      SmartDashboard.putNumber("Velocity Right", 0);
    }

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
