package org.firstinspires.ftc.teamcode.TELEOP;

import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.A2;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.X2;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Commands.Climbing_Command;
import org.firstinspires.ftc.teamcode.Commands.Deposit_Command;
import org.firstinspires.ftc.teamcode.Commands.Drive_Command;
import org.firstinspires.ftc.teamcode.Commands.Drone_Command;
import org.firstinspires.ftc.teamcode.Commands.Intake_Command;
import org.firstinspires.ftc.teamcode.Commands.Lift_Command;
import org.firstinspires.ftc.teamcode.Subsystems.Climbing_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Deposit_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drone_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Intake_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Lift_Subsystem;

abstract public class OpModeTemplate extends CommandOpMode {
    protected Climbing_Subsystem climbingSubsystem;
    protected Drone_Subsystem droneSubsystem;
    protected Drive_Subsystem driveSubsystem;
    protected Drive_Command driveCommand;
    protected Lift_Subsystem liftSubsystem;
    protected Lift_Command liftCommand;
    protected Intake_Subsystem intakeSubsystem;
    protected Intake_Command intakeCommand;
    protected Deposit_Subsystem depositSubsystem;
    protected Deposit_Command depositCommand;
    public static GamepadEx toolOp;
    public static GamepadEx driverOp;

    protected void initSubsystems(){
        climbingSubsystem = new Climbing_Subsystem(hardwareMap);

        droneSubsystem = new Drone_Subsystem(hardwareMap);

        driveSubsystem = new Drive_Subsystem(hardwareMap);

        liftSubsystem = new Lift_Subsystem(hardwareMap);

        intakeSubsystem = new Intake_Subsystem(hardwareMap);

        depositSubsystem = new Deposit_Subsystem(hardwareMap);

    }
    protected void initCommands(){
        X2.whenPressed(new Climbing_Command(climbingSubsystem));
        A2.whenPressed(new Drone_Command(droneSubsystem));
        driveCommand = new Drive_Command(driveSubsystem);
        liftCommand = new Lift_Command(liftSubsystem);
        intakeCommand = new Intake_Command(intakeSubsystem, depositSubsystem, liftSubsystem);
        depositCommand = new Deposit_Command(depositSubsystem, liftSubsystem);
    }
    protected void initGamepad(){
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);

    }



}
