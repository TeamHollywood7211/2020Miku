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
import frc.robot.subsystems.Shooter;

public class RunConveyor extends CommandBase {
  /**
   * Creates a new RunConveyor.
   */
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
    //check driver for the down button on d-pad to move it in reverse.
    if(RobotContainer.reverseConveyorButton.get()){
      Conveyor.frontConveyor.set(speed);
      //Conveyor.backConveyor.set(-speed * 0.9);
    }
    //Make sure our motor is over good enough RPM to get a proper shot off before allowing us to take a shot.
    else if (RobotContainer.operatorJoystick.getRawAxis(2) >= 0.5 && RobotContainer.operatorJoystick.getRawAxis(3) >= 0.5){
      if(Shooter.shooterEncoder.getVelocity() >= 5200){
        Conveyor.frontConveyor.set(speed);
        //Conveyor.backConveyor.set(speed * 0.9);
      }
      else{
        Conveyor.frontConveyor.set(0);
        //Conveyor.backConveyor.set(0);
      }
    }
    //check operator left trigger to move conveyor forward if the driver isn't trying to unjam it and they aren't trying to feed it into shooter either.
    else if (RobotContainer.operatorJoystick.getRawAxis(2) >= 0.5){
      Conveyor.frontConveyor.set(speed);
      //Conveyor.backConveyor.set(speed * 0.9);
    }
    //otherwise do not move the conveyor at all.
    else{
      Conveyor.frontConveyor.set(0);
      //Conveyor.backConveyor.set(0);
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
