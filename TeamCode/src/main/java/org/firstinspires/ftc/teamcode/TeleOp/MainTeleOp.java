package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOp.DECODESEASON.Mechanism;


@TeleOp(name = "Main", group = "DECODE")
public class MainTeleOp extends OpMode {

    //private Mechanism mechanism = new Mechanism();
    private MechanumRobotCentric drive;


    @Override
    public void init(){
        drive = new MechanumRobotCentric(false);

        telemetry.addData("### INIT ###", "");
        drive.init(hardwareMap);
        //mechanism.init(hardwareMap);
        telemetry.update();
    }

    @Override
    public void loop(){
        telemetry.addData("### TELEOP ###", "");
        drive.driveLoop(gamepad1);
        telemetry.addData("Brake setting", drive.brake ? "Brake" : "Float");
        //mechanism.loop(gamepad1);
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
