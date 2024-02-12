package org.firstinspires.ftc.teamcode.TELEOP;

import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.getB2;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.getRightBumper1;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.climb.MIN_FOR_OPN_SRV;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.greenMax;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.greenMin;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.purpleMax;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.purpleMin;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.whiteMax;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.whiteMin;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.yellowMax;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.yellowMin;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.drive.SpinPower;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.drive.drivePower;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.drive.xPower;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.drive.yPower;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.lift.LiftPower;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.lift.LiftTargPose;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.Deposit_Command;
import org.firstinspires.ftc.teamcode.Subsystems.Deposit_Subsystem;

public class SAFE_DRIVE extends LinearOpMode {
    public static double drivePower = 1;
    private enum INTK_OR_DPS{
        DPS,
        INTK
    }
    Servo ArmServoLeft, ArmServoRight, DepositAngleServo;
    CRServo ServoWheelLeft, ServoWheelRight;
    MotorEx Climbing_Motor;
    MotorEx leftFront, rightFront, leftBack, rightBack;
    SensorColor LeftColorSensor, RightColorSensor;
    Servo DroneServo;
    Servo CLM_SERVO;
    MotorEx IntakeMotor;
    MotorGroup LiftMotors, DT;
    MotorEx rightLiftMotor, leftLiftMotor;
    MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        ArmServoLeft = hardwareMap.get(Servo.class, "ArmServoLeft");
        ArmServoRight = hardwareMap.get(Servo.class, "ArmServoRight");
        DepositAngleServo = hardwareMap.get(Servo.class, "DepositAngleServo");
        DroneServo = hardwareMap.get(Servo.class, "DroneServo");
        CLM_SERVO = hardwareMap.get(Servo.class, "CLM_SERVO");
        ServoWheelLeft = hardwareMap.get(CRServo.class, "ServoWheelLeft");
        ServoWheelRight = hardwareMap.get(CRServo.class, "ServoWheelRight");
        leftFront = new MotorEx(hardwareMap,"leftFront");
        rightFront = new MotorEx(hardwareMap,"rightFront");
        leftBack = new MotorEx(hardwareMap,"leftBack");
        rightBack = new MotorEx(hardwareMap,"rightBack");
        IntakeMotor = new MotorEx(hardwareMap,"IntakeMotor");
        rightLiftMotor = new MotorEx(hardwareMap,"rightLiftMotor");
        leftLiftMotor = new MotorEx(hardwareMap,"leftLiftMotor");
        Climbing_Motor = new MotorEx(hardwareMap,"Climbing_Motor");
        LeftColorSensor = new SensorColor(hardwareMap.get(ColorSensor.class, "LeftColorSensor"));
        RightColorSensor = new SensorColor(hardwareMap.get(ColorSensor.class, "RightColorSensor"));

        rightLiftMotor.setInverted(true);
        LiftMotors = new MotorGroup(leftLiftMotor, rightLiftMotor);
        LiftMotors.resetEncoder();

        DT = new MotorGroup(leftFront, rightFront, leftBack, rightBack);
        DT.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        drive = new MecanumDrive(leftFront, rightFront, leftBack, rightBack);

        Climbing_Motor.resetEncoder();
        Climbing_Motor.setRunMode(Motor.RunMode.RawPower);


        waitForStart();
        while (opModeIsActive()){
            Drive();
            CLM();
            DRN();
            LIFT();
            INTK();
            DPS();
        }
    }
    public void Drive(){
        drive.driveRobotCentric(
                gamepad1.left_stick_x*drivePower,
                gamepad1.left_stick_y*drivePower,
                gamepad1.right_stick_x*drivePower


        );

        if (gamepad1.right_bumper){
            drivePower = 0.3;
        }
        else {
            drivePower = 1;
        }
    }

    public void CLM(){
        if (Climbing_Motor.getCurrentPosition()>MIN_FOR_OPN_SRV){
            CLM_SERVO.setPosition(1);
        }
        Climbing_Motor.set(gamepad2.right_trigger-gamepad2.left_trigger);

    }

    public void DRN(){
        if (gamepad2.y){
            DroneServo.setPosition(1);
        }
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
    public void LIFT(){
        if (gamepad2.left_stick_y>0.1){
            MoveLift(gamepad2.left_stick_y);
        }
        else if (gamepad2.b){
            ReturnLiftTo0();

        }
        else {
            KeepLiftPose();
        }
    }
    public void INTK_IN(){
        IntakeMotor.set(1);
        if (ColorSensingLeft()==null){
            ServoWheelLeft.set(1);
        }
        else {
            ServoWheelLeft.set(0);
        }
        if (ColorSensingRight()==null){
                ServoWheelRight.set(0);
        }
        else {
            ServoWheelLeft.set(1);
        }

    }
    public void INTK_OUT(){
        IntakeMotor.set(-1);
    }
    public void INTK(){
        if (gamepad2.right_bumper&&isLiftAtIntake()){
            INTK_IN();
        }
        else if (gamepad2.left_bumper&&isLiftAtIntake()){
            INTK_OUT();
        }
    }


    public void DPS(){
        if (isLiftAtIntake()){
            ArmMove(1);
            DepositAngle(1);
        }
        else {
            ArmMove(0);
            DepositAngle(0);
        }
        if (gamepad2.left_bumper&&!isLiftAtIntake()){
            IntakeAndDepositLeftWheel(1);
        }
        else if (gamepad2.right_bumper&&!isLiftAtIntake()){
            IntakeAndDepositRightWheel(-1);
        }
    }




    public void ArmMove(double ArmAngle){
        ArmServoRight.setPosition(ArmAngle);
        ArmServoLeft.setPosition(ArmAngle);
    }
    public void DepositAngle(double DepositAngle){
        DepositAngleServo.setPosition(DepositAngle);
    }
    public void IntakeAndDepositLeftWheel(double ServoWheelPower){
        ServoWheelLeft.set(ServoWheelPower);


    }
    public void IntakeAndDepositRightWheel(int ServoWheelPower){
        ServoWheelRight.set(ServoWheelPower);
    }



    public Deposit_Subsystem.Color ColorSensingLeft(){
        return ColorSensing(LeftColorSensor);
    }
    public Deposit_Subsystem.Color ColorSensingRight(){
        return ColorSensing(RightColorSensor);
    }
    public Deposit_Subsystem.Color ColorSensing(SensorColor colorSensor){
        if (CheckColor(colorSensor, purpleMin, purpleMax)){
            return Deposit_Subsystem.Color.purple;
        }
        else if (CheckColor(colorSensor, whiteMin, whiteMax)){
            return Deposit_Subsystem.Color.white;
        }
        else if (CheckColor(colorSensor, greenMin, greenMax)){
            return Deposit_Subsystem.Color.green;
        }
        else if (CheckColor(colorSensor, yellowMin, yellowMax)){
            return Deposit_Subsystem.Color.yellow;
        }
        else return null;
    }

    public boolean CheckColor(SensorColor colorSensor, int[] MinRange, int[] MaxRange){
        int[] CheckedColor = colorSensor.getARGB();
        boolean[] CorrectRange ={false, false, false};
        for (int i = 0; i<3; i++){
            if (MinRange[i]<CheckedColor[i]&&MaxRange[i]>CheckedColor[i]){
                CorrectRange[i] = true;
            }
        }
        return (CorrectRange[0] && CorrectRange[1] && CorrectRange[2]);
    }

}
