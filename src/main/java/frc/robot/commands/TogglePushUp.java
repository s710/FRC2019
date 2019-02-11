/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TogglePushUp extends Command {

  private boolean finished = false;

  public TogglePushUp() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_driveTrain.isBackExtended() != Robot.m_driveTrain.isFrontExtended()){
      SmartDashboard.putString("A message to the driver", "You nincompoop");
    } else if (Robot.m_driveTrain.isBackExtended() && Robot.m_driveTrain.isFrontExtended()){ // both true
      Robot.m_driveTrain.retractDownBack();
      Robot.m_driveTrain.retractDownFront();
    } else if(!Robot.m_driveTrain.isBackExtended() && !Robot.m_driveTrain.isFrontExtended()){ //both false
      Robot.m_driveTrain.pushUpBack();
      Robot.m_driveTrain.pushUpFront();
    }
    finished = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
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
