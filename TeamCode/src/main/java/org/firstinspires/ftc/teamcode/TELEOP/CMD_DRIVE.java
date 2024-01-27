package org.firstinspires.ftc.teamcode.TELEOP;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp
public class CMD_DRIVE extends OpModeTemplate{
    @Override
    public void initialize(){
        initGamepad();
        initSubsystems();
        initCommands();
    }

}
