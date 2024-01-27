package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.climb.ClimdMaxHight;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.constants.climb.FullyRetract;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Climbing_Subsystem extends SubsystemBase {
    private final MotorEx Climbing_Motor;

    public Climbing_Subsystem(final HardwareMap hMap) {
        Climbing_Motor = new MotorEx(hMap, "clmMotor", MotorEx.GoBILDA.RPM_1150);
        Climbing_Motor.resetEncoder();
        Climbing_Motor.setRunMode(Motor.RunMode.PositionControl);


    }

    @Override
    public void periodic(){

    }
    public void Open() {
        Climbing_Motor.setTargetPosition(ClimdMaxHight);
    }

    public void Retract() {
        Climbing_Motor.setTargetPosition(FullyRetract);
    }
}
