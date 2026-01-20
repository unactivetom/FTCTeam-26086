package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.TeleOp.DECODESEASON.Mechanism;

//Go to 192.168.49.1:8080/dash for other and 192.168.43.1:8080/dash for control hub


@TeleOp(name = "Main", group = "DECODE")
public class MainTeleOp extends OpMode {

    private Mechanism mechanism = new Mechanism();
    private MechanumRobotCentric drive;
    private FtcDashboard dashboard = FtcDashboard.getInstance();
    public TelemetryPacket packet = new TelemetryPacket();



    @Override
    public void init(){
        drive = new MechanumRobotCentric(false);
        packet.addLine("INIT");
        telemetry.addLine("### INIT ###");
        drive.init(hardwareMap);
        mechanism.init(hardwareMap);
        telemetry.update();
        dashboard.sendTelemetryPacket(packet);
    }

    @Override
    public void loop(){
        telemetry.addData("### TELEOP ###", "");
        drive.driveLoop(gamepad1, telemetry);
        telemetry.addData("Brake setting", drive.brake ? "Brake" : "Float");
        mechanism.loop(gamepad1, telemetry);
        telemetry.update();
        dashboard.sendTelemetryPacket(packet);
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
