package org.firstinspires.ftc.teamcode.TeleOp.DECODESEASON;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.TeleOp.MainTeleOp;

public class Mechanism {

    DcMotor intake;
    DcMotor shooter;
    DcMotor upperThroughPut;

    int intakeSpeed = 288*2;  //in ticks per sec, 288 is one rotation
    int indexerSpeed = 288*2;  //in ticks per sec, 288 is one rotation
    double shooterPower = 0.7;  //in ticks per sec, 28 is one rotation

    MainTeleOp main;

    public Mechanism(){

    }

    public void init(HardwareMap hardwareMap){
        main = new MainTeleOp();
        intake = hardwareMap.get(DcMotor.class, "intake");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        upperThroughPut = hardwareMap.get(DcMotor.class, "upperThroughPut");

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        upperThroughPut.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void loop(Gamepad gamepad, Telemetry telemetry){
        if(gamepad.rightBumperWasPressed())
            intake(true, true);
        if(gamepad.rightBumperWasReleased())
            intake(false, true);
        if(gamepad.leftBumperWasPressed())
            shooter(true);
        if(gamepad.leftBumperWasReleased())
            shooter(false);
        if(gamepad.circleWasPressed())
            intake(true, false);
        if(gamepad.circleWasReleased())
            intake(false, false);
        if(gamepad.crossWasPressed())
            upperThroughPut(true);
        if(gamepad.crossWasReleased())
            upperThroughPut(false);
        if(gamepad.dpadLeftWasPressed() && shooterPower > 0.1)
            shooterPower -= 0.1;
        if(gamepad.dpadRightWasPressed() && shooterPower < 1.0)
            shooterPower += 0.1;

        telemetry.addData("shooterPower: ", shooterPower);
        main.mainPacket.put("shooter Power: ", shooterPower);


    }

    private void intake(boolean value, boolean forward){
        double direction = forward ? 1 : -1;
        intake.setPower(value ? direction : 0);
    }


    private void shooter(boolean value) {
        shooter.setPower(value ? shooterPower : 0);
    }

    private void upperThroughPut(boolean value){
        upperThroughPut.setPower(value ? -1 : 0);
    }



    public void setIntakeSpeed(int intakeSpeed) {
        this.intakeSpeed = intakeSpeed;
    }

    public void setIndexerSpeed(int indexerSpeed) {
        this.indexerSpeed = indexerSpeed;
    }





}
