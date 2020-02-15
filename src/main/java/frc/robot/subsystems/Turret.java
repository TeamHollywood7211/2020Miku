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
import frc.robot.commands.TurnTurret;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Turret extends SubsystemBase {
  /**
   * Creates a new Turret.
   */

  public CANSparkMax turretMotor;
  public CANEncoder turretEncoder;
  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry tx = table.getEntry("tx");
  public static double x = tx.getDouble(0.0);

  public Turret() {
    turretMotor = new CANSparkMax(50, MotorType.kBrushless);
    turretEncoder = new CANEncoder(turretMotor);
  }

  public void DriveTurret(){
    double Kp = 0.1; // Proportional control constant
    double x = tx.getDouble(0.0);

    if (RobotContainer.operatorJoystick.getRawButton(1) == true) {
      double headingError = -x;
      double turretAdjust = 0;
      if (x > 0.5){
        turretAdjust = Kp*headingError;
        }
      else if (x < 0.5){
        turretAdjust = Kp*headingError;
        }     
      turretMotor.set(turretAdjust);
    }
    else if(RobotContainer.operatorJoystick.getRawAxis(4) > 0.1){
      turretMotor.set(-1);
    }
    else if (RobotContainer.operatorJoystick.getRawAxis(4) < -0.1){
      turretMotor.set(1);
    }
    else{
      turretMotor.set(0);
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new TurnTurret(RobotContainer.m_turret));
  }
}
