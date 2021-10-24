// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ExampleAutoCommand;
import frc.robot.commands.vision.TrackTargetCommand;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();

  private final ExampleAutoCommand autoCommand = new ExampleAutoCommand(swerveSubsystem);

  private final VisionSubsystem visionSubsystem = new VisionSubsystem();
  // private TrackTargetCommand trackTargetCommand = new
  // TrackTargetCommand(visionSubsystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    visionSubsystem.setDefaultCommand(new TrackTargetCommand(visionSubsystem));

    SmartDashboard.putData("Track Target Command", new TrackTargetCommand(visionSubsystem));
    // Configure the button bindings
    configureButtonBindings();

    // Set the scheduler to log Shuffleboard events for command initialize,
    // interrupt, finish
    CommandScheduler.getInstance().onCommandInitialize(
        command -> Shuffleboard.addEventMarker("Command initialized", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance().onCommandInterrupt(
        command -> Shuffleboard.addEventMarker("Command interrupted", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance().onCommandFinish(
        command -> Shuffleboard.addEventMarker("Command finished", command.getName(), EventImportance.kNormal));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
}
