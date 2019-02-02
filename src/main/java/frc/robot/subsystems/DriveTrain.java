/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.*;
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
  private static WPI_TalonSRX middleLeftMotor = new WPI_TalonSRX(RobotMap.middleLeftMotor);
  private static WPI_TalonSRX middleRightMotor = new WPI_TalonSRX(RobotMap.middleRightMotor);
  private static WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(RobotMap.backLeftMotor);
  private static WPI_TalonSRX backRightMotor = new WPI_TalonSRX(RobotMap.backRightMotor);

  private static SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeftMotor, middleLeftMotor, backLeftMotor);
  private static SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRightMotor, middleRightMotor, backRightMotor);

  private static DifferentialDrive differentialDriveTrain = new DifferentialDrive(leftMotors, rightMotors);

  private static WPI_TalonSRX extendedMotor = new WPI_TalonSRX(RobotMap.extendedMotor);//Needs to set CAN ID

  private boolean driveTankMode = true;
  private boolean extended = false;

  //Pneumatics will be used in the drivetrain. The following is where that is:
  private static DoubleSolenoid firstNoid = new DoubleSolenoid(0,1);//Rename these
  private static DoubleSolenoid secondNoid = new DoubleSolenoid(4,5);
  private static DoubleSolenoid thirdNoid;
  private static DoubleSolenoid fourthNoid;//Not sure how many we need


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveWithJoysticks());
  }

  public void driveTank(double left, double right) {
    System.out.println("drive(double left, double right) reached");
    differentialDriveTrain.tankDrive(left, right);
    // differentialDriveTrain.arcadeDrive(speed, rotation);
  }
  public void driveTank(Joystick joy){ 
    System.out.println(-joy.getY() + " " + -joy.getThrottle());
    driveTank(-joy.getY(), -joy.getThrottle());
  }


  public void driveArcade(double speed, double rotation) {
    differentialDriveTrain.arcadeDrive(speed, rotation);
  }
  public void driveArcade(Joystick joy) {
    driveArcade(-joy.getY(), joy.getZ());
  }

  //Pneumatic pushup code
  public void pushUpFront () { 
    //pneumatics pushes robot up to climb
    firstNoid.set(DoubleSolenoid.Value.kForward);
  }
  public void pushUpBack() {
    secondNoid.set(DoubleSolenoid.Value.kForward);
  }
  public void retractDownFront() {
    //pneumatics retracts robot
    firstNoid.set(DoubleSolenoid.Value.kReverse);
  }
  public void retractDownBack() {
    secondNoid.set(DoubleSolenoid.Value.kReverse);
  }
  public void extendState() {
    extended = true;
  }
  public void retractState() {
    extended =false;
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

}
