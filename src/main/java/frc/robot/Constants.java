// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;

    // Assuming the lenth and width of the wheel spacings are even, these can be equal
    // and can be made a constant so its not calculated on each method call. -Shane
    static double L = 1.0;
    static double W = 1.0;
    public static final double drive_r = Math.sqrt ((L * L) + (W * W));
  }
  public static final double deadZone = 0.1;
  public static final double maxSpeed = 1.0;
  //Within FRC Driver Station, go to USB Devices on the left, these variables follow the USB order
  //Click rescan devices when you plug in a new controller
  public static final int driverController = 0;
  public static final int auxControllerPort = 1;

  //You can put auxController in Constants if you want

  public static XboxController auxController = new XboxController(Constants.auxControllerPort);

  //These are the cables connected to the PWN Section on the RoboRio, labled 0-9
  public static final int servoPort = 0;
  public static final int shooterPort = 1;
  public static final int flipperPort = 2;
  public static final int suckerPort = 3;
  public static final int beltPort = 4;


  public static final int leftMotor = 9;
  public static final int rightMotor = 8;

  //Constant if we want to invert the direction of a motor
  public static final double inversion = -1.0;
  // This may need to be scaled.
  public static final double maxShootSpeed = 1.0;

  //Shooter related constants
  public static final int speakerShot = 1;
  public static final int ampShot = 2;
  public static final double speakerShotSpeed = 0.5;
  public static final double ampShotSpeed = 0.33;
  //This determines how long we want to spin the shooter motors when calling Shoot command
  public static final int spinShooterTimeMs = 5000;
  public static final int reverse = -1;

  //Flipper related constants
  public static final double flipperFlipSpeed = 0.02;
  public static final double flipperStaySpeed = 0.01;
  public static final double flipperFlipDuration = 3;

  //Sucker related constants
  public static final double suckerSuckSpeed = .8;

  //Belt related constants
  public static final double beltMoveSpeed = .9;

  //Servo related constnats
  public static final double servoMoveSpeed = 1;
  public static final double servoShootDelaySec = 2;
  

  // Autonomous values
  public static final double AutoTurnRate = 0.4;
  public static final double AutoStallRate = 0.0;
  public static final double AutoDriveRate = 0.5;
  public static final double AutoReverseRate = -0.5;
  public static final double WheelDiameter = 0.5; // Feet
  public static final double Pi = 3.14159265358979; // 15 digits
  public static final double ShooterSpinTimeTillServoPush = 3.0;

}
