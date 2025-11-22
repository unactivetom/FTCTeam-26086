package org.firstinspires.ftc.teamcode.Autonomous.DECODE;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunnerLib.MecanumDrive;


public class DrivePath {

    public enum autoPaths{
        STANDARD_SIX_BALLS,
        PRELOAD_NINE_BALLS,
        PRELOAD_NINE_BALLS_ALTSTART

    }

    public MecanumDrive drive;
    private final Pose2d standardSixBallsStart = new Pose2d(56, -16, Math.toRadians(180));
    private final Pose2d preloadNineBallsStart = standardSixBallsStart;
    private final Pose2d preloadNineBallsAltStartStart = new Pose2d(-52.5, -49.5, Math.toRadians(235));

    public DrivePath(){

    }


    public void init(HardwareMap hardwareMap, autoPaths path){
        switch (path) {
            case STANDARD_SIX_BALLS:drive = new MecanumDrive(hardwareMap, standardSixBallsStart);
            case PRELOAD_NINE_BALLS:drive = new MecanumDrive(hardwareMap, preloadNineBallsStart);
            case PRELOAD_NINE_BALLS_ALTSTART:drive = new MecanumDrive(hardwareMap, preloadNineBallsAltStartStart);
        }

    }

    public Action getPath(autoPaths path){
        switch (path) {
            case STANDARD_SIX_BALLS:Action standardSixBalls = drive.actionBuilder(standardSixBallsStart)
                    .splineTo(new Vector2d(12, -34), Math.toRadians(270))
                    .lineToY(-50)
                    .lineToYLinearHeading(-30, Math.toRadians(250))
                    .splineToLinearHeading(new Pose2d(-52.5, -49.5, Math.toRadians(235)), Math.toRadians(230))
                    .stopAndAdd(new Shooter(3, 1))
                    .lineToY(-30)
                    .splineToLinearHeading(new Pose2d(-12, -30, Math.toRadians(270)), Math.toRadians(270))
                    .lineToY(-50)
                    .lineToYLinearHeading(-30, Math.toRadians(250))
                    .splineToLinearHeading(new Pose2d(-52.5, -49.5, Math.toRadians(235)), Math.toRadians(230))
                    .stopAndAdd(new Shooter(3, 1))
                    .build();
                return standardSixBalls;
            case PRELOAD_NINE_BALLS:
                Action preloadNineBalls = drive.actionBuilder(preloadNineBallsStart)
                        .splineToLinearHeading(new Pose2d(-52.5, -49.5, Math.toRadians(235)), Math.toRadians(230))
                        .waitSeconds(1)
                        .lineToY(-30)
                        .splineToSplineHeading(new Pose2d(12, -34, Math.toRadians(270)), Math.toRadians(270))
                        .lineToY(-50)
                        .lineToYLinearHeading(-30, Math.toRadians(250))
                        .splineToLinearHeading(new Pose2d(-52.5, -49.5, Math.toRadians(235)), Math.toRadians(230))
                        .waitSeconds(1)
//                    .stopAndAdd(new Shooter(3, 1))
                        .lineToY(-30)
                        .splineToLinearHeading(new Pose2d(-12, -30, Math.toRadians(270)), Math.toRadians(270))
                        .lineToY(-50)
                        .lineToYLinearHeading(-30, Math.toRadians(250))
                        .splineToLinearHeading(new Pose2d(-52.5, -49.5, Math.toRadians(235)), Math.toRadians(230))
                        .waitSeconds(1)
//                    .stopAndAdd(new Shooter(3, 1))
                        .build();
                return preloadNineBalls;
            case PRELOAD_NINE_BALLS_ALTSTART:
                Action preloadNineBallsAltstart = drive.actionBuilder(preloadNineBallsAltStartStart)
                        .waitSeconds(1)
                        .lineToY(-30)
                        .splineToSplineHeading(new Pose2d(12, -34, Math.toRadians(270)), Math.toRadians(270))
                        .lineToY(-50)
                        .lineToYLinearHeading(-30, Math.toRadians(250))
                        .splineToLinearHeading(new Pose2d(-52.5, -49.5, Math.toRadians(235)), Math.toRadians(230))
                        .waitSeconds(1)
//                    .stopAndAdd(new Shooter(3, 1))
                        .lineToY(-30)
                        .splineToLinearHeading(new Pose2d(-12, -30, Math.toRadians(270)), Math.toRadians(270))
                        .lineToY(-50)
                        .lineToYLinearHeading(-30, Math.toRadians(250))
                        .splineToLinearHeading(new Pose2d(-52.5, -49.5, Math.toRadians(235)), Math.toRadians(230))
                        .waitSeconds(1)
//                    .stopAndAdd(new Shooter(3, 1))
                        .build();
                return preloadNineBallsAltstart;



            default:
                return drive.actionBuilder(standardSixBallsStart).build();
        }




    }

}
