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
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  public static CANSparkMax shootingFrontMotor;
  public static CANSparkMax shootingBackMotor;

  public static CANEncoder shooterEncoder;
  static double deviser;

  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry ty = table.getEntry("ty");
  public static NetworkTableEntry tv = table.getEntry("tv");

  //work in progress variables for distance//
  //public static double a1 = 83.9875579;
  //public static double aSum = a1 + a2;

  public static double difference = 41.9375;

  public Shooter() {
    shootingFrontMotor = new CANSparkMax(40, MotorType.kBrushless);
    shootingBackMotor = new CANSparkMax(41, MotorType.kBrushless);
    shooterEncoder = new CANEncoder(shootingFrontMotor);
  }
  @Override
  
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new RunShooter(RobotContainer.m_shooter));

    //calculateDistance();
    //System.out.println("Distance: " + calculateDistance());
  }

 /* public double calculateDistance(){
    double distance;
    //If we can see the target, calulate the distance to the target.
    if(visibleTarget == 1){
    deviser = Math.tan(aSum);
    distance = difference / deviser;
    }
    else{
      distance = 0;
    }
    return distance;
  }*/
  public static double autoVertical(){
    double Kp = 0.02; // Proportional control constant
    double y = ty.getDouble(0.0);
    double v = tv.getDouble(1);
    double headingError = -y;
    double angleAdjust = 0;

    // If A is held down, run a PID loop to center the turret.
    if(v == 1){
      if (y > 0.5) {
        angleAdjust = Kp * headingError;
      } 
      else if (y < 0.5) {
        angleAdjust = Kp * headingError;
      }
    }
    return angleAdjust;
  }
}
