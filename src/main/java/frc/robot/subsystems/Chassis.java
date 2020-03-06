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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
/**
 * Add your docs here.
 */
public class Chassis extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static CANSparkMax leftFront;
  private static CANSparkMax leftMiddle;
  private static CANSparkMax leftBack;
  public static CANEncoder leftEncoder;

  private static CANSparkMax rightFront;
  private static CANSparkMax rightMiddle;
  private static CANSparkMax rightBack;
  public static CANEncoder rightEncoder;

  public static DifferentialDrive diffDrive;

  public static double rampRate = 1.15;

  public Chassis() {
    // Define the left side of the robot and group the motors together.
    leftFront = new CANSparkMax(1, MotorType.kBrushless);
    leftMiddle = new CANSparkMax(2, MotorType.kBrushless);
    leftBack = new CANSparkMax(3, MotorType.kBrushless);
    leftEncoder = new CANEncoder(leftMiddle);

    final SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftFront, leftMiddle, leftBack);

    // Define the right side of the robot and group the motors together.
    rightFront = new CANSparkMax(10, MotorType.kBrushless);
    rightMiddle = new CANSparkMax(11, MotorType.kBrushless);
    rightBack = new CANSparkMax(12, MotorType.kBrushless);
    rightEncoder = new CANEncoder(rightMiddle);
    

    final SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightFront, rightMiddle, rightBack);

    // Group the SpeedControllerGroups into one Differential Drive system.
    diffDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public static void DriveByJoystick() {

    // Increase the values at a increasing rate.
    boolean squaredInputs = true;

    // use the actual FRC provided method with easier access in the defined method.
    driveArcade(returnLeftAxis(1), -returnRightAxis(4), squaredInputs);
    // this.driveTank(returnLeftAxis(1), returnRightAxis(5), squaredInputs);

    configureChassis(true, true);
  }

  public static void driveArcade(double moveSpeed, double turnSpeed, boolean squaredInputs) {
    diffDrive.arcadeDrive(moveSpeed, turnSpeed, squaredInputs);
  }

  public static void driveTank(double leftMotors, double rightMotors, boolean squaredInputs) {
    diffDrive.tankDrive(leftMotors, rightMotors, squaredInputs);
  }

  @Override
  public void periodic() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveChassis(RobotContainer.m_chassis));
    System.out.println("Drive Encoder: " + rightEncoder.getPosition());

  }

  public static void configureChassis(boolean invertedLeft, boolean invertedRight) {
    /*
     * Ramp up the speed of every speed controller on the chassis. Also sets
     * inversion of left and right side of the chassis motors.
     */
    leftFront.setOpenLoopRampRate(rampRate);
    leftFront.setInverted(invertedLeft);

    leftMiddle.setOpenLoopRampRate(rampRate);
    leftMiddle.setInverted(invertedLeft);

    leftBack.setOpenLoopRampRate(rampRate);
    leftBack.setInverted(invertedLeft);

    rightFront.setOpenLoopRampRate(rampRate);
    rightFront.setInverted(invertedRight);

    rightMiddle.setOpenLoopRampRate(rampRate);
    rightMiddle.setInverted(invertedRight);

    rightBack.setOpenLoopRampRate(rampRate);
    rightBack.setInverted(invertedRight);
    SmartDashboard.putNumber("Ramp Rate", rightBack.getOpenLoopRampRate());

  }

  public static double returnLeftAxis(int leftAxis) {
    //Returns axis easier without much more to type out
    double leftStick = RobotContainer.driverJoystick.getRawAxis(leftAxis);
    return leftStick;
  }
  public static double returnRightAxis(int rightAxis){
    //returns axis easier without much more to type out
    double rightStick = RobotContainer.driverJoystick.getRawAxis(rightAxis);
    return rightStick;
  }

}
