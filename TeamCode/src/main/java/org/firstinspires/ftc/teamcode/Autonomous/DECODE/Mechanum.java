package org.firstinspires.ftc.teamcode.Autonomous.DECODE;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Mechanum {

    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;

    public int programCount = 0;

    public void init(HardwareMap hardwareMap){
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void forward(double distance, double power, Telemetry telemetry){
        programCount++;
        //Set to not running for setting the target position
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set the power
        leftFront.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(power/1.3);
        rightBack.setPower(power);

        telemetry.addData("leftBack: ", leftBack.getPower());

        //set target pos
        leftFront.setTargetPosition((int) (distance * 47.534)/2);
        rightFront.setTargetPosition((int) (distance * 47.534)/2);
        leftBack.setTargetPosition((int) (((int) (distance * 47.534))/1.3));
        rightBack.setTargetPosition((int) (distance * 47.534)/2);

        //run to target pos
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(leftFront.isBusy()){

        }

    }

    public void left(double distance, double power, Telemetry telemetry){
        programCount++;

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set the power
        leftFront.setPower(-power);
        rightFront.setPower(power);
        leftBack.setPower(power/1.3);
        rightBack.setPower(-power);

        telemetry.addData("leftBack: ", leftBack.getPower());

        //set target pos
        leftFront.setTargetPosition(-((int) (distance * 47.534)));
        rightFront.setTargetPosition(((int) (distance * 47.534)));
        leftBack.setTargetPosition((int) (((int) (distance * 47.534))/1.3));
        rightBack.setTargetPosition(-((int) (distance * 47.534)));

        //run to target pos
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(leftFront.isBusy()){

        }
    }

    public void stop(){
        leftFront.setPower(0.0);
        rightFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightBack.setPower(0.0);
    }


}
