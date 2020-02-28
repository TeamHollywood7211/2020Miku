/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton;

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
    harvesterArm(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Harvester.harvesterMotor.set(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Harvester.harvesterMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void harvesterArm(boolean extend){
    //A toggle to check if the pneumatics is extended or not to reuse the same button.
    if (extend == true){
      Harvester.harvesterArm.set(DoubleSolenoid.Value.kForward);
    }
    else if(extend == false){
      Harvester.harvesterArm.set(DoubleSolenoid.Value.kReverse);
    }
  }
}
