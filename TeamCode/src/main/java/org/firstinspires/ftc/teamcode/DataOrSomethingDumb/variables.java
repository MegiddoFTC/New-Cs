package org.firstinspires.ftc.teamcode.DataOrSomethingDumb;

import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.LeftStickX1;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.LeftStickY1;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.RightStickX1;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.driveTriggersAxis;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.intGetLeftBumper2;
import static org.firstinspires.ftc.teamcode.DataOrSomethingDumb.Buttons.intGetRightBumper2;

import com.arcrobotics.ftclib.controller.PController;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;

public class variables {
    public static class lift{
        public static double kP = 0, kI = 0, kD = 0;
        public static double kF = 0;
        public static int LiftTargPose;
        public static double LiftPower;
        public static PIDFController pidf = new PIDFController(lift.kP, lift.kI, lift.kD, lift.kF);
        public static double output;
        public static PController pController = new PController(kP);


    }
    public static class climb{
        public static boolean ClimbIsOpen = false;
    }
    public static class intake{
        public static int IntakePower = intGetRightBumper2 - intGetLeftBumper2;
    }
    public static class deposit{

    }
    public static class drive{
        public static double drivePower = 1;
        public static double xPower = (LeftStickX1+driveTriggersAxis)*drivePower;
        public static double yPower = LeftStickY1*drivePower;
        public static double SpinPower = RightStickX1*drivePower;
    }

}
