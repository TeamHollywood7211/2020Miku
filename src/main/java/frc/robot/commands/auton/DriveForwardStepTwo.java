/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class DriveForwardStepTwo extends CommandBase {
  /**
   * Creates a new DriveForward.
   */
  private final double targetDistance = 74;
  public DriveForwardStepTwo(Chassis chassis) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    while(Chassis.rightEncoder.getPosition() < targetDistance){
      Chassis.diffDrive.arcadeDrive(-0.35, 0);
      }
      Chassis.diffDrive.arcadeDrive(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*if(Chassis.rightEncoder.getPosition() < target Distance){
      Chassis.diffDrive.arcadeDrive(1, 0);
    }
    else{
      Chassis.diffDrive.arcadeDrive(0, 0);
    }*/
    System.out.println("Drive Encoder: " + Chassis.rightEncoder.getPosition());
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Chassis.rightEncoder.getPosition() >= targetDistance){
      return true;
    }
    return false;
  }
}
