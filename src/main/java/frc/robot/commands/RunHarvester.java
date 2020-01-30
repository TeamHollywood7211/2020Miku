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
    //addRequirements(RobotContainer.m_harvester);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Tell the code the harvester is extending its arm and then extend it.
    armExtended = true;
    RobotContainer.m_harvester.harvesterArm.set(armExtended);
    harvesterMotor();

  }
  public void harvesterMotor(){
    //Check if the arm is extended and turn on the motors if it is.
    if (armExtended == true){
      RobotContainer.m_harvester.harvesterMotor.set(1);
    }
    else {
      RobotContainer.m_harvester.harvesterMotor.set(0);
    }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armExtended = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
