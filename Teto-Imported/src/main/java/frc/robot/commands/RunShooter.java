/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase {
  /**
   * Creates a new RunShooter.
   */
  private boolean cellReady = true;

  public RunShooter(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //check the operator controller to see if we want to shoot it. If we do, fire.
    if (RobotContainer.operatorJoystick.getRawAxis(3) >= 0.5 && cellReady == true){
      Shooter.shootingFrontMotor.set(0.89);
      Shooter.shootingBackMotor.set(-0.89);
      }
    else{
      Shooter.shootingFrontMotor.set(0);
      Shooter.shootingBackMotor.set(0);
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
