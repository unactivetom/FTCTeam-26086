package org.firstinspires.ftc.teamcode.Autonomous.DECODE;

import com.acmerobotics.roadrunner.InstantFunction;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter implements InstantFunction {

    DcMotor shooter;
    DcMotor intake;
    //DcMotor pusher;



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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}
        intakeOn(true);
        //pusherOn(true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}
        //pusherOn(false);
        shooterSet(0);
        intakeOn(false);
    }


    public void init(HardwareMap hardwareMap){
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        intake = hardwareMap.get(DcMotor.class, "intake");
        //pusher = hardwareMap.get(DcMotor.class, "pusher");

    }
    public void intakeOn(boolean value){
        intake.setPower(value? .5:0);
    }

    public void shooterSet(double value){
        shooter.setPower(value);
    }

//    public void pusherOn(boolean value){
//        pusher.setPower(value? .2:0);
//    }

}
