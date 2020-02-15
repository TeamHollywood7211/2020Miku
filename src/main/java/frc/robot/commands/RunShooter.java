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
    if (RobotContainer.operatorJoystick.getRawAxis(3) >= 0.5 && cellReady == true){
      RobotContainer.m_shooter.shootingFrontMotor.set(1);
      RobotContainer.m_shooter.shootingBackMotor.set(-1);
      }
      else{
        RobotContainer.m_shooter.shootingFrontMotor.set(0);
        RobotContainer.m_shooter.shootingBackMotor.set(0);
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
