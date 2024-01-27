package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.intGetLeftBumper2;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.intGetRightBumper2;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.depositAngle;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.intakeAngle;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.lift.LiftTargPose;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Deposit_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Lift_Subsystem;

public class Deposit_Command extends CommandBase {
    private final Deposit_Subsystem depositSubsystem;
    private final Lift_Subsystem liftSubsystem;

    public Deposit_Command(Deposit_Subsystem depositSubsystem, Lift_Subsystem liftSubsystem) {
        this.depositSubsystem = depositSubsystem;
        this.liftSubsystem = liftSubsystem;
        addRequirements(liftSubsystem, depositSubsystem);
    }
    @Override
    public void execute(){
        if ((!liftSubsystem.isLiftAtIntake()&&LiftTargPose > 20)){
            depositSubsystem.ArmMove(depositAngle);
            depositSubsystem.IntakeAndDepositRightWheel(intGetRightBumper2);
            depositSubsystem.IntakeAndDepositLeftWheel(intGetLeftBumper2);
        }
        else {
            depositSubsystem.ArmMove(intakeAngle);
        }
    }
}
