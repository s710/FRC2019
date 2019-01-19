/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(RobotMap.frontLeftMotor);   
  private static WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(RobotMap.frontRightMotor);
  private static WPI_TalonSRX middleLeftMotor;
  private static WPI_TalonSRX middleRightMotor;
  private static WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(RobotMap.frontLeftMotor);
  private static WPI_TalonSRX backRightMotor = new WPI_TalonSRX(RobotMap.backRightMotor);;

  private static SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeftMotor,/* middleLeftMotor,*/ backLeftMotor);
  private static SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRightMotor,/* middleRightMotor,*/ backRightMotor);

  private static DifferentialDrive differentialDriveTrain = new DifferentialDrive(leftMotors, rightMotors);


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveWithJoysticks());
  }

  public void drive(double left, double right) {
    System.out.println("drive(double left, double right) reached");
    differentialDriveTrain.tankDrive(left, right);
  }

  public void drive(Joystick joy){
    System.out.println("Drive(Joystick) reached");
    drive(-joy.getY(), -joy.getThrottle());
  }

}
