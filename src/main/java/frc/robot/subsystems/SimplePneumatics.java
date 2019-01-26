/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.CloseSimplePneumatic;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Add your docs here.
 */
public class SimplePneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid simpleDoubleSol = new DoubleSolenoid(2, 3);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CloseSimplePneumatic());
  }

  public void openSimpleSolenoid(){
    simpleDoubleSol.set(DoubleSolenoid.Value.kForward);
  }

  public void closeSimpleSolenoid(){
    simpleDoubleSol.set(DoubleSolenoid.Value.kReverse);
  }
 
}
