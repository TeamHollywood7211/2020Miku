/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.commands.*;
import frc.robot.commands.auton.*;
import frc.robot.subsystems.Chassis;

import static java.util.Map.entry;

import java.util.Map;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you
 *  must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  //Auton Commands
  private Command m_autonSuccession;
  //private Command m_threeBallSuccession = new ThreeBallSuccession();
  //private Command m_sixBallSuccession = new SixBallSuccession();
  //private Command m_seekAndCenter = new SeekAndCenter(RobotContainer.m_turret);

  //Teleop Commands
  private Command m_runConveyor = new RunConveyor(RobotContainer.m_conveyor);
  private Command m_runShooter = new RunShooter(RobotContainer.m_shooter);
  private Command m_turnTurret = new TurnTurret(RobotContainer.m_turret);
  private Command m_runHarvester = new RunHarvester(RobotContainer.m_harvester);
  private Command m_climber = new RunClimber(RobotContainer.m_climber);

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    new RobotContainer();
    CameraServer.getInstance().startAutomaticCapture("Conveyor", 0);
    CameraServer.getInstance().startAutomaticCapture("Front Facing Camera", 1);

    if(select() == CommandSelector.THREE){
      new PrintCommand("Three Ball Auton selected");
    }
    if(select() == CommandSelector.SIX){  
      new PrintCommand("Six Ball Auton selected");
    }
  
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    select();
    
    CommandScheduler.getInstance().run();
    System.out.println("Drive Encoder: " + Chassis.rightEncoder.getPosition());

    
    if(select() == CommandSelector.THREE){
      new PrintCommand("Three Ball Auton selected");
    }
    if(select() == CommandSelector.SIX){  
      new PrintCommand("Six Ball Auton selected");
    }
  
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    // schedule the autonomous command (example)
    m_autonSuccession = getAutonomousCommand();

    if(m_autonSuccession != null){
      m_autonSuccession.schedule();
    }
      
   // m_seekAndCenter.schedule();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    System.out.println("Drive Encoder: " + Chassis.rightEncoder.getPosition());
  }
  

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
   // m_seekAndCenter.cancel();
    if(m_autonSuccession != null){
      m_autonSuccession.cancel();
    }

    //Run the robot conveyor
    m_runConveyor.schedule();

    //Run the shooting mechanism
    m_runShooter.schedule();
  
    //Turn the top turret
    m_turnTurret.schedule();

    m_runHarvester.schedule();

    m_climber.schedule();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    //Post the values retrieved from the limelight.
    //RobotContainer.m_turret.returnCameraValues();

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private enum CommandSelector {
    THREE, SIX
  }

  // An example selector method for the selectcommand.  Returns the selector that will select
  // which command to run.  Can base this choice on logical conditions evaluated at runtime.
  public CommandSelector select() {
    if(RobotContainer.driverJoystick.getRawButton(8)){
      return CommandSelector.THREE;
    }
    if(RobotContainer.driverJoystick.getRawButton(7)){
      return CommandSelector.SIX;
    }
    return null;
  }

  // An example selectcommand.  Will select from the three commands based on the value returned
  // by the selector method at runtime.  Note that selectcommand works on Object(), so the
  // selector does not have to be an enum; it could be any desired type (string, integer,
  // boolean, double...)
  private final Command selectCommand =
      new SelectCommand(
          // Maps selector values to commands
          Map.ofEntries(
              entry(CommandSelector.THREE, new ThreeBallSuccession()),
              entry(CommandSelector.SIX, new SixBallSuccession())),
          this::select);
      public Command getAutonomousCommand() {
        return selectCommand;
      }
}
