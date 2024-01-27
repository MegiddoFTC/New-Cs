package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.getRightBumper1;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.drive.SpinPower;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.drive.drivePower;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.drive.xPower;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.variables.drive.yPower;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.GyroEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Drive_Subsystem extends SubsystemBase {
    protected final MotorEx leftFront, rightFront, leftBack, rightBack;
    private final RevIMU imu;
    Gamepad gamepad;


    private final MecanumDrive drive;

    public Drive_Subsystem(HardwareMap hMap) {
        leftFront = new MotorEx(hMap, "LeftFront");
        rightBack = new MotorEx(hMap, "RightBack");
        rightFront = new MotorEx(hMap, "RightFront");
        leftBack = new MotorEx(hMap, "LeftBack");
        imu = new RevIMU(hMap);
        imu.init();
        gamepad =  new Gamepad();


        drive = new MecanumDrive(leftFront, rightFront, leftBack, rightBack);
    }

    @Override
    public void periodic(){

    }
    public void drive(){
        drive.driveFieldCentric(
                xPower,
                yPower,
                SpinPower,
                imu.getHeading()

        );

        if (getRightBumper1){
            drivePower = 0.3;
        }
        else {
            drivePower = 1;
        }
    }

}
