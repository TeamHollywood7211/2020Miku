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
import frc.robot.subsystems.Turret;

public class SeekAndCenter extends CommandBase {
  /**
   * Creates a new SeekAndCenter.
   */

  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry tx = table.getEntry("tx");
  double x = tx.getDouble(0.0);

  public static NetworkTableEntry tv = table.getEntry("tv");
  boolean v = tv.getBoolean(false);
  public SeekAndCenter(Turret turret) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double Kp = 0.1; // Proportional control constant
    double x = tx.getDouble(0.0);
      double headingError = -x;
      double turretAdjust = 0;
      if(v == true){
        if (x > 0.5) {
          turretAdjust = Kp * headingError;
        } else if (x < 0.5) {
          turretAdjust = Kp * headingError;
        }
        Turret.turretMotor.set(turretAdjust);
      }
    }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    /*if(x >= 0.4 && x <= 0.6){
    return true;
  }*/
  return false;
}

  
}
