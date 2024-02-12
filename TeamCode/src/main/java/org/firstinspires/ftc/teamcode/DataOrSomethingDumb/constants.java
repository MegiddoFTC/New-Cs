package org.firstinspires.ftc.teamcode.DataOrSomethingDumb;

public final class constants {
    public final static class climb{
        //TODO: ADD MAX HIGHT
        public static int MIN_FOR_OPN_SRV = 1000;
    }
    public final static class droneRelease{
        //TODO: Check pos of servo
        public final static int droneRelease = 1;
        public final static int droneStore = 0;
    }
    public final static class intake{
        //TODO: Check pos of servo
        public final static double intakeHigh = 0;
        public final static double intakeMidHigh = 100;
        public final static double intakeMidLow = 200;
        public final static double intakeLow = 300;


    }
    public final static class deposit{
        public final static class colors{
            //TODO: ADD DATA
            public static int[] greenMin = {0,0,0};
            public static int[] greenMax = {0,0,0};
            public static int[] whiteMin = {0,0,0};
            public static int[] whiteMax = {0,0,0};
            public static int[] purpleMin = {0,0,0};
            public static int[] purpleMax = {0,0,0};
            public static int[] yellowMin = {0,0,0};
            public static int[] yellowMax = {0,0,0};
            public static int ServoStop = 0;
            public static double depositAngle = 200;
            public static double intakeAngle = 200;
        }
    }



}
