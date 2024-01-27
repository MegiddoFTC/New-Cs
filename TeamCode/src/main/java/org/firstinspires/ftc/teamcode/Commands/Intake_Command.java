package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.ServoStop;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.intake.intakeHigh;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.intake.intakeLow;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.intake.intakeMidHigh;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.intake.intakeMidLow;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.intake.IntakePower;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons;
import org.firstinspires.ftc.teamcode.Subsystems.Deposit_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Intake_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Lift_Subsystem;
import org.openftc.apriltag.AprilTagDetection;


public class Intake_Command extends CommandBase {
    private final Intake_Subsystem intakeSubsystem;
    private final Deposit_Subsystem depositSubsystem;
    private final Lift_Subsystem liftSubsystem;

    public Intake_Command(Intake_Subsystem intakeSubsystem, Deposit_Subsystem depositSubsystem, Lift_Subsystem liftSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.depositSubsystem = depositSubsystem;
        this.liftSubsystem = liftSubsystem;
        addRequirements(intakeSubsystem, depositSubsystem, liftSubsystem);
    }
    @Override
    public void execute(){
        if (liftSubsystem.isLiftAtIntake()) {
            if (Buttons.getDpad() != null) {
                switch (Buttons.getDpad()) {
                    case Down:
                        intakeSubsystem.IntakeHight(intakeLow);
                        break;
                    case Up:
                        intakeSubsystem.IntakeHight(intakeHigh);
                        break;
                    case Right:
                        intakeSubsystem.IntakeHight(intakeMidHigh);
                        break;
                    case Left:
                        intakeSubsystem.IntakeHight(intakeMidLow);


                }
            }
            if (intakeSubsystem.LeftIntakeServo.getAngle() != intakeHigh) {

                if (depositSubsystem.ColorSensingLeft() == null) {
                    depositSubsystem.IntakeAndDepositLeftWheel(IntakePower);
                }
                else {
                    depositSubsystem.IntakeAndDepositLeftWheel(ServoStop);
                }
                if (depositSubsystem.ColorSensingRight() == null) {
                    depositSubsystem.IntakeAndDepositRightWheel(IntakePower);
                }
                else {
                    depositSubsystem.IntakeAndDepositRightWheel(ServoStop);
                }
                if (depositSubsystem.ColorSensingRight() != null && depositSubsystem.ColorSensingLeft() != null) {
                    intakeSubsystem.PowerIntake(-IntakePower);
                } else {
                    intakeSubsystem.PowerIntake(IntakePower);
                }
            }
        }
        else {
            intakeSubsystem.IntakeHight(intakeHigh);
            intakeSubsystem.StopIntake();

        }
    }
}
