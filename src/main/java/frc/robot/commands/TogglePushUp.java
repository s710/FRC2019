/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class TogglePushUp extends Command {

  private boolean finished = false;
  private int ticker = 0;

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
      //SmartDashboard.putString("A message to the driver", "You nincompoop");
    } else if (Robot.m_driveTrain.isBackExtended() && Robot.m_driveTrain.isFrontExtended()){ // both true
      Robot.m_driveTrain.retractDownBack();
      Robot.m_driveTrain.retractDownFront();
      Robot.m_driveTrain.setBackExtended(false);
      Robot.m_driveTrain.setFrontExtended(false);
    } else if(!Robot.m_driveTrain.isBackExtended() && !Robot.m_driveTrain.isFrontExtended()){ //both false
      // Robot.m_driveTrain.pushUpFront();
      // Timer.delay(SmartDashboard.getNumber("Back Extend Delay (ms): ", 250)/1000);
      // Robot.m_driveTrain.pushUpBack();

      // if( !(Math.floor(Robot.m_navigation.getUpAccel()) == 0 && ticker > SmartDashboard.getNumber("Accel Ticker Threshold", 50))) {

      //  // ticker += 1;
  
      //   if(Robot.m_navigation.getRoll() < -1*SmartDashboard.getNumber("Angle Threshold: ", 5)){
  
      //     System.out.println("Back Freezing");
        
      //     Robot.m_driveTrain.freezeBack();
  
      //   } else {
  
      //     System.out.println("Back Pushing");
  
      //     Robot.m_driveTrain.pushUpBack();
          
      //   }
      // } else {
      //   //ticker = 0;
      //   Robot.m_driveTrain.setBackExtended(true);
      // }

      // if ( (Math.floor(Robot.m_navigation.getUpAccel())) == 0 ){
      //   ticker += 1;

      // } else {

      //   ticker = 0;

      // }
      System.out.println("Roll: " + Robot.m_navigation.getRoll());
      System.out.println("Acceleration: " + Robot.m_navigation.getUpAccel());
      System.out.println("Front State, extended? " + Robot.m_driveTrain.isFrontExtended());
      System.out.println("Back State, extended? " + Robot.m_driveTrain.isBackExtended());
      System.out.println("Ticker: " + ticker);
      



      while(ticker < SmartDashboard.getNumber("Accel Ticker Threshold", 50)){
        ticker += 1;
        if(Robot.m_navigation.getRoll() > 5.0/*SmartDashboard.getNumber("Angle Threshold: ", 5)*/) {
          Robot.m_driveTrain.freezeFront();
          //Robot.m_driveTrain.retractDownFront();
          Timer.delay((SmartDashboard.getNumber("Flutter Delay (ms)", 24)/1000));
          Robot.m_driveTrain.pushUpFront();
          Robot.m_driveTrain.pushUpBack();

          System.out.println("Freezing front");
          //System.out.println(Robot.m_navigation.getRoll());

        } else if(Robot.m_navigation.getRoll() < -5.0/*1*SmartDashboard.getNumber("Angle Threshold: ", 5)*/){
          Robot.m_driveTrain.freezeBack();
          //Robot.m_driveTrain.retractDownBack();
          Timer.delay((SmartDashboard.getNumber("Flutter Delay (ms)", 24)/1000));
          Robot.m_driveTrain.pushUpBack();
          Robot.m_driveTrain.pushUpFront();

          System.out.println("Freezing back");
          //System.out.println(Robot.m_navigation.getRoll());

        }else{
  
          // Robot.m_driveTrain.pushUpFront();
          Robot.m_driveTrain.pushUpBack();
          Timer.delay(SmartDashboard.getNumber("Back Extend Delay (ms): ", 250)/1000);
          // Robot.m_driveTrain.pushUpBack();
          Robot.m_driveTrain.pushUpFront();

          System.out.println("Pushing up both");
          //System.out.println("Roll: " + Robot.m_navigation.getRoll());
        }
        // System.out.println("Roll: " + Robot.m_navigation.getRoll());
        // System.out.println("Acceleration: " + Robot.m_navigation.getUpAccel());
        // System.out.println("Front State, extended? " + Robot.m_driveTrain.isFrontExtended());
        // System.out.println("Back State, extended? " + Robot.m_driveTrain.isBackExtended());
        // System.out.println("Ticker: " + ticker);
      }
      System.out.println("Ticker count reached");
      ticker = 0;
      Robot.m_driveTrain.setFrontExtended(true);
      Robot.m_driveTrain.setBackExtended(true);
      finished = true;
  
      




/**
      if( ticker < SmartDashboard.getNumber("Accel Ticker Threshold", 50)) {
        ticker += 1;
        if(Robot.m_navigation.getRoll() > 5.0/*SmartDashboard.getNumber("Angle Threshold: ", 5)* /) {
          Robot.m_driveTrain.freezeFront();
          //Robot.m_driveTrain.retractDownFront();
          Timer.delay((SmartDashboard.getNumber("Flutter Delay (ms)", 24)/1000));
          Robot.m_driveTrain.pushUpFront();
          Robot.m_driveTrain.pushUpBack();

          System.out.println("Freezing front");
          //System.out.println(Robot.m_navigation.getRoll());

        } else if(Robot.m_navigation.getRoll() < -5.0/*1*SmartDashboard.getNumber("Angle Threshold: ", 5)* /){
          Robot.m_driveTrain.freezeBack();
          //Robot.m_driveTrain.retractDownBack();
          Timer.delay((SmartDashboard.getNumber("Flutter Delay (ms)", 24)/1000));
          Robot.m_driveTrain.pushUpBack();
          Robot.m_driveTrain.pushUpFront();

          System.out.println("Freezing back");
          //System.out.println(Robot.m_navigation.getRoll());

        }else{
  
          // Robot.m_driveTrain.pushUpFront();
          Robot.m_driveTrain.pushUpBack();
          Timer.delay(SmartDashboard.getNumber("Back Extend Delay (ms): ", 250)/1000);
          // Robot.m_driveTrain.pushUpBack();
          Robot.m_driveTrain.pushUpFront();

          System.out.println("Pushing up both");
          //System.out.println("Roll: " + Robot.m_navigation.getRoll());
        }
        // System.out.println("Roll: " + Robot.m_navigation.getRoll());
        // System.out.println("Acceleration: " + Robot.m_navigation.getUpAccel());
        // System.out.println("Front State, extended? " + Robot.m_driveTrain.isFrontExtended());
        // System.out.println("Back State, extended? " + Robot.m_driveTrain.isBackExtended());
        // System.out.println("Ticker: " + ticker);

  
      } else {
        System.out.println("Ticker count reached");
        ticker = 0;
        Robot.m_driveTrain.setFrontExtended(true);
        Robot.m_driveTrain.setBackExtended(true);
        finished = true;
      }  
*/






      
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
