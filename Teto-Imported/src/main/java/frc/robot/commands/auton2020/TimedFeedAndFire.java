/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton2020;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TimedFeedAndFire extends ParallelDeadlineGroup {
  /**
   * Creates a new TimedFeedAndFire.
   */
  public TimedFeedAndFire() {
    // Add your commands in the super() call.  Add the deadline first.
    super(
      new timeRegulator(), new ConveyorAuton(RobotContainer.m_conveyor),
      new ShooterAuton(RobotContainer.m_shooter));
  }
}
