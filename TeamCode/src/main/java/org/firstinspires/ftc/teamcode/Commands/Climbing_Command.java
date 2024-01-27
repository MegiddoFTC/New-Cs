package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.climb.ClimbIsOpen;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Climbing_Subsystem;

public class Climbing_Command extends CommandBase {

    private final Climbing_Subsystem ClimbingSubsystem;

    public Climbing_Command(Climbing_Subsystem subsystem){
        this.ClimbingSubsystem = subsystem;
        addRequirements(ClimbingSubsystem);
    }

    @Override
    public void initialize(){
        if (!ClimbIsOpen) {
            ClimbingSubsystem.Open();
            ClimbIsOpen = true;
        }
        else {
            ClimbingSubsystem.Retract();
            ClimbIsOpen = false;
        }

    }

    @Override
    public boolean isFinished(){
        return true;
    }


}
