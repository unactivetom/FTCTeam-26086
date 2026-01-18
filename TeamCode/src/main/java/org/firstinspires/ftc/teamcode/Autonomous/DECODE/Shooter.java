package org.firstinspires.ftc.teamcode.Autonomous.DECODE;

import com.acmerobotics.roadrunner.InstantFunction;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter implements InstantFunction {

    DcMotor shooter;
    DcMotor intake;
    DcMotor upperThroughPut;



    int numberBalls = 0;
    double motorPower = 0;
    public Shooter(int number, double power){
        this.numberBalls = number;
        this.motorPower = power;
    }

    @Override
    public void run() {
        shooterSet(this.motorPower);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}
        intakeOn(true);
        upperThroughPut(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}
        upperThroughPut(false);
        shooterSet(0);
        intakeOn(false);
    }


    public void init(HardwareMap hardwareMap){
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        intake = hardwareMap.get(DcMotor.class, "intake");
        upperThroughPut = hardwareMap.get(DcMotor.class, "upperThroughPut");

        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        upperThroughPut.setDirection(DcMotorSimple.Direction.FORWARD);


    }
    public void intakeOn(boolean value){
        intake.setPower(value? 1.0 : 0.0);
    }

    public void shooterSet(double value){
        shooter.setPower(value);
    }

    public void upperThroughPut(boolean value){
        upperThroughPut.setPower(value? -1.0 : 0.0);
    }

}
