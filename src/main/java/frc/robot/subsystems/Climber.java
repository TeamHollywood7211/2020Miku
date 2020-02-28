/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  public static CANSparkMax climberMaster;
  public static CANSparkMax climberFollower;
  public static CANEncoder climberEncoder;


  public Climber() {
  climberMaster = new CANSparkMax(60, MotorType.kBrushless);
  climberFollower = new CANSparkMax(61, MotorType.kBrushless);
  
  climberFollower.follow(climberMaster, true);

  climberEncoder = new CANEncoder(climberMaster);
  
}

public static void moveBothArms(double motorSpeed) {
  climberMaster.set(motorSpeed);
  }

  public static boolean checkPOVState(POVButton POVButton){
    boolean buttonPressed = false;
    
    if(POVButton.get() == true){
      buttonPressed = true;
    }
    return buttonPressed;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
