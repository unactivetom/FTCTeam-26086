package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOp.DECODESEASON.Mechanism;


@TeleOp(name = "Main", group = "DECODE")
public class MainTeleOp extends OpMode {

    private Mechanism mechanism = new Mechanism();
    private Tank drive;


    @Override
    public void init(){
        if(drive == null) drive = new Tank(true);

        System.out.println("### INIT ###");
        telemetry.addData("### INIT ###", "");
        drive.init(hardwareMap);
        mechanism.init(hardwareMap);
        telemetry.update();
    }

    @Override
    public void loop(){
        telemetry.addData("### TELEOP ###", "");
        drive.driveLoop(gamepad1);
        mechanism.loop(gamepad1);
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
