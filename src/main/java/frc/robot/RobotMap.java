/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//import com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  public static int frontLeftMotor;   //probably isn't actually going to be an integer. Look into WPI_TalonSRX data type. Might need to import it
  public static int frontRightMotor;  //Also, need to set each speed controller to their given id's
  public static int middleLeftMotor;
  public static int middleRightMotor;
  public static int backLeftMotor;
  public static int backRightMotor;

  public static DifferentialDrive differentialDriveTrain;


  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
