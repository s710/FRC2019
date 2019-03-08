/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//import frc.robot.commands.AllPushUp;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Timer;

public class BackPushUp extends Command {

  private boolean finished= false;
  private int ticker = 0;

  public BackPushUp() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_navigation);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.m_driveTrain.backExtended(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    SmartDashboard.putNumber("BackPushUpTicker", ticker);

    if( !(Math.floor(Robot.m_navigation.getUpAccel()) == 0 && ticker > SmartDashboard.getNumber("Accel Ticker Threshold", 3))) {

      ticker += 1;

      if(Robot.m_navigation.getRoll() < -1*SmartDashboard.getNumber("Angle Threshold: ", 5)){

        System.out.println("Back Freezing");

        Robot.m_driveTrain.freezeBack();

      } else {

        System.out.println("Back Pushing");

        Robot.m_driveTrain.pushUpBack();
        
      }

    } else {

    ticker = 0;
    finished = true;
    }   

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
