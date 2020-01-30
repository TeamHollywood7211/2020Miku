/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Conveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */
  public CANSparkMax frontConveyor;
  public CANSparkMax backConveyor;

  public Conveyor() {
    frontConveyor = new CANSparkMax(30, MotorType.kBrushless);
    backConveyor = new CANSparkMax(31, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
