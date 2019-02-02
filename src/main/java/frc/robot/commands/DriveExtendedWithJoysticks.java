/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveExtendedWithJoysticks extends Command {
  public DriveExtendedWithJoysticks() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_driveTrain.driveExtension(Robot.m_oi.joy);
    Robot.m_driveTrain.driveArcade(Robot.m_oi.joy.getThrottle(), 0/*Robot.m_oi.joy.getZ()*/);
    if(Robot.m_oi.lb.get()){
      Robot.m_driveTrain.pushUpFront();
    }else{
      Robot.m_driveTrain.retractDownFront();
    }
    if(Robot.m_oi.rb.get()){
      Robot.m_driveTrain.pushUpBack();
    }else{
      Robot.m_driveTrain.retractDownBack();
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
