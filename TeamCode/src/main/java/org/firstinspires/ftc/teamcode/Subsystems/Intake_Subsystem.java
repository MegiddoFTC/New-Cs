package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake_Subsystem extends SubsystemBase {
    public MotorEx IntakeMotor;
    public SimpleServo LeftIntakeServo, RightIntakeServo;
    CRServo RollerServo;
    public Intake_Subsystem(final HardwareMap hmap){
        IntakeMotor = new MotorEx(hmap, "IntakeMotor", Motor.GoBILDA.RPM_1150);
        RollerServo = new CRServo(hmap, "RollerServo");
        LeftIntakeServo = new SimpleServo(hmap, "LeftIntakeServo", 0, 300);
        RightIntakeServo = new SimpleServo(hmap, "RightIntakeServo", 0, 300);
        RightIntakeServo.setInverted(true);
    }


    @Override
    public void periodic(){

    }

    public void IntakeHight(double intakeAngle){
        RightIntakeServo.turnToAngle(intakeAngle);
        LeftIntakeServo.turnToAngle(intakeAngle);
    }
    public void PowerIntake(double IntakePower){
        IntakeMotor.set(IntakePower);
        RollerServo.set(IntakePower);
    }
    public void StopIntake(){
        IntakeMotor.stopMotor();
        RollerServo.stopMotor();
    }

}
