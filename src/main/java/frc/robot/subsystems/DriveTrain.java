/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
//import frc.robot.commands.ChangeExtendedState;
import frc.robot.commands.DriveWithJoysticks;
//import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.ctre.phoenix.motorcontrol.NeutralMode;
//import edu.wpi.first.wpilibj.Timer;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // private static WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(RobotMap.frontLeftMotor);   
  // private static WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(RobotMap.frontRightMotor);
  // private static WPI_TalonSRX middleLeftMotor = new WPI_TalonSRX(RobotMap.middleLeftMotor);
  // private static WPI_TalonSRX middleRightMotor = new WPI_TalonSRX(RobotMap.middleRightMotor);
  // private static WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(RobotMap.backLeftMotor);
  // private static WPI_TalonSRX backRightMotor = new WPI_TalonSRX(RobotMap.backRightMotor);

  private static WPI_VictorSPX frontLeftMotor = new WPI_VictorSPX(1);   
  private static WPI_VictorSPX frontRightMotor = new WPI_VictorSPX(2);
  private static WPI_VictorSPX middleLeftMotor = new WPI_VictorSPX(3);
  private static WPI_VictorSPX middleRightMotor = new WPI_VictorSPX(4);
  private static WPI_VictorSPX backLeftMotor = new WPI_VictorSPX(5);
  private static WPI_VictorSPX backRightMotor = new WPI_VictorSPX(6);

  private static SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeftMotor, middleLeftMotor, backLeftMotor);
  private static SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRightMotor, middleRightMotor, backRightMotor);

  private static DifferentialDrive differentialDriveTrain = new DifferentialDrive(leftMotors, rightMotors);

  private static WPI_VictorSPX extendedMotor = new WPI_VictorSPX(8);

  private boolean driveTankMode = false;
  private boolean driveExtended =false;

  private double driveSpeed = 1.0;

  private boolean frontExtended = false;
  private boolean backExtended = false;
  // private boolean extended = false;

  //Pneumatics will be used in the drivetrain. The following is where that is:
  private static DoubleSolenoid frontNoid = new DoubleSolenoid(0,1);//Rename these
  private static DoubleSolenoid frontNoidSecondary = new DoubleSolenoid(2,3);
  private static DoubleSolenoid backNoid = new DoubleSolenoid(4,5);
  //private static DoubleSolenoid thirdNoid;
  //private static DoubleSolenoid fourthNoid;//Not sure how many we need

  public DriveTrain(){
    super();
    frontLeftMotor.configOpenloopRamp(.5);
    middleLeftMotor.configOpenloopRamp(.5);
    backLeftMotor.configOpenloopRamp(.5);
    frontRightMotor.configOpenloopRamp(.5);
    middleRightMotor.configOpenloopRamp(.5);
    backRightMotor.configOpenloopRamp(.5);
    frontNoid.set(DoubleSolenoid.Value.kReverse);
    backNoid.set(DoubleSolenoid.Value.kReverse);
    //frontRightMotor.setNeutralMode(NeutralMode.Brake);
    // frontRightMotor.setNeutralMode(NeutralMode.Coast);
    // middleRightMotor.setNeutralMode(NeutralMode.Coast);
    // backRightMotor.setNeutralMode(NeutralMode.Coast);
    // frontLeftMotor.setNeutralMode(NeutralMode.Coast);
    // middleLeftMotor.setNeutralMode(NeutralMode.Coast);
    // backLeftMotor.setNeutralMode(NeutralMode.Coast);
    // extendedMotor.setNeutralMode(NeutralMode.Coast);
  }

  // //TIMER STUFF. COMPLICATED. FOURTH DIMENSIONAL MAGIC. MIGHT EXPLODE. CAUTION.
  // private double timeFrozen;
  // private double timeCurrent;
  // private double timePassed;
  // public void beginTheTimeThing(){
  //   timeFrozen = Timer.getFPGATimestamp();
  // }
  // public void timePassing(){
  //   timeCurrent = Timer.getFPGATimestamp();
  //   timePassed = (timeCurrent-timeFrozen);
  // }
  // public double getTimePassed(){
  //   return timePassed;
  // }
  // /*
  //  * Here are the basics for this time thing.
  //  * It does stuff.
  //  */



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveWithJoysticks());
  }

  public void driveTank(double left, double right) {
    System.out.println("drive(double left, double right) reached");
    differentialDriveTrain.tankDrive(/*leftSpeed**/driveSpeed*left, /*rightSpeed**/driveSpeed*right);
    // differentialDriveTrain.arcadeDrive(speed, rotation);
  }
  public void driveTank(Joystick joy){
    System.out.println(-joy.getY() + " " + -joy.getThrottle());
    driveTank(-joy.getY(), -joy.getThrottle());
  }


  public void driveArcade(double speed, double rotation) {
    differentialDriveTrain.arcadeDrive(/*leftSpeed**/driveSpeed*speed, driveSpeed*rotation);
  }
  public void driveArcade(Joystick joy) {
    driveArcade(-joy.getY()*1.0, joy.getX()*0.75);
  }

