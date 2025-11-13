package org.firstinspires.ftc.teamcode.Autonomous.DECODE;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunnerLib.MecanumDrive;


public class DrivePath {
    public MecanumDrive drive;
    private Pose2d beginPose = new Pose2d(56, -16, Math.toRadians(180));

    public DrivePath(){

    }


    public void init(HardwareMap hardwareMap){
        drive = new MecanumDrive(hardwareMap, beginPose);
    }

    public Action getPath(int pathNumber){
        switch (pathNumber) {
            case 1:Action path = drive.actionBuilder(beginPose)
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
                return path;
            default:
                return drive.actionBuilder(beginPose).build();
        }




    }

}
