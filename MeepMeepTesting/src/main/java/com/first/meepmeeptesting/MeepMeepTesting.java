package com.first.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTesting {


    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);
        Pose2d beginPose = new Pose2d(56, -16, Math.toRadians(180));
        DrivePath drivePath = new DrivePath();



        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(8, 16)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 7)
                .build();


        myBot.runAction(drivePath.getPath(DrivePath.autoPaths.PRELOAD_NINE_BALLS, myBot));


        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}