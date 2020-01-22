/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class RunHarvester extends CommandBase {
  /**
   * Creates a new RunHarvester.
   */

  public boolean armExtended = false;

  public RunHarvester() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_harvester);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_harvester.harvesterArm.set(true);
    armExtended = true;
    harvesterMotor();

  }
  public void harvesterMotor(){
    if (armExtended == true){
      RobotContainer.m_harvester.harvesterMotor.set(1.0);
    }
    else {
      RobotContainer.m_harvester.harvesterMotor.set(0);
    }
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
