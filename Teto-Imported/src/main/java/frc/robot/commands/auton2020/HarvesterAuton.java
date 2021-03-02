/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton2020;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Harvester;

public class HarvesterAuton extends CommandBase {
  /**
   * Creates a new HarvesterAuton.
   */
  public HarvesterAuton(Harvester harvester) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(harvester);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Harvester.harvesterArm.set(DoubleSolenoid.Value.kForward);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Harvester.harvesterMotor.set(0.5);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if( Harvester.harvesterArm.get() == DoubleSolenoid.Value.kForward){
      return true;
    }
    return false;
  }
}
