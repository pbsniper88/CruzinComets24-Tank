// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.functions;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final int driverController = 0;
  public static final int auxController = 1;
  public static final int leftMotor = 0;
  public static final int rightMotor = 1;
  public static final int shooterMotor = 2;

  public static final double inversion = -1.0;
  public static final double deadZone = 0.15;

  // This may need to be scaled.
  public static final double maxSpeed = 1.0;
  public static final double maxShootSpeed = 1.0;

  // Autonomous values
  public static final double AutoTurnRate = 0.4;
  public static final double AutoStallRate = 0.0;
  public static final double AutoDriveRate = 0.5;
  public static final double AutoReverseRate = -0.2;

}
