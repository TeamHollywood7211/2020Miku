/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class RunClimber extends CommandBase {
  /**
   * Creates a new RunClimber.
   */
  private static double encoderLimit = -435;

  public RunClimber(Climber climber){
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Climber.climberEncoder.setPosition(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if (checkArmRange(Climber.climberEncoder) == false){
        // Warn the drive team that the arms is over the set points
        RobotContainer.driverJoystick.setRumble(RumbleType.kLeftRumble, 1);
        RobotContainer.driverJoystick.setRumble(RumbleType.kRightRumble, 1);

        RobotContainer.operatorJoystick.setRumble(RumbleType.kLeftRumble, 1);
        RobotContainer.operatorJoystick.setRumble(RumbleType.kRightRumble, 1);
    } else {
      RobotContainer.driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
      RobotContainer.driverJoystick.setRumble(RumbleType.kRightRumble, 0);

      RobotContainer.operatorJoystick.setRumble(RumbleType.kLeftRumble, 0);
      RobotContainer.operatorJoystick.setRumble(RumbleType.kRightRumble, 0);
    }

    //Lower Arms
    if (RobotContainer.driverJoystick.getPOV() == 180) {
      Climber.climberMaster.set(1);
    }
    //Raise Arms
    else if (RobotContainer.driverJoystick.getPOV() == 0) { 
      Climber.climberMaster.set(-1);
    }
    if(RobotContainer.driverJoystick.getPOV() == -1 || (Climber.climberEncoder.getPosition() <= encoderLimit && Climber.climberMaster.get() < 0)){
      Climber.climberMaster.set(0);
    }

    System.out.println("Climber Arms: " + Climber.climberEncoder.getPosition());
    if(RobotContainer.driverJoystick.getPOV() == 270){
      Climber.climberMaster.set(0.5);
    }
  }
  static boolean inRange;

  private static boolean checkArmRange(CANEncoder encoder) {
    if (encoder.getPosition() >= encoderLimit) {
      inRange = true;
    }
    else{
      inRange = false;
    }

    return inRange;
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
