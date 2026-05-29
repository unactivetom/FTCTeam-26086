package org.firstinspires.ftc.teamcode.Autonomous.DECODE;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



@Autonomous(name = "DECODE RED", group = "DECODE")
public class DecodeRed extends LinearOpMode {


    //public SampleMecanumDrive driveRR = new SampleMecanumDrive(hardwareMap);

    @Override
    public void runOpMode() {
        Shooter firstShooter = new Shooter(3, 0.55);
        //DrivePath drive = new DrivePath();
        Mechanum drive = new Mechanum();
        firstShooter.init(hardwareMap);
        drive.init(hardwareMap);

        waitForStart();

        drive.forward(-50, -1, telemetry);
        telemetry.update();
        drive.stop();
        firstShooter.run();
        drive.left(-40, -1, telemetry);
        telemetry.update();


    }




}
