/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.TurnTurret;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Turret extends SubsystemBase {
  /**
   * Creates a new Turret.
   */
  public CANSparkMax turretMotor;

  public Turret() {
    turretMotor = new CANSparkMax(50, MotorType.kBrushless);
  }

  public void DriveTurret(){

    if(RobotContainer.operatorJoystick.getRawAxis(4) > 0.25){
      turretMotor.set(-1);
    }
    else if (RobotContainer.operatorJoystick.getRawAxis(4) < -0.25){
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
