/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton2020;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class timeRegulator extends CommandBase {
  /**
   * Creates a new timeRegulator.
   */
  public Timer time;
  public timeRegulator() {
    // Use addRequirements() here to declare subsystem dependencies.
    time = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    time.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get() >= 3.5){
      return true;
    }
    return false;
  }
}