//\\ duh duh dudududu duh duh \\//

  //Pneumatic pushup code
  public void pushUpFront () { 
    //pneumatics pushes robot up to climb
    frontNoid.set(DoubleSolenoid.Value.kForward);
    frontNoidSecondary.set(DoubleSolenoid.Value.kForward);
    //frontExtended = true;
  }
  public void setFrontExtended(boolean isExtended){
    // if(isExtended){
    //   frontRightMotor.setNeutralMode(NeutralMode.Brake);
    //   middleRightMotor.setNeutralMode(NeutralMode.Brake);
    //   backRightMotor.setNeutralMode(NeutralMode.Brake);
    //   frontLeftMotor.setNeutralMode(NeutralMode.Brake);
    //   middleLeftMotor.setNeutralMode(NeutralMode.Brake);
    //   backLeftMotor.setNeutralMode(NeutralMode.Brake);
    //   extendedMotor.setNeutralMode(NeutralMode.Brake);
    // } else {
    //   // frontRightMotor.setNeutralMode(NeutralMode.Coast);
    //   // middleRightMotor.setNeutralMode(NeutralMode.Coast);
    //   // backRightMotor.setNeutralMode(NeutralMode.Coast);
    //   // frontLeftMotor.setNeutralMode(NeutralMode.Coast);
    //   // middleLeftMotor.setNeutralMode(NeutralMode.Coast);
    //   // backLeftMotor.setNeutralMode(NeutralMode.Coast);
    //   // extendedMotor.setNeutralMode(NeutralMode.Coast);
    // }
    frontExtended = isExtended;
  }
  public void pushUpBack() {
    backNoid.set(DoubleSolenoid.Value.kForward)wd;
    //backExtended = true;
  }
  public void setBackExtended(boolean isExtended) {
    // if(isExtended){
    //   frontRightMotor.setNeutralMode(NeutralMode.Brake);
    //   middleRightMotor.setNeutralMode(NeutralMode.Brake);
    //   backRightMotor.setNeutralMode(NeutralMode.Brake);
    //   frontLeftMotor.setNeutralMode(NeutralMode.Brake);
    //   middleLeftMotor.setNeutralMode(NeutralMode.Brake);
    //   backLeftMotor.setNeutralMode(NeutralMode.Brake);
    //   extendedMotor.setNeutralMode(NeutralMode.Brake);
    // } else {
    //   // frontRightMotor.setNeutralMode(NeutralMode.Coast);
    //   // middleRightMotor.setNeutralMode(NeutralMode.Coast);
    //   // backRightMotor.setNeutralMode(NeutralMode.Coast);
    //   // frontLeftMotor.setNeutralMode(NeutralMode.Coast);
    //   // middleLeftMotor.setNeutralMode(NeutralMode.Coast);
    //   // backLeftMotor.setNeutralMode(NeutralMode.Coast);
    //   // extendedMotor.setNeutralMode(NeutralMode.Coast);
    // }
    backExtended = isExtended;
  }
  public void retractDownFront() {
    //pneumatics retracts robot
    frontNoid.set(DoubleSolenoid.Value.kReverse);
    frontNoidSecondary.set(DoubleSolenoid.Value.kReverse);
    //frontExtended = false;
  }
  public void retractDownBack() {
    backNoid.set(DoubleSolenoid.Value.kReverse);
    //backExtended = false;
  }
  // public void extendState() {
  //   extended = true;
  // }
  // public void retractState() {
  //   extended = false;
  // }

  public void freezeFront() {
   
  //  frontNoid.set(DoubleSolenoid.Value.kReverse);

  //  Timer.delay(SmartDashboard.getNumber("Flutter Delay (ms): ",24)/1000);

   frontNoid.set(DoubleSolenoid.Value.kOff);
  }

  public void freezeBack() {
    // if(isBackExtended()){
    //   //System.out.println("Reverse!");
    //   // backNoid.set(DoubleSolenoid.Value.kReverse);
    //   // Timer.delay(SmartDashboard.getNumber("Flutter Delay (ms): ",24)/1000);
    //   //System.out.println("Forward!");
    //   backNoid.set(DoubleSolenoid.Value.kOff);

    // } else {
    //   //System.out.println("Forward!");
    //   backNoid.set(DoubleSolenoid.Value.kForward);
    //   Timer.delay(SmartDashboard.getNumber("Flutter Delay (ms): ",24)/1000);
    //   //System.out.println("Reverse!");
    //   backNoid.set(DoubleSolenoid.Value.kReverse);
    // }
    backNoid.set(DoubleSolenoid.Value.kOff);

   
  }


  public void driveExtension(double speed) {
    extendedMotor.set(speed);
  }
  public void driveExtension(Joystick joy){
    driveExtension(joy.getY());
  }
  public void stopDriveExtension() {
    extendedMotor.set(0); //This function is not needed but can be used if wanted
  }


  //Tank vs Arcade Mode Code
  public void switchMode() {
    driveTankMode = !driveTankMode;
  }
  public boolean getTankMode() {
    return driveTankMode;
  }

  //Extended or not?
  public void changeExtendedState(){
    driveExtended = !driveExtended;
  }
  public boolean getExtendState() {
    return driveExtended;
  }

  public void frontExtended(boolean frontExtendedState){
    frontExtended = frontExtendedState;
  }
  // public void frontExtended(){
  //   frontExtended = !frontExtended;
  // }
  public boolean isFrontExtended() {
    return frontExtended;
  }
  public void backExtended(boolean backExtendedState){
    backExtended = backExtendedState;
  }
  public boolean isBackExtended(){
    return backExtended;
  }



  public void changeSpeed() {
    // if(driveSpeed == 0.5){
    //   driveSpeed = 1.0;
    // }else{
    //   driveSpeed = 0.5;
    // }
  }

  public void fastSpeed(boolean fast){
    if (fast == true){
      driveSpeed = 1.0;
    } else {
      driveSpeed = 0.89;
    }
  }



}
