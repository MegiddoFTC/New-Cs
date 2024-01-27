package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.lift.LiftTargPose;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.R;

public class Lift_Subsystem extends SubsystemBase {
    public final MotorGroup LiftMotors;


    public Lift_Subsystem(HardwareMap hardwareMap) {
        MotorEx leftLiftMotor = new MotorEx(hardwareMap, "LeftLiftMotor", MotorEx.GoBILDA.RPM_435);
        MotorEx rightLiftMotor = new MotorEx(hardwareMap, "RightLiftMotor", MotorEx.GoBILDA.RPM_435);
        rightLiftMotor.setInverted(true);
        LiftMotors = new MotorGroup(leftLiftMotor, rightLiftMotor);
        LiftMotors.resetEncoder();
    }



    @Override
    public void periodic(){

    }
    public void MoveLift(double LiftPower){
        LiftMotors.setRunMode(Motor.RunMode.RawPower);
        LiftMotors.set(LiftPower);
        LiftTargPose = LiftMotors.getCurrentPosition();
    }
    public void KeepLiftPose(){
        LiftMotors.setRunMode(Motor.RunMode.PositionControl);
        LiftMotors.setTargetPosition(LiftTargPose);
    }
    public void ReturnLiftTo0(){
        LiftTargPose = 0;
    }
    public boolean isLiftAtIntake(){
        return (LiftMotors.getCurrentPosition()<20);
    }
}
