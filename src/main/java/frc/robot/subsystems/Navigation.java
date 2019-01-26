/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Add your docs here.
 */
public class Navigation extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private AHRS ahrs;

  public Navigation() {
    super();
    try {
      /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
      /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
      /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
      ahrs = new AHRS(SerialPort.Port.kUSB1);
      ahrs.reset();
    } catch (RuntimeException ex ) {
      System.out.print("Failed Gyro");
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void getAngle(){
    ahrs.getAngle();
  }

  public void getPitch(){
    ahrs.getPitch();  //Pitch is angle about the x-axis, i.e. how much tilt forward or backward
  }

  public void getRoll() {
    ahrs.getRoll();   //Roll is angle about the y-axis, i.e. how much tilt left or right, like it is rolling side to side
  }

}
