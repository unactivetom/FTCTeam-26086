package org.firstinspires.ftc.teamcode.Autonomous.DECODE;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "DECODE BLUE", group = "DECODE")
public class DecodeBlue extends LinearOpMode {


    @Override
    public void runOpMode() {
        Shooter firstShooter = new Shooter(3, 1.0);
        //DrivePath drive = new DrivePath();
        Mechanum drive = new Mechanum();
        firstShooter.init(hardwareMap);
        drive.init(hardwareMap);

        waitForStart();

        drive.forward(-20, -0.5);
        firstShooter.run();
        drive.left(20, 0.5);


        //shooter.intakeOn(true);


        //Actions.runBlocking(new SequentialAction(drive.getPath(DrivePath.autoPaths.PRELOAD_NINE_BALLS)));


        //shooter.intakeOn(false);
        //shooter.pusherOn(false);
        //shooter.shooterSet(0);
    }




}
