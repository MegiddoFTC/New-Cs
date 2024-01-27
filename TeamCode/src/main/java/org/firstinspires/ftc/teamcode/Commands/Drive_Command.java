package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;

public class Drive_Command extends CommandBase {
    private final Drive_Subsystem driveSubsystem;

    public Drive_Command(Drive_Subsystem subsystem){
        this.driveSubsystem = subsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.drive();
    }
}
