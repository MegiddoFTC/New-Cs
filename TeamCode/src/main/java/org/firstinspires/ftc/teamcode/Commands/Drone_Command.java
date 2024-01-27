package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.Drone_Subsystem;

public class Drone_Command extends CommandBase {
    private final Drone_Subsystem droneSubsystem;

    public Drone_Command(Drone_Subsystem subsystem){
        this.droneSubsystem = subsystem;
        addRequirements(droneSubsystem);
    }

    @Override
    public void initialize(){
        droneSubsystem.releaseDrone();
    }

    @Override
    public boolean isFinished(){
        return true;
    }




}
