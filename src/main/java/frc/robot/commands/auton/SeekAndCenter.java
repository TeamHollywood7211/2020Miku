/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class SeekAndCenter extends CommandBase {
  /**
   * Creates a new SeekAndCenter.
   */

  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry tx = table.getEntry("tx");
  public static NetworkTableEntry tv = table.getEntry("tv");

  double x = tx.getDouble(0.0);
  double v = tv.getDouble(0);

  public SeekAndCenter(Chassis chassis) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = tx.getDouble(0.0);
    double v = tv.getDouble(0);
    double Kp = 0.1; // Proportional control constant
      double headingError = -x;
      double adjust = 0;
    if(v == 1){
      if (x > 0.5 || x < 0.5) {
        adjust = Kp * headingError;
      } 
      Chassis.driveTank(adjust, adjust, false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Chassis.driveTank(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if((x > 0.45 || x < 55) || v == 0){
      return true;
    }
    return false;
  }

  
}
