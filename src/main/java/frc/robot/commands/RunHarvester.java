/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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
    //Tell us whether or not the arm is extended.
    if(Harvester.harvesterArm.get() == DoubleSolenoid.Value.kForward){
      armExtended = true;
    }
    else if(Harvester.harvesterArm.get() == DoubleSolenoid.Value.kReverse || Harvester.harvesterArm.get() == DoubleSolenoid.Value.kOff){
      armExtended = false;
    }
  return armExtended;
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
  
  double motorPower;
  public void harvesterMotor(){
    //Check if the arm is extended and turn on the motors if it is.
    if(RobotContainer.operatorJoystick.getRawButton(6)){
      motorPower = 0.8;
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
      new WaitCommand(1);
      if(returnValue() == false){
    harvesterArm(true);
      }
      else if(returnValue() == true){
        new WaitCommand(1);
        harvesterArm(false);
      }
    }
    harvesterMotor();
    Harvester.harvesterMotor.set(motorPower);
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
