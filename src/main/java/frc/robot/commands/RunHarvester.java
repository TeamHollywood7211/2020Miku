/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Harvester;

public class RunHarvester extends CommandBase {
  /**
   * Creates a new RunHarvester.
   */


  public RunHarvester(Harvester harvester) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(harvester);
  }

  boolean armExtended;
  public boolean returnValue(){
    if(RobotContainer.m_harvester.harvesterArm.get() == DoubleSolenoid.Value.kForward){
      armExtended = true;
    }
    else if(RobotContainer.m_harvester.harvesterArm.get() == DoubleSolenoid.Value.kReverse || RobotContainer.m_harvester.harvesterArm.get() == DoubleSolenoid.Value.kOff){
    armExtended = false;
    }
  return armExtended;
}
  public void harvesterArm(boolean extend){

    if (extend == true){
      RobotContainer.m_harvester.harvesterArm.set(DoubleSolenoid.Value.kForward);
    }
    else if(extend == false){
      RobotContainer.m_harvester.harvesterArm.set(DoubleSolenoid.Value.kReverse);
    }
  }
  int motorPower;
  public void harvesterMotor(){
    //Check if the arm is extended and turn on the motors if it is.
    if(RobotContainer.operatorJoystick.getRawButton(6)){
      motorPower = 1;
    }
    else{
      motorPower = 0;
    }
    
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Tell the code the harvester is extending its arm and then extend it.
    if(RobotContainer.operatorJoystick.getRawButton(8)){
      if(returnValue() == false){
    harvesterArm(true);
      }
      else if(returnValue() == true){
        harvesterArm(false);
      }
    }
    harvesterMotor();
    RobotContainer.m_harvester.harvesterMotor.set(motorPower);
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
