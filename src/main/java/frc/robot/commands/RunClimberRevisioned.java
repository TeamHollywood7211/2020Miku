/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.TrapezoidProfileCommand;
import frc.robot.subsystems.Chassis;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class RunClimberRevisioned extends TrapezoidProfileCommand {
  /**
   * Creates a new RunClimberRevisioned.
   */
  public RunClimberRevisioned(double meters, Chassis Chassis) {
    super(
        // The motion profile to be executed
        new TrapezoidProfile(
            // The motion profile constraints
            new TrapezoidProfile.Constraints(3, 3),
            // Goal state
            new TrapezoidProfile.State(meters, 0),
            // Initial state
            new TrapezoidProfile.State()),
        state -> {
          // Use current trajectory state here
        });
  }
}
