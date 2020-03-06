/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class ConveyorAuton extends CommandBase {
  public double time = 6.25;
  public Timer timer = new Timer();
  /**
   * Creates a new ConveyorAuton.
   */
  public ConveyorAuton(Conveyor conveyor) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
   //
      Conveyor.frontConveyor.set(1);
      Conveyor.backConveyor.set(0.9);
     // }
     /* else{
     Conveyor.frontConveyor.set(0);
     Conveyor.backConveyor.set(0);
      }*/
    }
public boolean withinTime(){
  if(timer.get() < time){
    return true;
  }
  else{
    return false;
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
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() >= time){
      return true;
    }
    return false;
  }
}
