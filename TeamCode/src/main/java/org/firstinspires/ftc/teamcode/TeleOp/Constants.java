package org.firstinspires.ftc.teamcode.TeleOp;

public class Constants {
    public static final double SLOW_SPEED = 0.4;

    /*TUNING*/
    public static TuneStruct LFTUNE = new TuneStruct(1.0, "LFTUNE");
    public static TuneStruct RFTUNE = new TuneStruct(1.0, "LFTUNE");
    public static TuneStruct LBTUNE = new TuneStruct(1.0, "LFTUNE");
    public static TuneStruct RBTUNE = new TuneStruct(1.0, "LFTUNE");

    public static class TuneStruct{
        public double value;
        public String name;

        public TuneStruct(double value, String name){
            this.value = value;
            this.name = name;
        }

    }

}
