/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Conveyor;

public class RunConveyor extends CommandBase {
  /**
   * Creates a new RunConveyor.
   */
  private boolean conveyorClear = true;
  private int speed = 1;
  public RunConveyor(Conveyor conveyor) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.operatorJoystick.getRawAxis(2) >= 0.5 && conveyorClear == true){
    RobotContainer.m_conveyor.frontConveyor.set(speed);
    RobotContainer.m_conveyor.backConveyor.set(speed);
    }
    else{
      RobotContainer.m_conveyor.frontConveyor.set(0);
      RobotContainer.m_conveyor.backConveyor.set(0);
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
