package org.firstinspires.ftc.teamcode.DataOrSomethingDumb;


import static org.firstinspires.ftc.teamcode.TELEOP.OpModeTemplate.driverOp;
import static org.firstinspires.ftc.teamcode.TELEOP.OpModeTemplate.toolOp;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Supplier;

public class Buttons {


    public static Button AutoButtonMap(GamepadEx gamepadEx, GamepadKeys.Button gamepadButton){
        return gamepadEx.getGamepadButton(gamepadButton);
    }
    public static double autoGetTrigger(GamepadEx gamepadEx, GamepadKeys.Trigger trigger){
        return gamepadEx.getTrigger(trigger);
    }
    public static boolean autoGetButtons(GamepadEx gamepadEx, GamepadKeys.Button button){
        return gamepadEx.getButton(button);
    }
    public static double TriggerAxis(double leftTrigger, double rightTrigger){
        return rightTrigger-leftTrigger;
    }
    public enum GetDpad{
        Up,
        Right,
        Left,
        Down
    }
    public static GetDpad getDpad(){
        if (getUp2){
            return GetDpad.Up;
        }
        else if (getRight2){
            return GetDpad.Right;
        }
        else if (getLeft2){
            return GetDpad.Left;
        }
        else if (getDown2){
            return GetDpad.Down;
        }
        return null;

    }

    public static Button X2 = AutoButtonMap(toolOp, GamepadKeys.Button.X);
    public static Button A2 = AutoButtonMap(toolOp, GamepadKeys.Button.A);
    public static double leftTrigger2 = autoGetTrigger(toolOp, GamepadKeys.Trigger.LEFT_TRIGGER);
    public static double rightTrigger2 = autoGetTrigger(toolOp, GamepadKeys.Trigger.RIGHT_TRIGGER);
    public static double leftTrigger1 = autoGetTrigger(driverOp, GamepadKeys.Trigger.LEFT_TRIGGER);
    public static double rightTrigger1 = autoGetTrigger(driverOp, GamepadKeys.Trigger.RIGHT_TRIGGER);
    public static boolean getX2 = autoGetButtons(toolOp, GamepadKeys.Button.X);
    public static boolean getB2 = autoGetButtons(toolOp, GamepadKeys.Button.B);
    public static boolean getUp2 = autoGetButtons(toolOp, GamepadKeys.Button.DPAD_UP);
    public static boolean getRight2 = autoGetButtons(toolOp, GamepadKeys.Button.DPAD_RIGHT);
    public static boolean getLeft2 = autoGetButtons(toolOp, GamepadKeys.Button.DPAD_LEFT);
    public static boolean getDown2 = autoGetButtons(toolOp, GamepadKeys.Button.DPAD_DOWN);
    public static boolean getRightBumper2 = autoGetButtons(toolOp, GamepadKeys.Button.RIGHT_BUMPER);
    public static boolean getLeftBumper2 = autoGetButtons(toolOp, GamepadKeys.Button.LEFT_BUMPER);
    public static boolean getRightBumper1 = autoGetButtons(driverOp, GamepadKeys.Button.RIGHT_BUMPER);
    public static int intGetLeftBumper2 = getLeftBumper2 ? 1 : 0;
    public static int intGetRightBumper2 = getRightBumper2 ? 1 : 0;
    public static double LeftStickX1 = driverOp.getLeftX();
    public static double LeftStickY1 = driverOp.getLeftY();
    public static double RightStickX1 = driverOp.getRightX();
    public static double toolsTriggersAxis = TriggerAxis(leftTrigger2, rightTrigger2);
    public static double driveTriggersAxis = TriggerAxis(leftTrigger1, rightTrigger1);
}
