/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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

  private static WPI_TalonSRX frontLeftMotor;   
  private static WPI_TalonSRX frontRightMotor;  //Also, need to set each speed controller to their given id's
  private static WPI_TalonSRX middleLeftMotor;
  private static WPI_TalonSRX middleRightMotor;
  private static WPI_TalonSRX backLeftMotor;
  private static WPI_TalonSRX backRightMotor;

  private static SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeftMotor, middleLeftMotor, backLeftMotor);
  private static SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRightMotor, middleRightMotor, backRightMotor);

  public static DifferentialDrive differentialDriveTrain = new DifferentialDrive(leftMotors, rightMotors);


  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
