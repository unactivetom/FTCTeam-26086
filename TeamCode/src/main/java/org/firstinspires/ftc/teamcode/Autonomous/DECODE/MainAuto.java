package org.firstinspires.ftc.teamcode.Autonomous.DECODE;


import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "DECODEMain", group = "DECODE")
public class MainAuto extends LinearOpMode {


    @Override
    public void runOpMode() {
        Shooter shooter = new Shooter(3, 1);
        //DrivePath drive = new DrivePath();
        Tank drive = new Tank();
        shooter.init(hardwareMap);
        drive.init(hardwareMap);

        waitForStart();

        drive.driveOn();
        sleep(1000);
        drive.driveOff();
        shooter.run();


        //shooter.intakeOn(true);


        //Actions.runBlocking(new SequentialAction(drive.getPath(DrivePath.autoPaths.PRELOAD_NINE_BALLS)));


        //shooter.intakeOn(false);
        //shooter.pusherOn(false);
        //shooter.shooterSet(0);
    }




}
