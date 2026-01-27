package org.firstinspires.ftc.teamcode.TeleOp.DECODESEASON;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.TeleOp.Drive;
import org.firstinspires.ftc.teamcode.TeleOp.MainTeleOp;

import java.util.HashMap;

public class Mechanism {

    DcMotor lowerThroughPut;
    DcMotor shooter;
    DcMotor upperThroughPut;
    DcMotor intake;
    DistanceSensor distanceSensor;
    Drive drive = new Drive();

    HashMap<Integer, Double> speedMap = new HashMap<>();

    int intakeSpeed = 288*2;  //in ticks per sec, 288 is one rotation
    int indexerSpeed = 288*2;  //in ticks per sec, 288 is one rotation
    double shooterPower = 0.7;  //in ticks per min, 28 is one rotation

    MainTeleOp main;

    public Mechanism(){

    }

    public void init(HardwareMap hardwareMap){
        main = new MainTeleOp();
        lowerThroughPut = hardwareMap.get(DcMotor.class, "lowerThroughPut");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        upperThroughPut = hardwareMap.get(DcMotor.class, "upperThroughPut");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distance");
        intake = hardwareMap.get(DcMotor.class, "intake");

        drive.initializer((byte) 4, hardwareMap);


        lowerThroughPut.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        upperThroughPut.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerThroughPut.setDirection(DcMotorSimple.Direction.FORWARD);

        speedMap.put(35, 0.6);
        speedMap.put(50, 0.65);
        speedMap.put(75, 0.7);
        speedMap.put(100, 0.75);


    }

    public void loop(Gamepad gamepad, Telemetry telemetry){
        if(gamepad.rightBumperWasPressed()) {
            lowerThroughPut(true, true);
            intake(true, true);
        }
        if(gamepad.rightBumperWasReleased()) {
            lowerThroughPut(false, true);
            intake(false, true);
        }
        if(gamepad.leftBumperWasPressed())
            shooter(true);
        if(gamepad.leftBumperWasReleased())
            shooter(false);
        if(gamepad.circleWasPressed()) {
            lowerThroughPut(true, false);
            intake(true, false);
        }
        if(gamepad.circleWasReleased()) {
            lowerThroughPut(false, false);
            intake(false, false);
        }
        if(gamepad.crossWasPressed())
            upperThroughPut(true);
        if(gamepad.crossWasReleased())
            upperThroughPut(false);
        if(gamepad.dpadLeftWasPressed() && shooterPower > 0.05)
            shooterPower -= 0.05;
        if(gamepad.dpadRightWasPressed() && shooterPower < 0.95)
            shooterPower += 0.05;
        if(gamepad.triangleWasPressed())
            autoPosition();

        telemetry.addData("shooterPower: ", shooterPower);
        telemetry.addData("Distance: ", distanceSensor.getDistance(DistanceUnit.CM));
        main.mainPacket.put("shooter Power: ", shooterPower);


    }

    private void lowerThroughPut(boolean value, boolean forward){
        double direction = forward ? 1 : -1;
        lowerThroughPut.setPower(value ? direction : 0);
    }

    private void intake(boolean value, boolean forward){
        double direction = forward ? 1 : -1;
        intake.setPower(value ? direction : 0);
    }


    private void shooter(boolean value) {
        shooter.setPower(value? shooterPower : 0);
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

    public void autoPosition(){
//        speedMap.forEach((n, k) -> {
//            double distance = distanceSensor.getDistance(DistanceUnit.CM);
//            if(Math.abs(n - distance) < 10){
//                shooterPower = speedMap.get(n);
//                while(Math.abs(n - distance) > 2){
//                    distance = distanceSensor.getDistance(DistanceUnit.CM);
//                    drive.setPowers(distance > n ? 0.2 : -0.2, distance > n ? 0.2 : -0.2, distance > n ? 0.2 : -0.2, distance > n ? 0.2 : -0.2);
//
//                }
//                drive.setPowers(0,0,0,0);
//            }
//        });

        for(int dist : speedMap.keySet()){
            if(Math.abs(dist - distanceSensor.getDistance(DistanceUnit.CM)) < 10){
                shooterPower = speedMap.get(dist);
                while(Math.abs(dist - distanceSensor.getDistance(DistanceUnit.CM)) > 2){
                    double speed = Range.clip(distanceSensor.getDistance(DistanceUnit.CM) - dist, -0.3, 0.3);
                    drive.setPowers(speed,speed,speed,speed);
                }
                drive.setPowers(0,0,0,0);

            }
        }


    }





}
