/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.RunShooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  public CANSparkMax shootingFrontMotor;
  public CANSparkMax shootingBackMotor;

  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry ty = table.getEntry("tx");
  //work in progress variables for distance
  public static double y = ty.getDouble(0.0);
  public static double difference = 41.9375;

  public Shooter() {
    shootingFrontMotor = new CANSparkMax(40, MotorType.kBrushless);
    shootingBackMotor = new CANSparkMax(41, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new RunShooter(RobotContainer.m_shooter));
  }
}
