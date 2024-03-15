// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Solenoid
import edu.wpi.first.wpilibj.Relay;

import javax.swing.plaf.TreeUI;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoMode;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.commands.Autonomous.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController.Button;



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
  private static Shooter shooter = new Shooter(Constants.shooterPort);
  private static Flipper flipper = new Flipper(Constants.flipperPort);
  private static Sucker sucker = new Sucker(Constants.suckerPort);
  private static Belt belt = new Belt(Constants.beltPort);
  private final SpiralSpinner spinner = new SpiralSpinner();
  private final Launcher launcher = new Launcher();
  // private static Servo servo = new Servo(Constants.servoPort);
  private static Solenoid solenoid = new Solenoid(Constants.solenoidPort);
  private static XboxController controllerOne = new XboxController(Constants.driverController);
  private PowerDistribution m_PD = new PowerDistribution();
  private static int counter = 0;
  private int autonStyle;
  private int secondsToWait;
  private double secondsRunning;
  public boolean slowMode = false;
  public static TankDrive m_tankdrive = new TankDrive (rightSide, leftSide);
  // public static AccelerometerSubsystem accel = new AccelerometerSubsystem();
  private RobotContainer m_robotContainer;
  public double ultrasonicSensorRange = 0;
  public double voltageScaleFactor = 1;
  public int desiredRPM;
  

  // private static GyroSubsystem m_gyro = new GyroSubsystem();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Robot Vision to See
    UsbCamera camera = CameraServer.startAutomaticCapture();
    VideoMode videoMode = new VideoMode(1, 1280, 720, 10);
    camera.setVideoMode(videoMode);
  
    configAuxBindings();
    



    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    
    m_robotContainer = new RobotContainer();
    telemetryPublisher = new TelemetryPublisher();
    autoScheduler = new AutonomousScheduler();
    SmartDashboard.putNumber("Auton Style", 0);
    SmartDashboard.putNumber("Seconds Running", 0);
    SmartDashboard.putNumber("Ultra Sensor Range", 0);
    SmartDashboard.putNumber("Speaker Shooter Voltage", 0);
    SmartDashboard.putNumber("Amp Shooter Voltage", 0);
    SmartDashboard.putNumber("Auton Wait Time", 0);
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
  public void disabledInit() {
    telemetryPublisher.publishShotTelemetry("Current Shot Type", "Off");
    shooter.stopMotor();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {


    autonStyle = (int) SmartDashboard.getNumber("Auton Style", 0);
    secondsRunning = (double) SmartDashboard.getNumber("Seconds Running", 0);
    secondsToWait = (int) SmartDashboard.getNumber("Auton Wait Time", 0);
    //TurnAction for approx .3 seconds does 45 degrees
    //TurnAction for approx .5 seconds does 90 degrees
    //TurnAction for approx .9 seconds does 180 degrees
    //DriveForwardAction for approx 0.5 second is 55 inches
    //DriveForwardAction for approx 1 second is 110 inches
    //DriveForwardAction for approx 2 seconds is 188 inches
    //DriveReverseAction for approx 1 second is 

    //Wall to endAmpZone is 133 in
    //Wall to middleOfAmp is approx 80 in
    //Speaker to wall is 3 feet, 36 in
    //Amp zone 130 in
    //Speaker to amp zone is 94 in
    //DF for 0.75 to get near
    //Start auton 1 ft
    


    
    //Auton 1 - Robot is flush and touching with the back wall, facing away from the drivers.
    //Robot will be 6 inches away from the wall on the right (Measured Bumper to wall)

    //Red team amp shot auton 1
    if (autonStyle == 1){
    autoScheduler.addAction(new WaitAction(secondsToWait));
    autoScheduler.addAction(new FlipAction(0.1, flipper, 3));
    autoScheduler.addAction(new DriveForwardAction(Constants.wallToAmpCentered, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.addAction(new TurnAction(Constants.ninetyDeg, false, m_tankdrive));
    autoScheduler.addAction(new DriveReverseAction(0.3, m_tankdrive, Constants.AutoReverseRate));
    autoScheduler.addAction(new ShootWithSoleAction(shooter, solenoid, Constants.ampShot));
    //Get away from the amp slightly
    autoScheduler.addAction(new DriveForwardAction(.2, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.addAction(new TurnAction(Constants.ninetyDeg, true, m_tankdrive));
    //Should be positioned ahead of the spike mark
    autoScheduler.addAction(new DriveForwardAction(0.6, m_tankdrive, Constants.AutoDriveRate));

    autoScheduler.init();
    }
    //Blue team amp shot auton 2
    else if (autonStyle == 2){
    autoScheduler.addAction(new WaitAction(secondsToWait));
    autoScheduler.addAction(new FlipAction(0.1, flipper, 3));
    autoScheduler.addAction(new DriveForwardAction(Constants.wallToAmpCentered, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.addAction(new TurnAction(Constants.ninetyDeg, true, m_tankdrive));
    autoScheduler.addAction(new DriveReverseAction(0.3, m_tankdrive, Constants.AutoReverseRate));
    autoScheduler.addAction(new ShootWithSoleAction(shooter, solenoid, Constants.ampShot));
    //Get away from the amp slightly
    autoScheduler.addAction(new DriveForwardAction(.2, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.addAction(new TurnAction(Constants.ninetyDeg, false, m_tankdrive));
    //Should be positioned ahead of the spike mark
    autoScheduler.addAction(new DriveForwardAction(0.6, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.init();
    }

    //Both team speaker shot (straight) auton 3
    else if (autonStyle == 3){
    autoScheduler.addAction(new WaitAction(secondsToWait));
    autoScheduler.addAction(new FlipAction(0.1, flipper, 3));
    autoScheduler.addAction(new LauncherWithSoleAction(launcher, solenoid, Constants.speakerShot));
    autoScheduler.addAction(new DriveForwardAction(0.85, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.init();
    }

    else if (autonStyle == 4){
    autoScheduler.addAction(new WaitAction(secondsToWait));
    autoScheduler.addAction(new FlipAction(0.1, flipper, 3));
    autoScheduler.addAction(new LauncherWithSoleAction(launcher, solenoid, Constants.speakerShot));
    autoScheduler.addAction(new DriveForwardAction(1.3, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.init();
    }

    //Red team placement near amp auton 5
    else if (autonStyle == 5){
    autoScheduler.addAction(new WaitAction(secondsToWait));
    autoScheduler.addAction(new FlipAction(0.1, flipper, 3));
    autoScheduler.addAction(new LauncherWithSoleAction(launcher, solenoid, Constants.speakerShot));
    autoScheduler.addAction(new DriveForwardAction(0.2, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.addAction(new TurnAction(0.3, false, m_tankdrive));
    autoScheduler.addAction(new DriveForwardAction(0.8, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.init();
    }

    //Blue team placement near amp auton 6
    else if (autonStyle == 6){
    autoScheduler.addAction(new WaitAction(secondsToWait));
    autoScheduler.addAction(new FlipAction(0.1, flipper, 3));
    autoScheduler.addAction(new LauncherWithSoleAction(launcher, solenoid, Constants.speakerShot));
    autoScheduler.addAction(new DriveForwardAction(0.2, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.addAction(new TurnAction(0.3, true, m_tankdrive));
    autoScheduler.addAction(new DriveForwardAction(0.8, m_tankdrive, Constants.AutoDriveRate));
    autoScheduler.init();
    }
    //Auton 3 but it will attempt to pick up another piece
    else if (autonStyle == 7){
    autoScheduler.addAction(new WaitAction(secondsToWait));
    autoScheduler.addAction(new LauncherWithSoleAction(launcher, solenoid, Constants.speakerShot));
    autoScheduler.addAction(new FlipAction(1, flipper, 2));
    autoScheduler.addAction(new DriveAndSuckAction(1.5, m_tankdrive, sucker));
    autoScheduler.addAction(new SuckAction(2.5, sucker));
    autoScheduler.addAction(new BeltAction(4, belt));
    autoScheduler.addAction(new SolenoidAction(1, solenoid));
    autoScheduler.addAction(new DriveReverseAction(1.7, m_tankdrive, Constants.AutoReverseRate));
    autoScheduler.addAction(new LauncherWithSoleAction(launcher, solenoid, Constants.speakerShot));
    autoScheduler.init();
    }

    else if (autonStyle == 8){
      autoScheduler.addAction(new WaitAction(secondsToWait));
      autoScheduler.addAction(new LauncherWithSoleAction(launcher, solenoid, Constants.speakerShot));
      autoScheduler.addAction(new DriveReverseAction(0.4, m_tankdrive, Constants.AutoReverseRate));
      //Teleop Code messing with auton
      autoScheduler.init();
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

  public void configAuxBindings(){
    // JoystickButton shootSpeakerButton = new JoystickButton(Constants.auxController, Button.kX.value);
    // shootSpeakerButton.onTrue(new Shoot(shooter, Constants.speakerShot));

    // JoystickButton shootAmpButton = new JoystickButton(Constants.auxController, Button.kY.value);
    // shootAmpButton.onTrue(new Shoot(shooter, Constants.ampShot));

    JoystickButton shootSpeakerButton = new JoystickButton(Constants.auxController, Button.kX.value);
    shootSpeakerButton.onTrue(new SetLauncherVelocity(launcher, Constants.LauncherConstants.targetSpeakerRPM));

    JoystickButton shootSpeakerButton2 = new JoystickButton(Constants.auxController, Button.kY.value);
    shootSpeakerButton2.onTrue(new SetLauncherVelocity(launcher, Constants.LauncherConstants.targetAmpRPM));

    JoystickButton shootSpeakerButton3 = new JoystickButton(Constants.auxController, Button.kLeftStick.value);
    shootSpeakerButton3.onTrue(new SetLauncherVelocity(launcher, 0));

    JoystickButton suckButton = new JoystickButton(controllerOne, Button.kRightBumper.value);
    suckButton.whileTrue(new Suck(sucker, true));
    suckButton.onFalse(new Suck(sucker, false));

    JoystickButton beltButton = new JoystickButton(Constants.auxController, Button.kA.value);
    beltButton.whileTrue(new MoveBelt(belt, true));
    beltButton.onFalse(new MoveBelt(belt, false));

    // JoystickButton servoButton = new JoystickButton(Constants.auxController, Button.kRightBumper.value);
    // servoButton.whileTrue(new MoveServo(servo, true));
    // servoButton.onFalse(new MoveServo(servo, false));

    JoystickButton solenoidButton = new JoystickButton(Constants.auxController, Button.kB.value);
    solenoidButton.onTrue(new SolenoidMove(solenoid, true));
    solenoidButton.whileFalse(new SolenoidMove(solenoid, false));


    JoystickButton flipUpButton = new JoystickButton(Constants.auxController, Button.kRightBumper.value);
    flipUpButton.whileTrue(new Flip(flipper, 0));
    flipUpButton.onFalse(new Flip(flipper, 1));

    JoystickButton loosenFlipperButton = new JoystickButton(Constants.auxController, Button.kLeftBumper.value);
    loosenFlipperButton.whileTrue(new Flip(flipper, 2));
    loosenFlipperButton.onFalse(new Flip(flipper, 1));

    JoystickButton activateFlipperPassivePowerButton = new JoystickButton(Constants.auxController, Button.kRightStick.value);
    activateFlipperPassivePowerButton.onTrue(new Flip(flipper, 3));

    JoystickButton spinSpiralButton = new JoystickButton(controllerOne, Button.kY.value);
    spinSpiralButton.whileTrue(new SetSpiralSpinnerVelocity(spinner, Constants.SpiralSpinnerConstants.spiralSpinnerVelocty));
    
    JoystickButton spinSpiralButton2 = new JoystickButton(controllerOne, Button.kX.value);
    spinSpiralButton2.whileTrue(new SetSpiralSpinnerVelocity(spinner, -Constants.SpiralSpinnerConstants.spiralSpinnerVelocty));



  }
}
