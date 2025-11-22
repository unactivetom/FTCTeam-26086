package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Main", group = "DECODE")
public class MainTeleOp extends OpMode {

    private Tank drive = new Tank(false);


    @Override
    public void init(){
        telemetry.addData("### INIT ###", "");
        drive.Init(hardwareMap);

        telemetry.update();
    }

    @Override
    public void loop(){
        telemetry.addData("### TELEOP ###", "");
        drive.driveLoop(gamepad1);

        telemetry.update();
    }



    /*Logging*/
    public void log(String data){
        telemetry.addData(data, "");
    }
    public void log(String data, Object value){
        telemetry.addData(data, value);
    }
    public void log(){
        telemetry.addLine();
    }
}
