/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveChassis;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
/**
 * Add your docs here.
 */
public class Chassis extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax leftFront;
  private CANSparkMax leftMiddle;
  private CANSparkMax leftBack;

  private CANSparkMax rightFront;
  private CANSparkMax rightMiddle;
  private CANSparkMax rightBack;
  public DifferentialDrive diffDrive;

  public Chassis() {
    //Define the left side of the robot and group the motors together.
    leftFront = new CANSparkMax(1, MotorType.kBrushless);
    leftMiddle = new CANSparkMax(2, MotorType.kBrushless);
    leftBack = new CANSparkMax(3, MotorType.kBrushless);
    final SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftFront, leftMiddle, leftBack);

    //Define the right side of the robot and group the motors together.
    rightFront = new CANSparkMax(10, MotorType.kBrushless);
    rightMiddle = new CANSparkMax(11, MotorType.kBrushless);
    rightBack = new CANSparkMax(12, MotorType.kBrushless);
    final SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightFront, rightMiddle, rightBack);

    //Group the SpeedControllerGroups into one Differential Drive system.
    diffDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public void DriveByJoystick() {
    //Read the opposite value returned from the driver left y stick.
    double leftStick = -RobotContainer.driverJoystick.getRawAxis(1) * 0.5; //Left Y

    //read the value returned from the driver right x stick.
    double rightStick = -RobotContainer.driverJoystick.getRawAxis(5) * 0.5; //Right X

    //Increase the values at a increasing rate.
    boolean squaredInputs = true;

    //use the actual FRC provided method with easier access in the defined method.
    //this.driveArcade(moveSpeed, turnSpeed, squaredInputs);
    this.driveTank(leftStick, rightStick, squaredInputs);
  }
  public void driveArcade(double leftStick, double rightStick, boolean squaredInputs) {
    this.diffDrive.arcadeDrive(leftStick, rightStick, squaredInputs);
  }
  public void driveTank(double leftStick, double rightStick, boolean squaredInputs) {
    this.diffDrive.tankDrive(leftStick, rightStick, squaredInputs);
  }

  @Override
  public void periodic() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveChassis(RobotContainer.m_chassis));
  
  }
}
