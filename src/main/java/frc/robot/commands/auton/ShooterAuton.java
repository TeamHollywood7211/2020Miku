/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton;

//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterAuton extends CommandBase {
  /**
   * Creates a new ShooterAuton.
   */
  public ShooterAuton(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      Shooter.shootingFrontMotor.set(1);
      Shooter.shootingBackMotor.set(-1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*Shooter.shootingFrontMotor.set(1);
    Shooter.shootingBackMotor.set(-1);*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.shootingFrontMotor.set(0);
    Shooter.shootingBackMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
