package com.first.meepmeeptesting;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.roadrunner.DriveTrainType;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class DrivePath {

    public enum autoPaths{
        STANDARD_SIX_BALLS,
        PRELOAD_NINE_BALLS,
        PRELOAD_NINE_BALLS_ALTSTART

    }



    public DriveTrainType drive = DriveTrainType.MECANUM;
    private Pose2d standardSixBallsStart = new Pose2d(56, -16, Math.toRadians(180));
    private Pose2d preloadNineBallsStart = standardSixBallsStart;
    private Pose2d preloadNineBallsAltStartStart = new Pose2d(-52.5, -49.5, Math.toRadians(235));

    public DrivePath(){

    }


    public Action getPath(autoPaths aP, RoadRunnerBotEntity bot){
        switch (aP) {
            case STANDARD_SIX_BALLS:
                Action standardSixBalls = bot.getDrive().actionBuilder(standardSixBallsStart)
                    .splineTo(new Vector2d(12, -34), Math.toRadians(270))
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
                return standardSixBalls;
            case PRELOAD_NINE_BALLS:
                Action preloadNineBalls = bot.getDrive().actionBuilder(preloadNineBallsStart)
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
                


            default:
                return bot.getDrive().actionBuilder(standardSixBallsStart).build();
        }




    }

}
