package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.greenMax;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.greenMin;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.purpleMin;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.purpleMax;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.whiteMax;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.whiteMin;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.yellowMax;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.deposit.colors.yellowMin;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Deposit_Subsystem extends SubsystemBase {
    public final SimpleServo ArmServoLeft, ArmServoRight, DepositAngleServo;
    public final CRServo ServoWheelLeft, ServoWheelRight;
    public final SensorColor LeftColorSensor, RightColorSensor;
    public enum Color{
        white,
        green,
        purple,
        yellow
    }

    public Deposit_Subsystem(HardwareMap hMap) {
        DepositAngleServo = new SimpleServo(hMap, "DepositAngleServo", 0, 300);
        ArmServoLeft = new SimpleServo(hMap, "ArmServoLeft", 0, 300);
        ArmServoRight = new SimpleServo(hMap, "ArmServoRight", 0, 300);
        ArmServoRight.setInverted(true);
        ServoWheelLeft = new CRServo(hMap, "ServoWheelLeft");
        ServoWheelLeft.setInverted(true);
        ServoWheelRight = new CRServo(hMap, "ServoWheelRight");
        LeftColorSensor = new SensorColor(hMap.get(ColorSensor.class, "LeftColorSensor"));
        RightColorSensor = new SensorColor(hMap.get(ColorSensor.class, "RightColorSensor"));
    }
    @Override
    public void periodic(){

    }

    public void ArmMove(double ArmAngle){
        ArmServoRight.turnToAngle(ArmAngle);
        ArmServoLeft.turnToAngle(ArmAngle);
    }
    public void DepositAngle(double DepositAngle){
        DepositAngleServo.turnToAngle(DepositAngle);
    }
    public void IntakeAndDepositLeftWheel(double ServoWheelPower){
        ServoWheelLeft.set(ServoWheelPower);


    }
    public void IntakeAndDepositRightWheel(int ServoWheelPower){
        ServoWheelRight.set(ServoWheelPower);
        }



    public Color ColorSensingLeft(){
        return ColorSensing(LeftColorSensor);
    }
    public Color ColorSensingRight(){
        return ColorSensing(RightColorSensor);
    }
    public Color ColorSensing(SensorColor colorSensor){
        if (CheckColor(colorSensor, purpleMin, purpleMax)){
            return Color.purple;
        }
        else if (CheckColor(colorSensor, whiteMin, whiteMax)){
            return Color.white;
        }
        else if (CheckColor(colorSensor, greenMin, greenMax)){
            return Color.green;
        }
        else if (CheckColor(colorSensor, yellowMin, yellowMax)){
            return Color.yellow;
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
