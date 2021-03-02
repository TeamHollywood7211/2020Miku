/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveChassis;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.*;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
/**
 * Add your docs here.
 */
public class Chassis extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static BuiltInAccelerometer accel;

  private static CANSparkMax leftFront;
  private static CANSparkMax leftMiddle;
  private static CANSparkMax leftBack;
  public static CANEncoder leftEncoder;

  private static CANSparkMax rightFront;
  private static CANSparkMax rightMiddle;
  private static CANSparkMax rightBack;
  public static CANEncoder rightEncoder;

  public static CANSparkMax verticalMotor;
  public static CANEncoder verticalEncoder;

  public static DifferentialDrive diffDrive;
  public static AHRS ahrs;

  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry tx = table.getEntry("tx");
  public static NetworkTableEntry ty = table.getEntry("ty");
  public static NetworkTableEntry tv = table.getEntry("tv");

  public static double rampRate = 1.15;

  public Chassis() {
    accel = new BuiltInAccelerometer();

    // Define the left side of the robot and group the motors together.
    leftFront = new CANSparkMax(1, MotorType.kBrushless);
    leftMiddle = new CANSparkMax(2, MotorType.kBrushless);
    leftBack = new CANSparkMax(3, MotorType.kBrushless);
    leftEncoder = new CANEncoder(leftMiddle);

    ahrs = new AHRS(SPI.Port.kMXP);

    final SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftFront, leftMiddle, leftBack);

    // Define the right side of the robot and group the motors together.
    rightFront = new CANSparkMax(10, MotorType.kBrushless);
    rightMiddle = new CANSparkMax(11, MotorType.kBrushless);
    rightBack = new CANSparkMax(12, MotorType.kBrushless);
    rightEncoder = new CANEncoder(rightMiddle);
    

    final SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightFront, rightMiddle, rightBack);

    // Group the SpeedControllerGroups into one Differential Drive system.
    diffDrive = new DifferentialDrive(leftMotors, rightMotors);

    verticalMotor = new CANSparkMax(50, MotorType.kBrushless);
    verticalEncoder = new CANEncoder(verticalMotor);
  }

  public static void DriveByJoystick() {

    // Increase the values at a increasing rate.
    boolean squaredInputs = true;

    if(RobotContainer.operatorJoystick.getRawButton(1) == true){
      driveTank(horizontalAuto(), -horizontalAuto(), false);
      verticalMotor.set(verticalAuto());
    }
    else{
      // use the actual FRC provided method with easier access in the defined method.
      driveArcade(antiTip(returnLeftAxis(1)), -returnRightAxis(4), squaredInputs);
      // this.driveTank(returnLeftAxis(1), returnRightAxis(5), squaredInputs);
    }
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
  static boolean highVelocity;
  static int timer;
  public static double antiTip(double moveSpeed){

    
    if(leftEncoder.getVelocity() > 3500){
      highVelocity = true;
      timer = 0;
    }
    if(leftEncoder.getVelocity() < 0){
      highVelocity = false;
    }
    if(highVelocity){
      if(moveSpeed <= 0.3){
        if(timer < 10){
          moveSpeed = 0.5;
          timer++;
        }
        if(timer > 10 && getRoll() <= 11){
          highVelocity = false;
        }
      }
    }
    return moveSpeed;
  }

  public static double getRoll(){
    double X = accel.getX();
    double Y = accel.getY();
    double Z = accel.getZ();
    return Math.atan2(-X, Math.sqrt(Y*Y + Z*Z)) * 180/Math.PI;
    }

    public static double horizontalAuto(){
      double Kp = 0.0075; // Proportional control constant
      double x = tx.getDouble(0.0);
      double v = tv.getDouble(1);
      double horizontalError = -x;
      double chassisAdjust = 0;

      // If A is held down, run a PID loop to center the turret.
      if(v == 1){
        if (x > 0.5) {
          chassisAdjust = Kp * horizontalError;
        } 
        else if (x < 0.5) {
          chassisAdjust = Kp * horizontalError;
        }
      }
      return chassisAdjust;
    }

    public static double verticalAuto(){
      double Kp = 0.02; // Proportional control constant
      double y = ty.getDouble(0.0);
      double v = tv.getDouble(1);
      double verticalError = -y;
      double angleAdjust = 0;

      if(v == 1){
        if (y > 0.5) {
          angleAdjust = Kp * verticalError;
        } 
        else if (y < 0.5) {
          angleAdjust = Kp * verticalError;
        }
      }
      return angleAdjust;
    }
  }


