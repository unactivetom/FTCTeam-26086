package org.firstinspires.ftc.teamcode.Autonomous.DECODE;


import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "DECODEMain", group = "DECODE")
public class MainAuto extends LinearOpMode {


    @Override
    public void runOpMode() {
        Shooter shooter = new Shooter(0, 0);
        DrivePath drive = new DrivePath();
        shooter.init(hardwareMap);


        waitForStart();

        shooter.intakeOn(true);


        Actions.runBlocking(new SequentialAction(drive.getPath(1)));


        shooter.intakeOn(false);
        shooter.pusherOn(false);
        shooter.shooterSet(0);
    }
}
