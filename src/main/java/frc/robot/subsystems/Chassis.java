/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.commands.DriveChassis;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
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
    leftFront = new CANSparkMax(0, MotorType.kBrushless);
    leftMiddle = new CANSparkMax(1, MotorType.kBrushless);
    leftBack = new CANSparkMax(2, MotorType.kBrushless);
    final SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftFront, leftMiddle, leftBack);

    rightFront = new CANSparkMax(10, MotorType.kBrushless);
    rightMiddle = new CANSparkMax(11, MotorType.kBrushless);
    rightBack = new CANSparkMax(12, MotorType.kBrushless);
    final SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightFront, rightMiddle, rightBack);

    diffDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public void driveByJoystick() {
    double moveSpeed = -RobotContainer.driverJoystick.getRawAxis(1); //Left Y
    double turnSpeed = RobotContainer.driverJoystick.getRawAxis(4); //Right X
    boolean squaredInputs = true;

    this.driveArcade(moveSpeed, turnSpeed, squaredInputs);
    // this.driveTank(moveSpeed, turnSpeed, squaredInputs);
  }
  public void driveArcade(double moveSpeed, double turnSpeed, boolean squaredInputs) {
    this.diffDrive.arcadeDrive(moveSpeed, turnSpeed, squaredInputs);
  }

  @Override
  public void periodic() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveChassis());
  }
}
