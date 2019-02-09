/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  public Joystick joy = new Joystick(0);
  
  public JoystickButton x = new JoystickButton(joy, 1);
  public JoystickButton a = new JoystickButton(joy, 2);
  public JoystickButton b = new JoystickButton(joy, 3);
  public JoystickButton y = new JoystickButton(joy, 4);
  public JoystickButton lb = new JoystickButton(joy, 5);
  public JoystickButton rb = new JoystickButton(joy, 6);
  public JoystickButton lt = new JoystickButton(joy, 7);
  public JoystickButton rt = new JoystickButton(joy, 8);
  public JoystickButton back = new JoystickButton(joy, 9);
  public JoystickButton start = new JoystickButton(joy, 10);

  public OI(){
    a.whileHeld(new PushHatch());
    start.whenPressed(new ChangeDriveMode());
    back.whenPressed(new ChangeExtendedState());
    // lb.whileHeld(new DriveExtendedWithJoysticks());
    // rb.whileHeld(new DriveExtendedWithJoysticks());

    lb.whenPressed(new FrontPushUp());
    rb.whenPressed(new BackPushUp());
    lt.whenPressed(new FrontRetract());
    rt.whenPressed(new BackRetract());

    // y.whenPressed(new AllPushUp());
    // x.whenPressed(new AllRetract());
    y.whenPressed(new TogglePushUp());

    //rt.whenPressed(new AllPushUp());

  }
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
