/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Harvester extends SubsystemBase {
  /**
   * Creates a new Harvester.
   */

  public DoubleSolenoid harvesterArm;
  public CANSparkMax harvesterMotor;

  public Harvester() {
    harvesterArm = new DoubleSolenoid(0,1);
    harvesterMotor = new CANSparkMax(20, MotorType.kBrushless);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
