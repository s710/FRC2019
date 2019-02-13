/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveWithJoysticks extends Command {
  public DriveWithJoysticks() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("About to drive with joysticks command");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_driveTrain.isBackExtended()){
      Robot.m_driveTrain.driveExtension(0.5*Robot.m_oi.joy.getY());
      Robot.m_driveTrain.driveArcade(-0.76*Robot.m_oi.joy.getY(), 0.2*Robot.m_oi.joy.getThrottle());
    } else {
      if( Robot.m_driveTrain.getTankMode()) {
        Robot.m_driveTrain.driveTank(Robot.m_oi.joy);  // the drivetrain object from the Robot class invokes drive()
      } else {
        Robot.m_driveTrain.driveArcade(Robot.m_oi.joy);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
