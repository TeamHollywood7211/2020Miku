/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Conveyor;

public class ConveyorAuton extends CommandBase {
  /**
   * Creates a new ConveyorAuton.
   */
  public final double desiredTime = 3;
  public Timer time;
  public ConveyorAuton(Conveyor conveyor) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = new Timer();
    time.start();
    while(time.get() < desiredTime){
    Conveyor.frontConveyor.set(0.9);
    Conveyor.backConveyor.set(0.8);
    }
    
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  /*Conveyor.frontConveyor.set(0.8);
  Conveyor.backConveyor.set(0.7);*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Conveyor.frontConveyor.set(0);
    Conveyor.backConveyor.set(0);
    time.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get() >= desiredTime){
      return true;
    }
    return false;
  }
}
